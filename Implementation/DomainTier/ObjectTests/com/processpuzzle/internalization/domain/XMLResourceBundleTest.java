/*
 * =====================================================================
 * 
 * XMLResourceBundleTest.java
 * 
 * Created by Claude Duguay Copyright (c) 2002
 * 
 * =====================================================================
 */

package com.processpuzzle.internalization.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.internalization.domain.InternalizationException;
import com.processpuzzle.internalization.domain.InvalidResourcePathException;
import com.processpuzzle.internalization.domain.NoneExistingResourceKeyException;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.internalization.domain.XMLResourceBundle;

public class XMLResourceBundleTest {
   protected static final ProcessPuzzleLocale HUNGARIAN = new ProcessPuzzleLocale("hu");
   protected static final ProcessPuzzleLocale ENGLISH = new ProcessPuzzleLocale("en");
   protected static final ProcessPuzzleLocale ENGLISH_USA = new ProcessPuzzleLocale("en", "US");
   protected static final ProcessPuzzleLocale SPANISH = new ProcessPuzzleLocale("es");
   protected static final ProcessPuzzleLocale PORTUGUESE = new ProcessPuzzleLocale("pt");
   protected static final ProcessPuzzleLocale PORTUGUESE_BRAZIL = new ProcessPuzzleLocale("pt", "BR");
   protected static final String RESOURCE_PATH = "classpath:com/itcodex/objectpuzzle/framework/internalization/domain/TestResources";
   protected static final String ANOTHER_RESOURCE_PATH = "classpath:com/itcodex/objectpuzzle/framework/internalization/domain/AnotherTestResources";
   protected XMLResourceBundle bundle = null;

   @Before
   public void setUp() {
      bundle = new XMLResourceBundle( RESOURCE_PATH );
   }

   @After
   public void tearDown() {
      bundle = null;
   }

   @Ignore
   @Test
   public void testLoadFile_ForHuLocale() throws InvalidResourcePathException, InternalizationException {
      bundle.loadResources( HUNGARIAN );
      assertEquals("TestResources.xml defines value:", "Szöveg_5", bundle.getText("Kulcs_5"));
   }
   
   @Ignore
   @Test
   public void testLoadFile_ForMultipleResources() throws InvalidResourcePathException, InternalizationException {
      bundle = new XMLResourceBundle( RESOURCE_PATH + ";" + ANOTHER_RESOURCE_PATH );
      bundle.loadResources( HUNGARIAN );
      assertEquals("TestResources.xml defines value:", "Szöveg_5", bundle.getText("Kulcs_5"));
      assertEquals("AnotherTestResources.xml defines value:", "Szöveg_55", bundle.getText("Kulcs_55"));
   }
   
   @Test ( expected=InvalidResourcePathException.class ) 
   public void testLoadFile_ForException() throws InvalidResourcePathException, InternalizationException {
      bundle = new XMLResourceBundle( "NonExistingFile" );
      bundle.loadResources( ENGLISH );
   }
   
   @Ignore
   @Test
   public void getText_ForEnUsLocale () throws InvalidResourcePathException, InternalizationException {
      bundle.loadResources( ENGLISH_USA );
      assertEquals("'TextResource_en.xml' file defines value: ", "Text_1", bundle.getText("Key_1"));
      
      //Note that 'TextResource.xml' also defines 'Text_2' value for key 'Key_2' but the more specific 'TextResources_en.xml' overwrites it.
      assertEquals("'TextResource_en.xml' file overwrites value: ", "Overwritten value 2", bundle.getText("Key_2"));      

      //Note that 'TextResource_en.xml' 'Text_3' value for key 'Key_3' whithin the context of language, but Country context is more specific 'TextResources_en.xml' overwrites it. 
      assertEquals("Country specific value overwrites the value: ", "Overwritten value 3", bundle.getText("Key_3"));      

      //Note that 'TextResource_en.xml' also defines 'Text_4' value for key 'Key_4' but the more specific 'TextResources_en_US.xml' overwrites it.
      assertEquals("Country specific value overwrites the value: ", "Overwritten value 4", bundle.getText("Key_4"));      

      //Note that value for 'Text_5' is only defined in 'TextResources.xml'
      assertEquals("'TextResource.xml' file defines value: ", "Text_5", bundle.getText("Key_5"));
   }
   
   @Ignore
   @Test
   public void getText_ForHuLocale () throws InvalidResourcePathException, InternalizationException {
      bundle.loadResources( HUNGARIAN );
      assertEquals("'TextResource_hu.xml' file defines value: ", "Szöveg_1", bundle.getText("Kulcs_1"));
      assertEquals("'TextResource.xml' file defines value: ", "Szöveg_5", bundle.getText("Kulcs_5"));
   }
   
   @Ignore
   @Test ( expected=NoneExistingResourceKeyException.class ) 
   public void getText_ForException () throws InvalidResourcePathException, InternalizationException {
      bundle.loadResources( HUNGARIAN );
      bundle.getText( "None existing key" );
   }
}