/*
Name: 
    - ObjectFlow 

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
