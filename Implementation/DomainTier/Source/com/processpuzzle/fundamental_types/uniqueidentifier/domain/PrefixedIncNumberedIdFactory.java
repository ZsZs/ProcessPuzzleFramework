package com.processpuzzle.fundamental_types.uniqueidentifier.domain;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang.StringUtils;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;

public class PrefixedIncNumberedIdFactory extends GenericUnigueIdentifierFactory<UniqueIdentifier> implements UniqueIdentifierFactory {

   private String prefix;
   private String separator;
   private String idType;
   private Integer length;
   
   private static final String LPAD_CHAR="0";
   
   ProcessPuzzleContext applicationContext;
   
   public PrefixedIncNumberedIdFactory( String idType, String prefix, String separator, Integer length) {
      this.idType = idType;
      this.prefix = prefix;
      this.separator = separator;
      this.length = length;
     
   }
   
   //Properties
   public void setApplicationContext( ProcessPuzzleContext applicationContext ) {
      this.applicationContext = applicationContext;      
   }

   public UniqueIdentifier createNewIdentifier() {
      return createNewIdentifierWithPrefix( prefix );
   }

   public UniqueIdentifier createNewIdentifier( String dinamicPrefix ) {
      return createNewIdentifierWithPrefix( dinamicPrefix );
   }

   private UniqueIdentifier createNewIdentifierWithPrefix( String actualPerfix ) {
      UniqueIdentifier identifier = null;
      
      if ( applicationContext != null ) {
         LastIdNumberRepository lastIdNumberRepositoryRepository = (LastIdNumberRepository) applicationContext.getRepository( LastIdNumberRepository.class );
   
         LastIdNumber lastIdNumber = lastIdNumberRepositoryRepository.findLatestIdByType( idType );
   
         if ( lastIdNumber != null ) {
            Integer latestNumber = lastIdNumber.getLatestNumber();
            String newNumberStr = String.valueOf( latestNumber+1 );
            String newNumberWithLpadStr = StringUtils.leftPad( newNumberStr, length, LPAD_CHAR );
            
            String identifierStr = getIdentifierStr( actualPerfix, separator, newNumberWithLpadStr );
            
            identifier = generateUniqueIdentifierByType( idType, identifierStr );
            
            lastIdNumberRepositoryRepository.incrementLastIdNumber( idType );
         }
      }
      
      return identifier;
   }

   
   
   
   private String getIdentifierStr( String prefix, String separator, String newNumberWithLpadStr ) {
      StringBuffer strBuff = new StringBuffer();
      if (prefix != null) {
         strBuff.append( prefix );
      }
      if (separator != null) {
         strBuff.append( separator );
      }
      if (newNumberWithLpadStr != null) {
         strBuff.append( newNumberWithLpadStr );
      }
      return strBuff.toString();
   }
   
   @SuppressWarnings("unchecked")
   private UniqueIdentifier generateUniqueIdentifierByType( String idType, String identifier) {
     UniqueIdentifier uniqueIdentifier = null;
     if (idType != null && !idType.equals("")) {
         Class identifierClass = null;
         try {
            identifierClass = Class.forName(idType);
            try {
               Class[] argumentClasses = new Class[] { String.class };
               Object[] arguments = new Object[] { identifier };
               Constructor identifierConstructor = identifierClass.getConstructor(argumentClasses);
               uniqueIdentifier = (UniqueIdentifier) identifierConstructor.newInstance(arguments);
            } catch (SecurityException e) {
               throw new UniqueIdentifierInstantiationException(identifierClass.getName(), identifier);
            } catch (NoSuchMethodException e) {
               throw new UniqueIdentifierInstantiationException(identifierClass.getName(), identifier);
            } catch (IllegalArgumentException e) {
               throw new UniqueIdentifierInstantiationException(identifierClass.getName(), identifier);
            } catch (InvocationTargetException e) {
               throw new UniqueIdentifierInstantiationException(identifierClass.getName(), identifier);
            }

         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         } catch (InstantiationException e) {
            e.printStackTrace();
         } catch (IllegalAccessException e) {
            e.printStackTrace();
         }
      }
      
      return uniqueIdentifier;
      
   }
   
   public String getIdType() {
      return idType;
   }
   public Integer getLength() {
      return length;
   }
   public String getPrefix() {
      return prefix;
   }
   public String getSeparator() {
      return separator;
   }

}
