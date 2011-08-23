/*
 * Created on Jul 2, 2006
 */
package com.processpuzzle.application.security.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

/**
 * @author zsolt.zsuffa
 */
public class AccessRightConstraintViolationException extends ProcessPuzzleProgrammingException {
   /**
	 * 
	 */
	private static final long serialVersionUID = -2221045569851306096L;
private static String [] descriptions = {
         "A user can't have more than one right for the same AccessControlledObject.",
         "An AccessControlledObject can't have more than one right for the same user."};
   private static String defaultMessagePattern = "AccessRight Constraint: ''{0}'' caused an error";
   private String userName = "";
   private Integer objectId = null;
   private int descriptionIndex;

   public AccessRightConstraintViolationException(int descriptionIndex, String userName, Integer objectId) {
      super( ExceptionHelper.defineMessage( 
            AccessRightConstraintViolationException.class,
            new Object[] {descriptionIndex, userName, objectId},
            defaultMessagePattern ));
      
      this.descriptionIndex = descriptionIndex;
      this.userName = userName;
      this.objectId = objectId;
   }
   
   public Integer getObjectId() {
      return objectId;
   }

   public String getUsername() {
      return userName;
   }

   public String getDescription () {
      return descriptions[descriptionIndex];
   }
}
