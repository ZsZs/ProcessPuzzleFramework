/*
 * Created on Nov 28, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

/**
 * @author zsolt.zsuffa
 */
public class ObjectFlow {
   private Integer id = null;
   private String name = null;
   private ArtifactInstance subject = null;
   private ProtocolCallAction source = null;
   private OutputParameter sourceParameter = null;
   private ProtocolCallAction target = null;
   private InputParameter targetParameter = null;

   ObjectFlow( ArtifactInstance subject, ProtocolCallAction source, OutputParameter sourceParameter, ProtocolCallAction target, InputParameter targetParameter ) {
      this( null, subject, source, sourceParameter, target, targetParameter );
   }

   ObjectFlow( String name, ArtifactInstance subject, ProtocolCallAction source, OutputParameter sourceParameter,
         ProtocolCallAction target, InputParameter targetParameter ) {
      this.name = name;
      this.subject = subject;
      this.source = source;
      this.sourceParameter = sourceParameter;
      this.target = target;
      this.targetParameter = targetParameter;
      source.getOutputObjectFlows().add( this );
      target.getInputObjectFlows().add( this );
   }
   
   protected ObjectFlow() {
      //Needed by Hibernate.
   }

   // Setter and getter methods
   public Integer getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public ProtocolCallAction getSource() {
      return source;
   }

   public OutputParameter getSourceParameter() {
      return sourceParameter;
   }

   public ArtifactInstance getSubject() {
      return subject;
   }

   public ProtocolCallAction getTarget() {
      return target;
   }

   public InputParameter getTargetParameter() {
      return targetParameter;
   }
}
