package com.processpuzzle.file.control;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.Document;
import com.processpuzzle.artifact.domain.FileStorage;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.util.domain.GeneralService;

public class FileServices {

   public static void uploadFile( FileItem item, String name, User responsible, String path, FileStorage fileStorage ) {
      File folder = new File( path );
      if( !folder.exists() )
         folder.mkdirs();
      if( item == null )
         fileStorage = null;

      String originalFileName = MultipartManager.getUTF8( GeneralService.getLastToken( item.getName().toString(), "\\" ) );

      String addedName = MultipartManager.getUTF8( name );
      String contentType = item.getContentType();
      long sizeInBytes = item.getSize();

      if( addedName == null || addedName.equals( "" ) )
         fileStorage = null;
      if( sizeInBytes == 0 )
         fileStorage = null;

      File uploadedFile = null;
      boolean succes = true;
      try{
         uploadedFile = new File( folder, addedName );
         item.write( uploadedFile );
      }catch( Exception e ){
         succes = false;
      }

      if( !succes )
         fileStorage = null;
      else{
         fileStorage.setOriginalFileName( originalFileName );
         fileStorage.setSource( uploadedFile.getPath() );
         fileStorage.setContentType( contentType );
         fileStorage.setUploadDate( Calendar.getInstance().getTime() );
         fileStorage.setSize( new Long( sizeInBytes ) );
      }
   }

   public static void deleteFile( FileStorage fileStorage ) {
      if( fileStorage == null )
         return;
      java.io.File dFile = new java.io.File( fileStorage.getSource() );
      if( dFile.delete() )
         ; /* the result is not intresting :) yet */
   }

   public static FileItem getFileItemByFieldName( String fieldName, Collection<?> fileItems ) {
      if( fieldName != null && fileItems != null ){
         Iterator<?> i = fileItems.iterator();
         while( i.hasNext() ){
            FileItem item = (FileItem) i.next();
            if( !item.isFormField() ){
               if( item.getFieldName().equals( fieldName ) )
                  return item;
            }
         }
      }
      return null;
   }

   public static FileItem getFileItemByName( String fileName, Collection<?> fileItems ) {
      if( (fileItems != null) && (!(fileItems.isEmpty())) ){
         Iterator<?> it = fileItems.iterator();
         boolean l = false;
         FileItem fitem = null;
         while( (it.hasNext()) && (!(l)) ){
            fitem = (FileItem) it.next();
            String thisName = (String) GeneralService.getLastToken( fitem.getName(), "\\" );
            if( thisName.equals( fileName ) ){
               l = true;
            }
         }
         if( l ){
            return fitem;
         }else
            return null;
      }else
         return null;
   }

   public static void changeStorageSource( Collection<?> documents, Collection<?> fileItems ) {
   /*
    * for (Iterator iter = fileItems.iterator(); iter.hasNext();) { FileItem fileItem = (FileItem) iter.next(); FileStorage fileStorage =
    * FileServices.uploadFile(fileItem); Document document; try { document = (Document)
    * GeneralService.findCollectionItemByFieldName(documents,"fileStorage.originalFileName", fileStorage.getOriginalFileName()); if (document != null) {
    * document.getFileStorage().setSource(fileStorage.getSource()); } } catch (SecurityException e) { e.printStackTrace(); } catch (IllegalArgumentException e)
    * { e.printStackTrace(); } catch (IllegalAccessException e) { e.printStackTrace(); } catch (NoSuchFieldException e) { e.printStackTrace(); } }
    */
   }

   public static Collection<?> updateDocuments( Collection<?> newDocuments, Collection<?> oldDocuments ) {
      Collection<?> unusedDocumentIds = new HashSet();
      /*
       * if ((newDocuments != null) && !(newDocuments.isEmpty()) && (oldDocuments != null) && !(oldDocuments.isEmpty())) { Collection oldOrderDocuments = new
       * HashSet(oldDocuments); for (Iterator orderDocumentIterator = oldOrderDocuments.iterator(); orderDocumentIterator.hasNext();) { Document document =
       * (Document) orderDocumentIterator.next(); try { if (GeneralService.findCollectionItemByFieldName(newDocuments, "fileStorage.originalFileName",
       * document.getFileStorage().getOriginalFileName()) == null) { unusedDocumentIds.add(document.getId().toString()); oldDocuments.remove(document); } }
       * catch (SecurityException e) { e.printStackTrace(); } catch (IllegalArgumentException e) { e.printStackTrace(); } catch (NoSuchFieldException e) {
       * e.printStackTrace(); } catch (IllegalAccessException e) { e.printStackTrace(); } } for (Iterator orderDocumentIterator = newDocuments.iterator();
       * orderDocumentIterator.hasNext();) { Document document = (Document) orderDocumentIterator.next(); try { if
       * (GeneralService.findCollectionItemByFieldName(oldDocuments, "fileStorage.originalFileName", document.getFileStorage().getOriginalFileName()) == null) {
       * oldDocuments.add(document); } } catch (SecurityException e) { e.printStackTrace(); } catch (IllegalArgumentException e) { e.printStackTrace(); } catch
       * (NoSuchFieldException e) { e.printStackTrace(); } catch (IllegalAccessException e) { e.printStackTrace(); } } } if ((newDocuments != null) &&
       * (newDocuments.isEmpty()) && (oldDocuments != null) && !(oldDocuments.isEmpty())) { Collection oldOrderDocuments = new HashSet(oldDocuments); for
       * (Iterator orderDocumentIterator = oldOrderDocuments.iterator(); orderDocumentIterator.hasNext();) { Document document = (Document)
       * orderDocumentIterator.next(); unusedDocumentIds.add(document.getId().toString()); oldDocuments.remove(document); } } if ((newDocuments != null) &&
       * !(newDocuments.isEmpty()) && (oldDocuments != null) && (oldDocuments.isEmpty())) { for (Iterator orderDocumentIterator = newDocuments.iterator();
       * orderDocumentIterator.hasNext();) { Document document = (Document) orderDocumentIterator.next(); oldDocuments.add(document); } }
       */
      return unusedDocumentIds;
   }

   public static Document removeDocument( Collection<?> documents, Collection<?> fileItems, String currentValue ) {
      Document document = null;
      if( fileItems != null ){
         FileItem fileItem = FileServices.getFileItemByName( currentValue, fileItems );
         fileItems.remove( fileItem );

         if( fileItem != null ){
            document = (Document) GeneralService.findCollectionItemByFieldName( documents, "fileStorage.originalFileName", currentValue );
         }else{
            document = (Document) GeneralService.findCollectionItemByFieldName( documents, "fileStorage.id", new Integer( currentValue ) );
         }
         documents.remove( document );
      }else{
         if( (documents != null) && !(documents.isEmpty()) ){
            if( (document = (Document) GeneralService.findCollectionItemByFieldName( documents, "fileStorage.id", new Integer( currentValue ) )) != null )
               documents.remove( document );
         }
      }
      return document;
   }

   public static FileStorage openDocument( Collection<?> fileItems, Collection<?> documents, String fileName ) {
      /*
       * if (fileItems != null) { FileItem fileItem = FileServices.getFileItemByName(fileName, fileItems); if (fileItem != null) return
       * FileServices.upLoadFile(fileItem); else return null; } else { return null; }
       */return null;
   }

   public static void deleteFilesFromServer( Collection<?> documents ) {
      for( Iterator<?> iter = documents.iterator(); iter.hasNext(); ){
         Document doc = (Document) iter.next();
         for( Iterator<?> versionIt = doc.getVersions().entrySet().iterator(); versionIt.hasNext(); ){
            /* DocumentVersion version = (DocumentVersion) */((Map.Entry) versionIt.next()).getValue();
            // deleteFile(version.getFileStorage());
         }
      }
   }

   public static void addDocument( Collection<?> documents, Collection<?> fileItems, FileItem fileItem, String documentName, Person creator, String versioned ) {
      if( !GeneralService.contains( GeneralService.getLastToken( fileItem.getName(), "\\" ), documents ) ){
         /*
          * FileStorage fileStorage = FileServices.upLoadFile(fileItem); if (fileStorage != null) { fileItems.add(fileItem); Document document =
          * ArtifactFactory.createDocument(documentName, creator); if( versioned != null ) { document.setVersionControlled(true); } documents.add(document);
          * FileServices.deleteFile(fileStorage); fileStorage.setId(null); }
          */
      }
   }
}