/*
Name: 
    - Modification

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

package com.processpuzzle.artifact.domain;

import java.util.Date;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class Modification extends GenericEntity<Modification> implements Comparable<Object> {
   private String status;
   private String comment;
   private TimePeriod modificationPeriod;
   private ArtifactVersion sourceVersion;
   private ArtifactVersion targetVersion;
   private User modifier;

//Public constructors
   public Modification(ArtifactVersion source, User modifier, String comment) {
      if ((source != null) && (modifier != null)) {
         this.sourceVersion = source;
         this.targetVersion = (ArtifactVersion) source.clone();
         this.comment = comment;
         this.modifier = modifier;
         this.modificationPeriod = new TimePeriod( new TimePoint( new Date(System.currentTimeMillis())), null);
         this.sourceVersion.setNextModification(this);
         this.targetVersion.setPreviousModification(this);
         this.targetVersion.setResponsible(modifier);
      } else {
         this.sourceVersion = null;
         this.targetVersion = null;
         this.comment = null;
         this.modifier = null;
         this.modificationPeriod = null;
      }
   }

   protected Modification() {
      super();
   }

// Public accessors.
   public int compareTo(Object o) {
      Modification other = (Modification) o;
      int c;
      if ((c = (this.modificationPeriod.compareTo(other.getModificationPeriod()))) != 0)
         return c;
      if ((c = (this.comment.compareTo(other.getComment()))) != 0)
         return c;
      if ((c = (this.status.compareTo(other.getStatus()))) != 0)
         return c;
      return 0;
   }

// Public mutator methods.
   public void reserve( User modifier) {
      this.targetVersion = (ArtifactVersion) this.sourceVersion.clone();
      this.targetVersion.setPreviousModification(this);
      this.targetVersion.setNextModification(null);
      this.modificationPeriod = new TimePeriod( new TimePoint(new Date(System.currentTimeMillis())), null); 
      this.targetVersion.setResponsible( modifier );
      this.setModifier( modifier );
   }

   public void release() {
      //this.targetVersion.setId(sourceVersion.getId());
      this.sourceVersion = this.targetVersion;
      this.sourceVersion.setNextModification(this);
      this.sourceVersion.setPreviousModification(null);
      this.targetVersion = null;
      this.modificationPeriod = new TimePeriod( null, new TimePoint(new Date(System.currentTimeMillis()))); 
   }
   
// Getters, setters
   public void setBegin( TimePoint begin ) {
      this.modificationPeriod = new TimePeriod( begin, this.modificationPeriod.getEnd() );       
   }

   public void setEnd( TimePoint end ) {
      this.modificationPeriod = new TimePeriod( this.modificationPeriod.getBegin(), end );       
   }

   public String getComment() { return comment; }

   public void setComment(String comment) { this.comment = comment; }

   public String getStatus() { return status; }

   public void setStatus(String status) { this.status = status; }

   public User getModifier() { return modifier; }

   public void setModifier( User modifier) { this.modifier = modifier; }

   public ArtifactVersion getSourceVersion() { return sourceVersion; }

   public void setSourceVersion(ArtifactVersion sourceVersion) { this.sourceVersion = sourceVersion; }

   public ArtifactVersion getTargetVersion() { return targetVersion; }

   public void setTargetVersion(ArtifactVersion targetVersion) { this.targetVersion = targetVersion; }

   public TimePeriod getModificationPeriod() { return modificationPeriod; }

   public void setModificationPeriod( TimePeriod modificationPeriod ) { this.modificationPeriod = modificationPeriod; }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<Modification>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}
