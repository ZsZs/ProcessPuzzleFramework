package com.processpuzzle.application.resource.domain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FormattingResults;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.apps.PageSequenceResults;

public class FoToPdfConverter {

   public static ByteArrayOutputStream convertFO2PDF( String fo ) throws IOException, FOPException {

      FopFactory fopFactory = FopFactory.newInstance();

      ByteArrayOutputStream out = null;

      try{
         FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
         out = new ByteArrayOutputStream();

         Fop fop = fopFactory.newFop( MimeConstants.MIME_PDF, foUserAgent, out );

         TransformerFactory factory = TransformerFactory.newInstance();
         Transformer transformer = factory.newTransformer();

         Source src = new StreamSource( new StringReader( fo ) );

         Result res = new SAXResult( fop.getDefaultHandler() );

         transformer.transform( src, res );

         FormattingResults foResults = fop.getResults();
         java.util.List pageSequences = foResults.getPageSequences();
         for( java.util.Iterator it = pageSequences.iterator(); it.hasNext(); ){
            PageSequenceResults pageSequenceResults = (PageSequenceResults) it.next();
            System.out.println( "PageSequence " + (String.valueOf( pageSequenceResults.getID() ).length() > 0 ? pageSequenceResults.getID() : "<no id>")
                  + " generated " + pageSequenceResults.getPageCount() + " pages." );
         }
         System.out.println( "Generated " + foResults.getPageCount() + " pages in total." );

      }catch( Throwable e ){
         e.printStackTrace( System.err );
         System.exit( -1 );
      }finally{
         out.close();
      }
      return out;
   }

}
