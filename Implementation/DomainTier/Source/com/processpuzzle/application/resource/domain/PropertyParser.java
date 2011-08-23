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
