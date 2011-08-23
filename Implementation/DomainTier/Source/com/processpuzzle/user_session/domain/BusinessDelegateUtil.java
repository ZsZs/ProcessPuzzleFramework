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
