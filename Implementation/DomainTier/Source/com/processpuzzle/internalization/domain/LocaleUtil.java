/*
 * =====================================================================
 * 
 * ResourceCache.java
 * 
 * Created by Claude Duguay Copyright (c) 2002
 * 
 * =====================================================================
 */

package com.processpuzzle.internalization.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class LocaleUtil {
   protected static final char DELIMITER = '_';

   public static String getFileName(ProcessPuzzleLocale locale, String prefix, String suffix, int depth) {
      StringBuffer buffer = new StringBuffer(prefix);
      if (depth > 0) {
         String language = locale.getLanguage();
         if (!language.equals("")) {
            buffer.append(DELIMITER);
            buffer.append(language);
         }
      }
      if (depth > 1) {
         String country = locale.getCountry();
         if (country != null &&!country.equals("")) {
            buffer.append(DELIMITER);
            buffer.append(country);
         }
      }
      if (depth > 2) {
         String variant = locale.getVariant();
         if (variant != null &&!variant.equals("")) {
            buffer.append(DELIMITER);
            buffer.append(variant);
         }
      }
      return buffer.toString() + suffix;
   }

   public static String[] getFileNameList(ProcessPuzzleLocale locale, String prefix, String suffix) {
      List<String> list = new ArrayList<String>();
      for (int i = 0; i < 3; i++) {
         String filename = getFileName(locale, prefix, suffix, i);
         if (!list.contains(filename)) {
            list.add(filename);
         }
      }
      String[] array = new String[list.size()];
      for (int i = 0; i < array.length; i++) {
         array[i] = (String) list.get(i);
      }
      return array;
   }

   public static String[] splitName(String name) {
      StringTokenizer tokenizer = new StringTokenizer(name, "" + DELIMITER, false);
      int count = tokenizer.countTokens();
      String[] array = new String[count];
      for (int i = 0; i < count; i++) {
         array[i] = tokenizer.nextToken();
      }
      return array;
   }
}
