package com.processpuzzle.application.resource.domain;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SchemaLoader implements EntityResolver {
   public static final String FILE_SCHEME = "file://";

   public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
      if( systemId.startsWith( FILE_SCHEME )) {
         String filename = systemId.substring(FILE_SCHEME.length());
//         InputStream stream = SchemaLoader.class.getClassLoader().getResourceAsStream(filename);
//         InputStream stream = SchemaLoader.class.getClassLoader().getResourceAsStream(filename);
         ResourceLoader resourceLoader = new DefaultResourceLoader();
         Resource resource = resourceLoader.getResource( "classpath:" + filename );
         InputStream stream = resource.getInputStream();
         return new InputSource(stream);
      } else {
         return null;
      }
   }
}
