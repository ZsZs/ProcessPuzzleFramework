package com.processpuzzle.fitnesse.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.processpuzzle.persistence.domain.SimpleResultSet;
import com.processpuzzle.persistence.domain.TestEntity;

public class RepositoryResultSetCheckerTest {
   private RepositoryResultSet<TestEntity> resultSet;
   private TestEntity firstEntity;
   private TestEntity secondEntity;
   private RepositoryResultSetChecker resultChecker;
   private Class<?> expectedTargetClass;
   private Field nameField;

   @Before
   public void beforeEachTest() throws ClassNotFoundException, SecurityException, NoSuchFieldException {
      setUpResultSet();
      resultChecker = new RepositoryResultSetChecker( resultSet, TestEntity.class );
      expectedTargetClass = resultChecker.getCompilerClassLoader().loadClass( "com.processpuzzle.fitnesse.persistence.DefaultPersistentObjectWrapper" );
      nameField = expectedTargetClass.getDeclaredField( "name" );
   }
   
   @Test
   public void getTargetClass_IsSubclassOfPersistentObjectWrapper() {
      assertThat( resultChecker.getTargetClass().equals( expectedTargetClass ), is( true ));
   }
   
   @Test
   public void query_ReturnsArrayOfWrappedObjects() throws Exception {
      //SETUP: Implicit
      
      //EXCERCISE:
      Object[] entities = resultChecker.query(); 
      
      //VERIFY:
      assertThat( entities.length, equalTo( 2 ));
      assertThat( entities[0], instanceOf( PersistentObjectWrapper.class ));
      assertThat( entities[1], instanceOf( PersistentObjectWrapper.class ));
      
      assertThat( (String)nameField.get( entities[0] ), equalTo( "FirstEntity" ));
      assertThat( (String)nameField.get( entities[1] ), equalTo( "SecondEntity" ));
   }

   private void setUpResultSet() {
      firstEntity = new TestEntity( "FirstEntity" );
      secondEntity = new TestEntity( "SecondEntity" );
      List<TestEntity> entityList = Lists.newArrayList();
      entityList.add( firstEntity );
      entityList.add( secondEntity );
      resultSet = new SimpleResultSet<TestEntity>( entityList );
   }
}
