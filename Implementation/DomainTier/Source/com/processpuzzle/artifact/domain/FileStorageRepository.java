package com.processpuzzle.artifact.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;

public class FileStorageRepository extends GenericRepository<FileStorage> {

   public FileStorageRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public FileStorage findFileStorageById( DefaultUnitOfWork work, Integer id ) {
      return (FileStorage) super.findById( work, FileStorage.class, id );
   }
}
