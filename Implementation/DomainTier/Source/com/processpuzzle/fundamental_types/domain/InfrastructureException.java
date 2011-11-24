/*
Name: 
    - InfrastructureException

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

package com.processpuzzle.fundamental_types.domain;

import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 * This exception is used to mark (fatal) failures in infrastructure and system code.
 * <p>
 * Copyright (c) 1999-2007 Melloware, Inc. <http://www.melloware.com>
 * 
 * @author Emil A. Lefkof III <info@melloware.com>
 * @author Christian Bauer <christian@hibernate.org>
 * @version 4.0
 * @see org.apache.commons.lang.exception.NestableRuntimeException
 */
public class InfrastructureException extends NestableRuntimeException {
   private static final long serialVersionUID = -9203861055952253964L;

   public InfrastructureException() {
   // empty constructor
   }

   public InfrastructureException(String message) {
      super(message);
   }

   public InfrastructureException(Throwable cause) {
      super(cause);
   }

   public InfrastructureException(String message, Throwable cause) {
      super(message, cause);
   }
}
