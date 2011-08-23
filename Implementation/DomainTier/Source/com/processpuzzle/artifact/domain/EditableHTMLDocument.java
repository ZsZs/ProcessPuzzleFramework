package com.processpuzzle.artifact.domain;

import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.DocumentType;

public class EditableHTMLDocument extends HTMLDocument {

	public EditableHTMLDocument(String name,DocumentType type, User responsible) {
      super(name, type, responsible);
   }

    private EditableHTMLDocument() {
          super();
    }

   private Set htmlTexts = new HashSet();

	public Set getHtmlTexts() {
		return htmlTexts;
	}
	public void setHtmlTexts(Set htmlTexts) {
		this.htmlTexts = htmlTexts;
	}
}