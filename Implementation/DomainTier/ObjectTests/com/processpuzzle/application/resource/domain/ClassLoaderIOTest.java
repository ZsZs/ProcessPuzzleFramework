package com.processpuzzle.application.resource.domain;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.resource.domain.ClassLoaderIO;

public class ClassLoaderIOTest {
   private static String FILE_NAME_WITH_SLASHES = "com/itcodex/objectpuzzle/framework/resource_management/domain/simpleTextFile.txt";
   private static String FILE_NAME_WITH_TRAINLING_SLASH = "/com/itcodex/objectpuzzle/framework/resource_management/domain/simpleTextFile.txt";
   private static ClassLoaderIO ioEnvironment = null;
   
   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
      ioEnvironment = new ClassLoaderIO(ClassLoader.getSystemClassLoader());
   }

   @AfterClass
   public static void tearDownAfterClass() throws Exception {}

   @Before
   public void setUp() throws Exception {}

   @After
   public void tearDown() throws Exception {}

   @Ignore
   @Test
   public void testInputStream() {
      assertTrue( "inputStream returns an ", ioEnvironment.inputStream( FILE_NAME_WITH_SLASHES ) instanceof InputStream );
      assertTrue("inputStream returns an ", ioEnvironment.inputStream(FILE_NAME_WITH_TRAINLING_SLASH) instanceof InputStream );
   }

   @Ignore
   @Test
   public void testGetResource() {
      fail("Not yet implemented");
   }

   @Ignore
   @Test
   public void testGetRealPath() {
      fail("Not yet implemented");
   }

}
