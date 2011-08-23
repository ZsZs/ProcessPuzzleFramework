package com.processpuzzle.business.definition.domain;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.artifact.domain.Artifact;

@XmlAccessorType( XmlAccessType.FIELD ) 
@XmlType( name = "", propOrder = { "" })
@XmlRootElement( name = "businessImplementation" )
public class BusinessImplementation {
   public @XmlTransient Set<Artifact<?>> artifacts = new HashSet<Artifact<?>>(); //@XmlElementWrapper(name="artifacts") @XmlElement 
   private static Logger log = LoggerFactory.getLogger( BusinessImplementation.class );
   
   public BusinessImplementation() {
      log.debug( "BusinessImplementation was instantiated." );
   }
}
