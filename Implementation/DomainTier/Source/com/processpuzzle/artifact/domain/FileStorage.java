package com.processpuzzle.artifact.domain;

import java.util.Date;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class FileStorage extends Artifact<FileStorage> {

   public static FileStorage create( String fileName, User responsible ) {
      ArtifactTypeRepository artifactTypeRepository = (ArtifactTypeRepository) ProcessPuzzleContext.getInstance().getRepository(ArtifactTypeRepository.class);
      DefaultUnitOfWork work=new DefaultUnitOfWork(true);
      ArtifactType type = artifactTypeRepository.findArtifactTypeByName(work,"FileStorage");
      work.finish();
      return new FileStorage(fileName, type, responsible);
   }
   
   protected FileStorage() {
      super();
   }

   public FileStorage(String name, ArtifactType artifactType, User responsible) {
      super(name, artifactType, responsible);
   }

   public String getSourceName() {
      return (new java.io.File(((FileVersion)latest()).getSource())).getName();
   }

   public String getSourcePath() {
      return (new java.io.File(((FileVersion)latest()).getSource())).getParent();
   }

   public String getContentType() {
      return ((FileVersion)latest()).getContentType();
   }
   
   public void setContentType(String contentType) {
      ((FileVersion)latest()).setContentType(contentType);
   }

   public String getOriginalFileName() {
      return ((FileVersion)latest()).getOriginalFileName();
   }

   public void setOriginalFileName(String originalFileName) {
      ((FileVersion)latest()).setOriginalFileName(originalFileName);
   }

   public Long getSize() {
      return ((FileVersion)latest()).getSize();
   }

   public void setSize(Long size) {
      ((FileVersion)latest()).setSize(size);
   }

   public String getSource() {
      return ((FileVersion)latest()).getSource();
   }

   public void setSource(String source) {
      ((FileVersion)latest()).setSource(source);
   }

   public Date getUploadDate() {
      return ((FileVersion)latest()).getUploadDate();
   }

   public void setUploadDate(Date uploadDate) {
      ((FileVersion)latest()).setUploadDate(uploadDate);
   }

   public String getAsXml() {
      return super.getAsXml(FileStorage.class, this);
   }

   protected void instantiateVersion(String name, ArtifactType type, User responsible) {
      getVersions().put(new Integer(versions.size()+1), new FileVersion( name, responsible, type ));
   }

   @Override
   public DefaultIdentityExpression getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}
