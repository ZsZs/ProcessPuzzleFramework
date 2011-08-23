package com.processpuzzle.artifact_management.control;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.OpImage;
import javax.media.jai.RenderedOp;

import org.apache.commons.fileupload.FileItem;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.security.control.AuthorizationException;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFolder;
import com.processpuzzle.artifact.domain.ArtifactFolderRepository;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact.domain.FileStorage;
import com.processpuzzle.artifact.domain.ImageFile;
import com.processpuzzle.artifact.domain.ImageFileFactory;
import com.processpuzzle.file.control.FileServices;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.util.domain.GeneralService;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncodeParam;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;
import com.sun.media.jai.codec.SeekableStream;

public class AddRelatedArtifactCommand extends ArtifactViewCommand {
   public static final String ADD_RELATED_ARTIFACT_COMMAND_NAME = "AddRelatedArtifact";
   public static final String TARGETARTIFACT_NAME_PARAM = "targetArtifactName";
   public static final String TARGETARTIFACT_ID_PARAM = "targetArtifactId";
   public static final String TARGETFOLDER_PARAM = "targetFolder";
   public static final String UPLOADFORM_NAME_PARAM = "uploadFormName";
   public static final String JPEG_CONTENT_TYPE = "image/jpeg";
   public static final float scale = 0.2f;
   public static String UPLOADED_FILE_FOLDER;
   protected ImageFileFactory imageFileFactory;
   protected String targetArtifactName;
   protected String targetArtifactId;
   protected String targetFolderName;
   protected Artifact<?> targetArtifact = null;
   protected ArtifactFolder targetFolder = null;
   protected String uploadFormName = null;
   protected String docType = null;
   protected String docSubType = null;

   public void init( CommandDispatcher dispatcher ) {
      super.init( dispatcher );

      imageFileFactory = applicationContext.getEntityFactory( ImageFileFactory.class );

      UPLOADED_FILE_FOLDER = applicationContext.getProperty( ProcessPuzzleContext.UPLOADED_FILES_FOLDER );
      findTargetArtifact( dispatcher );
      try{
         addRelatedArtifact( dispatcher );
      }catch( IOException e ){
         e.printStackTrace();
      }
   }

   protected void retrieveRequestParameters( CommandDispatcher dispatcher ) {
      super.retrieveRequestParameters( dispatcher );
      targetArtifactName = (String) dispatcher.getProperties().getProperty( TARGETARTIFACT_NAME_PARAM );
      targetArtifactId = (String) dispatcher.getProperties().getProperty( TARGETARTIFACT_ID_PARAM );
      targetFolderName = (String) dispatcher.getProperties().getProperty( TARGETFOLDER_PARAM );

      uploadFormName = (String) dispatcher.getProperties().getProperty( UPLOADFORM_NAME_PARAM );

      docType = (String) dispatcher.getProperties().getProperty( "docType" );
      docSubType = (String) dispatcher.getProperties().getProperty( "docSubType" );
   }

   private void findTargetArtifact( CommandDispatcher dispatcher ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      DefaultArtifactRepository artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class );
      ArtifactFolderRepository artifactFolderRepository = applicationContext.getRepository( ArtifactFolderRepository.class );
      if( targetArtifactName != null && !targetArtifactName.equals( "" ) ){
         targetArtifact = artifactRepository.findByFullName( work, targetArtifactName );
      }
      if( (targetArtifactId != null) && (!targetArtifactId.equals( "" )) ){
         targetArtifact = artifactRepository.findById( work, new Integer( targetArtifactId ) );
      }else if( (targetFolderName != null) && (!targetFolderName.equals( "" )) ){
         targetFolder = (ArtifactFolder) artifactFolderRepository.findByPath( work, targetFolderName );
      }
      work.finish();
   }

   private ImageEncodeParam getJpegEncodeParam() {
      JPEGEncodeParam encodeParam = new JPEGEncodeParam();
      encodeParam.setQuality( 0.99f );
      encodeParam.setHorizontalSubsampling( 0, 1 );
      encodeParam.setHorizontalSubsampling( 1, 1 );
      encodeParam.setHorizontalSubsampling( 2, 1 );
      encodeParam.setVerticalSubsampling( 0, 1 );
      encodeParam.setVerticalSubsampling( 1, 1 );
      encodeParam.setVerticalSubsampling( 2, 1 );
      return encodeParam;
   }

   private ImageFile imageToJpeg( ImageFile sourceImage, String jpegPath ) {
      FileSeekableStream stream = null;
      try{
         stream = new FileSeekableStream( sourceImage.getSource() );
         String typeOfImage = GeneralService.getLastToken( sourceImage.getContentType(), "/" );
         String originalFileNameWithoutExtension = GeneralService.getFirstToken( sourceImage.getOriginalFileName(), "." );
         ImageDecoder dec = ImageCodec.createImageDecoder( typeOfImage, stream, null );
         RenderedImage image = dec.decodeAsRenderedImage( 0 );

         File outJpgFile = new File( jpegPath + "\\" + originalFileNameWithoutExtension + ".jpg" );
         OutputStream f = new FileOutputStream( outJpgFile );

         ImageEncoder jpgEnc = ImageCodec.createImageEncoder( "jpeg", f, getJpegEncodeParam() );
         jpgEnc.encode( image );
         f.flush();

         ImageFile jpegImage = imageFileFactory.createImageFile( originalFileNameWithoutExtension + ".jpg" );
         jpegImage.setOriginalFileName( originalFileNameWithoutExtension + ".jpg" );
         jpegImage.setSource( outJpgFile.getPath() );
         jpegImage.setContentType( JPEG_CONTENT_TYPE );
         jpegImage.setUploadDate( Calendar.getInstance().getTime() );
         jpegImage.setSize( new Long( outJpgFile.length() ) );
         return jpegImage;

      }catch( IOException e ){
         return null;
      }
   }

   private ImageFile createThumbnail( ImageFile jpegFile ) {
      try{
         InputStream is = new FileInputStream( jpegFile.getSource() );
         SeekableStream s = SeekableStream.wrapInputStream( is, true );
         RenderedOp objImage = JAI.create( "stream", s );
         ((OpImage) objImage.getRendering()).setTileCache( null );

         ParameterBlock pb = new ParameterBlock();
         pb.addSource( objImage ); // The source image
         pb.add( scale ); // The xScale
         pb.add( scale ); // The yScale
         pb.add( 0.0F ); // The x translation
         pb.add( 0.0F ); // The y translation
         pb.add( new InterpolationNearest() ); // The interpolation
         objImage = JAI.create( "scale", pb, null );

         String originalFileNameWithoutExtension = GeneralService.getFirstToken( jpegFile.getName(), "." );
         File outJpgThumbnail = new File( jpegFile.getSourcePath() + "\\" + originalFileNameWithoutExtension + "_thumbnail.jpg" );
         OutputStream ft = new FileOutputStream( outJpgThumbnail );

         ImageEncoder jpgEncThumbnail = ImageCodec.createImageEncoder( "jpeg", ft, getJpegEncodeParam() );
         jpgEncThumbnail.encode( objImage );
         ft.flush();

         ImageFile jpegImage = imageFileFactory.createImageFile( originalFileNameWithoutExtension + "_thumbnail.jpg" );
         jpegImage.setOriginalFileName( originalFileNameWithoutExtension + "_thumbnail.jpg" );
         jpegImage.setSource( outJpgThumbnail.getPath() );
         jpegImage.setContentType( JPEG_CONTENT_TYPE );
         jpegImage.setUploadDate( Calendar.getInstance().getTime() );
         jpegImage.setSize( new Long( outJpgThumbnail.length() ) );

         jpegImage.setIsInnerPicture( jpegFile.getIsInnerPicture() );
         jpegImage.setIsOuterPicture( jpegFile.getIsOuterPicture() );
         return jpegImage;
      }catch( FileNotFoundException e ){}catch( IOException e ){
         return null;
      }
      return null;
   }

   protected void addRelatedArtifact( CommandDispatcher dispatcher ) throws IOException {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      if( loggedInUser != null ){
         ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
         DefaultArtifactRepository artifactRepository = (DefaultArtifactRepository) applicationContext.getRepository( DefaultArtifactRepository.class );
         if( targetArtifact != null ){
            subjectArtifact.addRelatedArtifact( targetArtifact );
            artifactRepository.update( work, subjectArtifact );
         }else if( dispatcher.getRequest().getAttribute( "mmFileItems" ) != null ){
            List<?> mmFileItems = (List<?>) dispatcher.getRequest().getAttribute( "mmFileItems" );
            FileItem fileItem = FileServices.getFileItemByFieldName( uploadFormName, mmFileItems );
            if( fileItem != null ){
               if( targetFolder == null ){
                  targetFolder = (ArtifactFolder) artifactRepository.findById( work, subjectArtifact.getContainingFolder().getId() );
               }
               String path = dispatcher.getServletContext().getRealPath( UPLOADED_FILE_FOLDER + "\\" + targetFolder.getName() );
               FileStorage fileStorage = null;

               if( docType != null ){
                  if( docType.equals( "Picture" ) ){
                     String fileNameWithoutExtension = GeneralService.getFirstToken( GeneralService.getLastToken( fileItem.getName()
                           .toString(), "\\" ), "." );
                     String name = fileNameWithoutExtension + "_" + System.currentTimeMillis() + ".jpg";
                     fileStorage = imageFileFactory.createImageFile( name );
                     targetFolder.addChildArtifact( fileStorage );

                     FileServices.uploadFile( fileItem, name, loggedInUser, path, fileStorage );
                     if( fileItem.getContentType().indexOf( "image" ) != -1 ){
                        ImageFile jpegFile = (ImageFile) fileStorage;

                        if( docSubType.equals( "InnerPic" ) )
                           jpegFile.setIsInnerPicture( true );
                        if( docSubType.equals( "OuterPic" ) )
                           jpegFile.setIsOuterPicture( true );

                        if( fileStorage.getContentType().indexOf( "jpeg" ) == -1 ){
                           jpegFile = imageToJpeg( (ImageFile) fileStorage, path );
                           if( jpegFile != null ){
                              jpegFile.setContainingFolder( targetFolder );
                              artifactRepository.add( work, jpegFile );
                              fileStorage.addRelatedArtifact( jpegFile );
                           }
                        }
                        ImageFile thumbnailOfJpegImage = createThumbnail( jpegFile );
                        if( thumbnailOfJpegImage != null ){
                           thumbnailOfJpegImage.setContainingFolder( targetFolder );
                           artifactRepository.add( work, thumbnailOfJpegImage );
                           fileStorage.addRelatedArtifact( thumbnailOfJpegImage );
                        }
                     }
                     subjectArtifact.addRelatedArtifact( fileStorage );
                     artifactRepository.update( work, subjectArtifact );
                     artifactRepository.update( work, targetFolder );
                  }else if( docType.equals( "Document" ) ){
                     String fileNameWithoutExtension = GeneralService.getFirstToken( GeneralService.getLastToken( fileItem.getName()
                           .toString(), "\\" ), "." );
                     String name = fileNameWithoutExtension + "_" + System.currentTimeMillis() + "."
                           + GeneralService.getLastToken( GeneralService.getLastToken( fileItem.getName().toString(), "\\" ), "." );
                     fileStorage = FileStorage.create( name, loggedInUser );
                     targetFolder.addChildArtifact( fileStorage );

                     FileServices.uploadFile( fileItem, name, loggedInUser, path, fileStorage );

                     subjectArtifact.addRelatedArtifact( fileStorage );
                     artifactRepository.add( work, fileStorage );

                     artifactRepository.update( work, subjectArtifact );
                     artifactRepository.update( work, targetFolder );

                  }
               }
            }
            work.finish();
         }
      }else
         throw new AuthorizationException( loggedInUser.getUserName(), AddRelatedArtifactCommand.class.getSimpleName() );
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      return subjectArtifactView.getType().getPresentationUri();
   }

   protected void retrieveResponseDocument() {}

}