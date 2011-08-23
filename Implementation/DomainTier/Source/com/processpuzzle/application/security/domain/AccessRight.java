/*
 * Created on May 18, 2006
 */
package com.processpuzzle.application.security.domain;

import hu.itkodex.commons.persistence.PersistentObject;

/**
 * @author zsolt.zsuffa
 */
public class AccessRight extends DefaultAccessRight implements PersistentObject {
//   private User targetUser = null;
   // must be unique
   private Integer controlledObjectId = null;
   private String controlledObjectClass = null;

   public AccessRight( AccessControlledObject controlledObject, boolean canRead, boolean canCreate, boolean canWrite, boolean canDelete) {
      super(canRead, canCreate, canWrite, canDelete);
//      this.targetUser = user;
      this.controlledObjectId = controlledObject.getId();
      this.controlledObjectClass = controlledObject.getClass().getName();
   }

   AccessRight( AccessControlledObject object) {
      this( object, false, false, false, false);
   }

   protected AccessRight() {}

//   public User getTargetUser() {
//      return targetUser;
//   }
//
//   public void setTargetUser(User targetUser) {
//      this.targetUser = targetUser;
//   }
//
   public Integer getControlledObjectId() { return controlledObjectId; }
   public String getControlledObjectClass() { return controlledObjectClass; }

//   public void setControlledObjectName(String controlledObjectName) {
//      this.controlledObjectId = controlledObjectName;
//   }
}
