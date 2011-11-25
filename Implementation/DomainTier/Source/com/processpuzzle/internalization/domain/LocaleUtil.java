/*
Name: 
    - LocaleUtil

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
