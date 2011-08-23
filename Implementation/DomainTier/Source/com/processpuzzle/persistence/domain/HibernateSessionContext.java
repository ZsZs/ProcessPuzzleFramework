package com.processpuzzle.persistence.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientObjectException;
import org.hibernate.exception.DataException;

public class HibernateSessionContext extends SessionContext {
   private static Logger log = LoggerFactory.getLogger( HibernateSessionContext.class );
   private Session session = null;
   private Transaction transaction = null;

   HibernateSessionContext(SessionFactory sessionFactory) {
      session = sessionFactory.openSession();
   }
   
   @Override
   void open() {
      transaction = session.beginTransaction();
   }
   
   @Override
   void close(){
      try {
         transaction.commit();         
      } catch (DataException e) {
         log.error("Error occured during 'close()' action.", e);
         throw e;
      } catch (TransientObjectException e ) {
         log.error("One of the referenced aggregate roots was not saved or updated.", e);
         throw e;
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         session.close();         
      }
   }

   @Override
   void discard() {
      transaction.rollback();
      session.close();
   }

//Properties
   Session getSession() { return session; }

}
