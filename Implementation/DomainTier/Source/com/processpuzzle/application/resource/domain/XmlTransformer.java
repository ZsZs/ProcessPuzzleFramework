package com.processpuzzle.application.resource.domain;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XmlTransformer {

   public static String transform(String xml, InputStream xslt) throws IOException, TransformerException {
      StringWriter out = new StringWriter();
      
      try {
         TransformerFactory factory = TransformerFactory.newInstance();
         Transformer transformer = factory.newTransformer(new StreamSource(xslt));

         Source src = new StreamSource(new StringReader(xml));
         Result res = new StreamResult(out);
         
         transformer.transform(src, res);
         return out.toString();
      } finally {
         out.close();
      }
   }
   
   public static String transform(String xml, String xslt) throws IOException, TransformerException {
      StringWriter out = new StringWriter();
      
      try {
         TransformerFactory factory = TransformerFactory.newInstance();
         Transformer transformer = factory.newTransformer(new StreamSource(new StringReader(xslt)));

         Source src = new StreamSource(new StringReader(xml));
         Result res = new StreamResult(out);
         
         transformer.transform(src, res);
         return out.toString();
      } finally {
         out.close();
      }
   }
}
