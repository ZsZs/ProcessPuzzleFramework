package com.processpuzzle.application.configuration.domain;

import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;

public class ProcessPuzzleContextNamespace implements NamespaceContext {

   @Override
   public String getNamespaceURI( String prefix ) {
      if( prefix.equals( "pp" ) )
         return "http://www.processpuzzle.com";
      else if( prefix.equals( "ac" ) )
         return "http://www.processpuzzle.com/ApplicationConfiguration";
      else if( prefix.equals( "bc" ) )
         return "http://www.processpuzzle.com/BeanContainerConfiguration";
      else if( prefix.equals( "bd" ) )
         return "http://www.processpuzzle.com/BusinessDefinitionsConfiguration";
      else if( prefix.equals( "bi" ) )
         return "http://www.processpuzzle.com/BusinessImplementationsConfiguration";
      else if( prefix.equals( "dl" ) )
         return "http://www.processpuzzle.com/DataLoadersConfiguration";
      else if( prefix.equals( "em" ) )
         return "http://www.processpuzzle.com/EmailConfiguration";
      else if( prefix.equals( "fc" ) )
         return "http://www.processpuzzle.com/FrontControllerConfiguration";
      else if( prefix.equals( "ge" ) )
         return "http://www.processpuzzle.com/GlobalElements";
      else if( prefix.equals( "in" ) )
         return "http://www.processpuzzle.com/InternationalizationConfiguration";
      else if( prefix.equals( "pr" ) )
         return "http://www.processpuzzle.com/PersistenceConfiguration";
      else if( prefix.equals( "rt" ) )
         return "http://www.processpuzzle.com/RuntimeConfiguration";
      else if( prefix.equals( "wui" ) )
         return "http://www.processpuzzle.com/WebUIConfiguration";
      else
         throw new IllegalArgumentException(prefix);
   }

   @Override
   public String getPrefix( String namespaceURI ) {
      if( namespaceURI.equals( "http://www.processpuzzle.com" )) return "pp";
      else if( namespaceURI.equals( "http://www.processpuzzle.com/ApplicationConfiguration" )) return "ac";
      else if( namespaceURI.equals( "http://www.processpuzzle.com/BeanContainerConfiguration" )) return "bc";
      else if( namespaceURI.equals( "http://www.processpuzzle.com/BusinessDefinitionsConfiguration" )) return "bd";
      else if( namespaceURI.equals( "http://www.processpuzzle.com/BusinessImplementationsConfiguration" )) return "bi";
      else if( namespaceURI.equals( "http://www.processpuzzle.com/DataLoadersConfiguration" )) return "dl";
      else if( namespaceURI.equals( "http://www.processpuzzle.com/EmailConfiguration" )) return "em";
      else if( namespaceURI.equals( "http://www.processpuzzle.com/FrontControllerConfiguration" )) return "fc";
      else if( namespaceURI.equals( "http://www.processpuzzle.com/GlobalElements" )) return "ge";
      else if( namespaceURI.equals( "http://www.processpuzzle.com/InternationalizationConfiguration" )) return "in";
      else if( namespaceURI.equals( "http://www.processpuzzle.com/PersistenceConfiguration" )) return "pr";
      else if( namespaceURI.equals( "http://www.processpuzzle.com/RuntimeConfiguration" )) return "rt";
      else if( namespaceURI.equals( "http://www.processpuzzle.com/WebUIConfiguration" )) return "wui";
      else throw new IllegalArgumentException( namespaceURI );
   }

   @Override
   public Iterator<String> getPrefixes( String namespaceURI ) {
      return null;
   }

}
