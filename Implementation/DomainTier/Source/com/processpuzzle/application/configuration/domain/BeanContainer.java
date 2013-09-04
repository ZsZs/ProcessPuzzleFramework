/*
Name: 
    - BeanContainer

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.processpuzzle.application.configuration.domain;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;

import com.google.common.collect.Lists;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.commons.generics.GenericTypeParameterInvestigator;
import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.commons.spring.BeanName;
import com.processpuzzle.persistence.domain.EntityFactory;
import com.processpuzzle.persistence.domain.GenericFactory;

public class BeanContainer extends TransientApplicationContext implements com.processpuzzle.application.configuration.domain.ApplicationContext {
   private Resource containerDefinitionResource;
   private GenericApplicationContext springApplicationContext;
   private Map<Class<? extends Entity>, String> entityFactories;
   
   BeanContainer( Application application, Resource definitionResource ) {
      super( application );
      this.containerDefinitionResource = definitionResource;
   }

//Public mutators
   @Override public void setUpTransientComponents() {
      springApplicationContext = new GenericApplicationContext();
      XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader( springApplicationContext );
      try{
         xmlReader.loadBeanDefinitions( containerDefinitionResource );
         springApplicationContext.refresh();
      }catch( BeanDefinitionStoreException e ){
         throw new ConfigurationSetUpException( "BeanContainer", e );
      }catch( Exception e ){
         throw new ConfigurationSetUpException( "BeanContainer", e );
      }
   }
   
   @Override public void tearDownTransientComponents() {
      if( springApplicationContext != null ) springApplicationContext.close();
      springApplicationContext = null;
   }

//Public accessors
   public Object getBean( String beanName ) {
      Object bean = null;
      try{
         bean = springApplicationContext.getBean( beanName );
      }catch( NoSuchBeanDefinitionException e ){
         throw new UndefinedBeanException( beanName, containerDefinitionResource.getFilename() );
      }
      return bean;
   }
   
   @SuppressWarnings("unchecked")
   public <F extends EntityFactory<?>> F getEntityFactory( Class<F> factoryClass ) {
      String beanName = determineBeanNameFromFactoryClass( factoryClass );
      return (F) getBean( beanName );
   }

   public EntityFactory<?> getEntityFactoryByEntityClass( Class<? extends Entity> entityClass ) {
      List<String> targetPackageNames = Lists.newArrayList();
      targetPackageNames.add( "com.processpuzzle" );
      targetPackageNames.add( "com.itkodex" );
      return this.getEntityFactoryByEntityClass( targetPackageNames, entityClass ); 
   }

   public EntityFactory<?> getEntityFactoryByEntityClass( List<String> targetPackageNames, Class<? extends Entity> entityClass ) {
      entityClass.getClass();
      if( entityFactories == null ) discoverFactoryImplementations( targetPackageNames );
      
      return (EntityFactory<?>) getBean( entityFactories.get( entityClass )); 
   }

   //Properties
   public ApplicationContext getApplicationContext() { return springApplicationContext; }

   private <F> String determineBeanNameFromFactoryClass( Class<F> factoryClass ) {
      return BeanName.determineBeanNameFromClass( factoryClass );
   }

   @SuppressWarnings("unchecked")
   private Class<? extends Entity> determineFactoriesTargetClass( Class<?> anyClass ) {
      Class<? extends EntityFactory<?>> factoryClass = (Class<? extends EntityFactory<?>>) anyClass;
      Class<? extends Entity> entityClass = (Class<? extends Entity>) GenericTypeParameterInvestigator.getTypeParameter( factoryClass, 0 );
      return entityClass;
   }

   //Protected, private helper methods
   private void discoverFactoryImplementations( List<String> targetPackageNames ) {
      entityFactories = new HashMap<Class<? extends Entity>, String>();
      
      RunTimeClassHierarchyAnalyser subClassIdentifier = new RunTimeClassHierarchyAnalyser();
      
      try{
         Set<Class<?>> factories = subClassIdentifier.findSubClasses( targetPackageNames, GenericFactory.class.getName() );
         for( Class<?> factoryClass : factories ) {
            Class<? extends Entity> targetEntityClass = determineFactoriesTargetClass( factoryClass );
            if( targetEntityClass == null ) 
               log.error( "Factory: '" + "' dosn't have Entity parameter!" );
            else {
               entityFactories.put( targetEntityClass, determineBeanNameFromFactoryClass( factoryClass ) );
               log.debug( "BeanContainer: Factory implementation '" + targetEntityClass + "' added." );
            }
         }
      }catch( FileNotFoundException e ){
         throw new ConfigurationSetUpException( "BeanContainer", e );
      }
   }
}
