package com.processpuzzle.fundamental_types.uniqueidentifier.domain;

import hu.itkodex.litest.template.ApplicationObjectTestFixture;
import hu.itkodex.litest.template.DefaultApplicationFixture;

import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class PrefixedIncNumberedFactoryTestFixture extends ApplicationObjectTestFixture<PrefixedIncNumberedIdFactory> {
   protected DefaultApplicationFixture applicationFixture;
   protected String configurationDescriptorPath;
   protected PrefixedIncNumberedIdFactory factory;
   protected String idType = "com.processpuzzle.framework.fundamental_types.domain.TestUniqueIdentifier";
   protected Integer initialIdNumber = 1;
   protected String separator = "-";
   protected String prefix = "TEST_ID";
   protected Integer length = new Integer(6);
   protected LastIdNumberRepository lastIdNumberrepository;

   @Override
   protected void configureAfterSutInstantiation() {
      // TODO Auto-generated method stub
   }
   
   @Override
   protected void configureBeforeSutInstantiation() {
      lastIdNumberrepository = (LastIdNumberRepository) applicationContext.getRepository(LastIdNumberRepository.class);
      initializeIdNumber();

      factory = new PrefixedIncNumberedIdFactory( idType, prefix, separator, length );
      factory.setApplicationContext( applicationContext );
   }
   
   @Override
   protected PrefixedIncNumberedIdFactory instantiateSUT() {
      // TODO Auto-generated method stub
      return null;
   }
   
   @Override
   protected void releaseResources() {
      lastIdNumberrepository.deleteLastIdNumberByType( idType );
   }

   private void initializeIdNumber() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      lastIdNumberrepository.initializeLastIdNumber( work, idType, initialIdNumber );
      work.finish();
   }
}
