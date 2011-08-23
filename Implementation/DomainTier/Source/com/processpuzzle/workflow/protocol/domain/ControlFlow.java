/*
 * Created on Nov 28, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

/**
 * @author zsolt.zsuffa
 */
public class ControlFlow {
   private Integer id = null;
   private String name = null;
   private ProtocolCallAction source = null;
   private ProtocolCallAction target = null;
   
   public ControlFlow( ProtocolCallAction source, ProtocolCallAction target ) {
      this( null, source, target);
   }

   public ControlFlow( String name, ProtocolCallAction source, ProtocolCallAction target ) {
      this.name = name;
      this.source = source;
      this.target = target;
   }
   
   protected ControlFlow() {
      //Nedded by Hibernate
   }

   public Integer getId() { return id; }
   public String getName() { return name; }
   public ProtocolCallAction getSource() { return source; }
   public ProtocolCallAction getTarget() { return target; }
}
