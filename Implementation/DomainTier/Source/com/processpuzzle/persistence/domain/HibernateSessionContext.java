/*
Name: 
    - HibernateSessionContext 

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
