package com.processpuzzle.artifact.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;

public class EditableHTMLDocumentRepository extends GenericRepository<EditableHTMLDocument> {

   public EditableHTMLDocumentRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public EditableHTMLDocument findEditableHTMLDocumentById( DefaultUnitOfWork work, String id ) {
      return (EditableHTMLDocument) findById( work, EditableHTMLDocument.class, new Integer( id ) );
   }

   public void updateEditableHTMLDocument( DefaultUnitOfWork work, EditableHTMLDocument editableHTMLDocument ) {
      update( work, EditableHTMLDocument.class, editableHTMLDocument );
   }
}
