package com.processpuzzle.artifact_type.domain;

import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;


public class DocumentType extends ArtifactType {
   
	protected DocumentType(){}
    
	public DocumentType(String theName, ArtifactTypeGroup typeGroup) {
		super(theName, typeGroup);
	}

	public DocumentType(String theName) {
		this(theName, null);
	}
}