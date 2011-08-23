package com.processpuzzle.artifact_type.domain;

import hu.itkodex.commons.persistence.Entity;

import javax.xml.bind.annotation.XmlAttribute;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class ArtifactMenuCommand  extends GenericEntity<ArtifactMenuCommand> implements Entity{
   private @XmlAttribute String name;
   private @XmlAttribute String description;
   private @XmlAttribute ArtifactCommandType type;
   private @XmlAttribute String actionStatement;
   private @XmlAttribute String customPrefix;

   ArtifactMenuCommand( String name ) {
      this.name = name;
   }
   
   protected ArtifactMenuCommand() {}

   //Public accessors
   public @Override <I extends DefaultIdentityExpression<ArtifactMenuCommand>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   //Properties
   public String getName() { return name; }

   public String getDescription() { return description; }
   public void setDescription(String description) { this.description = description; }

   public String getActionStatement() { return actionStatement; }
   public void setActionStatement(String actionStatement) { this.actionStatement = actionStatement; }

   public ArtifactCommandType getType() { return type; }
   public void setType( ArtifactCommandType type ) { this.type = type; }

   public String getCustomPrefix() { return customPrefix; }
   
   public enum ArtifactCommandType { CustomCommand, PrintActiveDocumentCommand, CloseActiveDocumentCommand, OpenDocumentSelectedInListCommand }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }
}
