package com.processpuzzle.artifact.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.fundamental_types.domain.ServiceMessage;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement( name="FindByIdRequest", namespace="http://www.processpuzzle.com" )
public class FindArtifactRequest extends ServiceMessage{
   private @XmlElement( name="id", required = true, namespace="http://www.processpuzzle.com" ) String id;
   private @XmlElement( name="requestedView" ) String requestedViewName;

   protected FindArtifactRequest() {}
   
   public FindArtifactRequest( String artifactId ) {
      this( artifactId, null );
   }
   
   public FindArtifactRequest( String artifactId, String requestedViewName ) {
      this.id = artifactId;
      this.requestedViewName = requestedViewName;
   }
   
   public Integer getId() { return new Integer( id ); }
   public String getRequestedViewName() { return requestedViewName; }
   void setRequestedViewName( String requestedViewName ) { this.requestedViewName = requestedViewName; }
}
