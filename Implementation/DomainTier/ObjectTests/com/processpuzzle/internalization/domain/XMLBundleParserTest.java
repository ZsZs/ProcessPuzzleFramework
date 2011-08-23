package com.processpuzzle.internalization.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.internalization.domain.InternalizationException;
import com.processpuzzle.internalization.domain.InvalidResourceBundleException;
import com.processpuzzle.internalization.domain.NoneExistingResourceKeyException;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.internalization.domain.ResourceBundleIOException;
import com.processpuzzle.internalization.domain.ResourceCache;
import com.processpuzzle.internalization.domain.XMLBundleParser;

public class XMLBundleParserTest {
   public String resourcePath = "classpath:com/itcodex/objectpuzzle/framework/internalization/domain/TestResources_en.xml";
   public String invalidResourcePath = "classpath:com/itcodex/objectpuzzle/framework/internalization/domain/InvalidTestResources_en.xml";
   private XMLBundleParser parser = null;
   private ResourceCache cache = null;
   private ProcessPuzzleLocale targetLocale = new ProcessPuzzleLocale( "en" );

   @Before
   public void setUp() {
      parser = new XMLBundleParser();
      cache = new ResourceCache();
   }

   @After
   public void tearDown() {
      parser = null;
   }

   @Ignore
   @Test
   public void testParse_ForSuccess() {
      try {
         parser.parse( cache, resourcePath, targetLocale );
      } catch( Exception e ) {
         fail();
      }

      try {
         assertNotNull( cache.getResource( "Key_1", "String" ) );
      } catch( NoneExistingResourceKeyException e ) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @Test(expected = ResourceBundleIOException.class)
   public void testParse_ForNoneExistingFile() throws InternalizationException {
      parser.parse( cache, "NoneExistingFile", targetLocale );
   }

   @Ignore
   @Test(expected = InvalidResourceBundleException.class)
   public void testParse_ForInvalidFile() throws InternalizationException {
      parser.parse( cache, invalidResourcePath, targetLocale );
   }
}
