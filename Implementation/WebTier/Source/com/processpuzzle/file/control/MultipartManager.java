package com.processpuzzle.file.control;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;

//ez az osztaly ez miatt lett elkeszitve: http://www.jguru.com/faq/view.jsp?EID=1045507
//ha kell, atirhato mas megoldasra
public class MultipartManager {

   private HttpServletRequest request;
   private List<?> formItems;
   private boolean wasParse = false;

   public MultipartManager( HttpServletRequest request ) {
      this.request = request;
   }

   public boolean isMultipartContent() {
      // String contentType=request.getContentType();
      // if (contentType!=null &&
      // contentType.toLowerCase().indexOf("multipart/form-data")>-1)
      return FileUpload.isMultipartContent( request );
   }

   public void processRequest() {
      if( wasParse )
         return;

      DiskFileUpload upload = new DiskFileUpload();

      // setting up configuration
      // upload.setSizeThreshold(yourMaxMemorySize);
      // upload.setSizeMax(yourMaxRequestSize);
      // upload.setRepositoryPath("c:/temp");//!!!!!!!!

      try{
         formItems = upload.parseRequest( request );
         List<FileItem> fileItems = new Vector<FileItem>();
         Iterator<?> i = formItems.iterator();
         while( i.hasNext() ){
            FileItem item = (FileItem) i.next();
            if( !item.isFormField() ){ // file field
               fileItems.add( item );
            }
         }
         request.setAttribute( "mmFileItems", fileItems );
      }catch( FileUploadException e ){
         e.printStackTrace();
         // ezt is meg meg kell irni
      }

      wasParse = true;
   }

   public List<?> getFormItems() {
      if( wasParse )
         return formItems;
      else
         return null;
   }

   public String getParameter( String parameter ) {
      if( wasParse ){
         Iterator<?> i = formItems.iterator();
         while( i.hasNext() ){
            FileItem item = (FileItem) i.next();
            if( item.isFormField() ){ // it is work only for fields, not on
               // file items
               if( item.getFieldName().equals( parameter ) )
                  try{
                     return item.getString( "UTF-8" );
                  }catch( UnsupportedEncodingException e ){
                     // e.printStackTrace();
                     return item.getString();
                  }
            }
         }
      }
      return null;
   }

   public FileItem getFileItem( String parameter ) {
      if( wasParse ){
         Iterator<?> i = formItems.iterator();
         while( i.hasNext() ){
            FileItem item = (FileItem) i.next();
            if( !item.isFormField() ){ // it is work only for file items, not on
               // fields
               if( item.getFieldName().equals( parameter ) )
                  return item;
            }
         }
      }
      return null;
   }

   public static String getUTF8( String string ) {
      CharsetDecoder decoder = Charset.forName( "UTF-8" ).newDecoder();
      ByteBuffer byteBuffer = ByteBuffer.wrap( string.getBytes() );
      CharBuffer charBuffer = null;
      try{
         charBuffer = decoder.decode( byteBuffer );
      }catch( CharacterCodingException e ){
         return string;
      }
      return charBuffer.toString();
   }
}