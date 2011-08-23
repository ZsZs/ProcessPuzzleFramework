/*
 * Created on Jul 16, 2006
 */
package com.processpuzzle.artifact_type.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.artifact.domain.EditableTextView;

/**
 * @author zsolt.zsuffa
 */

@XmlRootElement(name = "editableTextViewType")
public class EditableTextViewType extends ArtifactViewType {

	@XmlAttribute
	private String xmlToHtmlXsltPath;

	public EditableTextViewType(String name, String presentationUri, String xsltPath) {
		super(name, presentationUri);
		this.xmlToHtmlXsltPath = xsltPath;
	}

	public String getXmlToHtmlXsltPath() {
		return xmlToHtmlXsltPath;
	}

	public void setXmlToHtmlXsltPath(String xmlToHtmlXsltPath) {
		this.xmlToHtmlXsltPath = xmlToHtmlXsltPath;
	}
	
   @Override
   public String getViewClassName() {
      if( viewClassName == null ) viewClassName = EditableTextView.class.getName();
      return viewClassName;
   }
   
   protected EditableTextViewType() { }
}
