package com.processpuzzle.artifact_type.domain;

import hu.itkodex.commons.persistence.Entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class ArtifactMenu  extends GenericEntity<ArtifactMenu> implements Entity{
   private @XmlAttribute String name;
   private @XmlElement ArtifactMenuCommand command;

   public ArtifactMenuCommand getCommand() { return command; }
   public @Override <I extends DefaultIdentityExpression<ArtifactMenu>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   //Properties
   public String getName() { return name; }

   public void setCommand( ArtifactMenuCommand command) { this.command = command; }
   protected ArtifactMenu() {}

   protected @Override void defineIdentityExpressions() {
      // TODO Auto-generated method stub
   }
}
