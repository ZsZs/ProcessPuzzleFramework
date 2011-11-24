/*
Name: 
    - MemoEditableTextView

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

package com.processpuzzle.artifact.domain;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.TransformerException;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.EndTag;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;

import com.processpuzzle.application.resource.domain.XmlTransformer;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.domain.Person;

public class MemoEditableTextView extends EditableTextView<Memo> {

   public MemoEditableTextView( Memo artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String getHtmlSubject() {
      return null;
   }

   protected String retrieveObjectsAsXml() {
      MemoCustomFormView customFormView = (MemoCustomFormView) parentArtifact.getAvailableViews().get( "MemoCustomFormView" );

      StringBuffer memoXml = new StringBuffer();
      StringBuffer memoXsl = new StringBuffer();
      String transformedXml = null;

      memoXml.append( "<memo>" );
      memoXml.append( "<subject>" + customFormView.getSubject() + "</subject>" );
      memoXml.append( "<responsible>" + customFormView.getResponsible().getUserName() + "</responsible>" );
      memoXml.append( "<recipients>" );
      int index = 0;
      for( Iterator<?> iter = customFormView.getRecipients().iterator(); iter.hasNext(); ){
         Person p = (Person) iter.next();
         memoXml.append( "<recipient index='" + index++ + "'><name>" + p.getPartyName().getName() + "</name></recipient>" );
      }
      memoXml.append( "</recipients>" );
      memoXml.append( "<content>" + customFormView.getContent() + "</content>" );
      memoXml.append( "</memo>" );

      memoXsl.append( "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">" );
      memoXsl.append( "<xsl:output omit-xml-declaration=\"yes\" method=\"xml\" indent=\"yes\" encoding=\"UTF-8\"/>" );

      memoXsl.append( "<xsl:template match=\"/\">" );
      memoXsl.append( "<div>" );
      memoXsl.append( "<text>Tárgy:</text><span class=\"editable\" id=\"memo.subject\"><xsl:value-of select=\"memo/subject\"/></span><br/>" );
      memoXsl.append( "<text>Feladó:</text><span class=\"editable\" id=\"memo.responsible\"><xsl:value-of select=\"memo/responsible\"/></span><br/>" );
      memoXsl.append( "<text>Cimzett:</text><xsl:for-each select=\"memo/recipients/recipient\">"
            + "<span class=\"editable\" id=\"memo.recipient\"><xsl:value-of select=\"name\"/></span>" );
      memoXsl.append( "</xsl:for-each><br/>" );
      memoXsl.append( "<span class=\"editable\" id=\"memo.content\"><xsl:value-of select=\"memo/content\"/></span><br/>" );
      memoXsl.append( "</div>" );
      memoXsl.append( "</xsl:template>" );
      memoXsl.append( "</xsl:stylesheet>" );

      try{
         transformedXml = XmlTransformer.transform( memoXml.toString(), memoXsl.toString() );
      }catch( IOException e ){
         e.printStackTrace();
      }catch( TransformerException e ){
         e.printStackTrace();
      }
      // System.out.println("transformed: "+transformedXml);
      return transformedXml;
   }

   protected void updateObjectsFromXml( String data ) {

      String htmlSubject = null;
      String htmlResponsible = null;
      String htmlRecipients = null;
      String htmlContent = null;

      Source source = null;
      try{
         source = new Source( new StringReader( data ) );
      }catch( MalformedURLException e ){
         e.printStackTrace();
      }catch( IOException e ){
         e.printStackTrace();
      }
      List<?> editableElements = source.getAllElements( HTMLElementName.SPAN );
      for( Iterator<?> i = editableElements.iterator(); i.hasNext(); ){
         Element editableElement = (Element) i.next();
         String aClass = editableElement.getAttributeValue( "class" );
         String id = editableElement.getAttributeValue( "id" );
         if( aClass.equals( "editable" ) ){
            if( id.equals( "memo.content" ) ){
               htmlContent = editableElement.getContent().toString();
               System.out.println( "html:" + htmlContent );
               System.out.println( "text:" + editableElement.getTextExtractor().toString() );

               Source editableSource = null;

               try{
                  editableSource = new Source( new StringReader( htmlContent ) );
               }catch( IOException e ){
                  e.printStackTrace();
               }

               List<?> elementList = editableSource.getAllElements();
               for( Iterator<?> iterator = elementList.iterator(); iterator.hasNext(); ){
                  Element element = (Element) iterator.next();
                  StartTag sTag = element.getStartTag();
                  EndTag eTag = element.getEndTag();
                  // HtmlAttributeFormat attributeFormat = new
                  // HtmlAttributeFormat("content"));
                  System.out.println( element.getStartTag().getName() );

               }
            }
         }
      }
   }

   public void initializeView() {}

   public String getData( String method, Map parameters ) {
      return retrieveObjectsAsXml();
   }

   public void setContent( String data ) {
      System.out.println( "Data2:" + data );
      updateObjectsFromXml( data );
   }

}
