/*
Name: 
    - BusinessDelegateUtil 

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

package com.processpuzzle.user_session.domain;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility methods for XDoclet generated business delegates.
 * 
 * @author kkj
 * @since 31-08-2004 10:14:23
 * @version $Revision: 1.1.1.1 $
 */
public class BusinessDelegateUtil {
   private static final Logger log = LoggerFactory.getLogger( BusinessDelegateUtil.class );

   /** Use the local EJB interface to invoke methods through delegate */
   public final static DelegateType TYPE_LOCAL = new DelegateType( "LOCAL" );

   /** Use the remote EJB interface to invoke methods through delegate */
   public final static DelegateType TYPE_REMOTE = new DelegateType( "REMOTE" );

   /**
    * Typed constant for business delegate types
    */
   public static class DelegateType {
      private String type;

      private DelegateType( String type ) {
         this.type = type;
      }

      public String toString() {
         return "DelegateType " + type;
      }
   }

   /**
    * Unwrap a RemoteException and throw the actual exception inside.
    * 
    * @param e
    *           The RemoteException to unwrap
    * @throws RuntimeException
    *            if RemoteException.detail instanceof RuntimeException
    * @throws Error
    *            if RemoteExceptioin.detail instanceof Error
    */
   public static void throwActualException( RemoteException e ) {
      log.debug( "throwActualException: " + e.getClass().getName() );
      Throwable actual = (Throwable) e.detail;
      while( actual instanceof RemoteException ){
         actual = ((RemoteException) actual).detail;
      }
      if( actual instanceof RuntimeException ){
         throw (RuntimeException) actual;
      }else if( actual instanceof Error ){
         throw (Error) actual;
      }else{
         throw new RuntimeException( actual );
      }
   }
}
