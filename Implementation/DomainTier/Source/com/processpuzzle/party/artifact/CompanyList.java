package com.processpuzzle.party.artifact;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact_type.domain.ArtifactType;

@XmlType( name = "CompanyList" )
@XmlRootElement( name = "companyList" )
public class CompanyList extends ArtifactList<CompanyList> {

   protected CompanyList() {
      super();
   }

   public CompanyList( String name, ArtifactType type, User creator ) {
      super( name, type, creator );
   }
}
