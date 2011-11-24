/*
Name: 
    - PropertyParser

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

package com.processpuzzle.application.resource.domain;

import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

public class PropertyParser {
  
    public static Properties decodeProperties(String param) {
       Properties props = new Properties();
       StringTokenizer tokenizer = new StringTokenizer(param, ";");
       while(tokenizer.hasMoreTokens()) {
          String token = tokenizer.nextToken();
          String key = token.substring(0, token.lastIndexOf("="));
          String value = token.substring(token.lastIndexOf("=")+1, token.length());
          props.put(key, value);
       }
       return props;
    }
    
    public static String encodeProperties(Properties props) {
       String ret = "";
       for (Iterator<?> iter = props.keySet().iterator(); iter.hasNext();) {
         String key = (String) iter.next();
         String value = props.getProperty(key);
         ret+=key+"="+value+";";
      }
       return ret;
    }

}
