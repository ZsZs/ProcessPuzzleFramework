package com.processpuzzle.artifact_type.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.artifact.domain.PrintView;

@XmlRootElement(name = "printViewType")
public class ArtifactPrintViewType extends ArtifactViewType {

	@XmlAttribute
	private String xmlToFoXsltPath;

	public ArtifactPrintViewType(String name, String viewClassName, String xsltPath) {
		super(name);
		this.presentationUri = "";
		this.viewClassName = viewClassName;
		this.xmlToFoXsltPath = xsltPath;
	}

	public ArtifactPrintViewType(String name, String viewClassName) {
		super(name);
		this.viewClassName = viewClassName;
	}

	public String getXmlToFoXsltPath() {
		return xmlToFoXsltPath;
	}
	
   @Override
   public String getViewClassName() {
      if( viewClassName == null ) viewClassName = PrintView.class.getName();
      return viewClassName;
   }
	   
    protected ArtifactPrintViewType() { }
}
