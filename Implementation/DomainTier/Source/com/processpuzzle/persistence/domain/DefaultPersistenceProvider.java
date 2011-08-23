package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.PersistenceProvider;
import hu.itkodex.commons.persistence.PersistentObject;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.commons.persistence.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.hibernate.StaleStateException;
import org.hibernate.TransientObjectException;
import org.hibernate.exception.DataException;

import com.processpuzzle.application.configuration.domain.PersistentDataInitializationStrategies;

public abstract class DefaultPersistenceProvider extends DefaultRepositoryEventHandler implements PersistenceProvider {
   protected Map<UnitOfWork, SessionContext> ongoingSessions = new HashMap<UnitOfWork, SessionContext>();

   // Constructors
   public DefaultPersistenceProvider( String name, HierarchicalConfiguration configuration, List<Class<?>> persistentClasses, PersistentDataInitializationStrategies databaseCreationStrategy ) {
      super( name, configuration, persistentClasses, databaseCreationStrategy );
   }

   // Public accessors
   public abstract <P extends PersistentObject> P findById( UnitOfWork work, Class<P> entityClass, Integer id );

   public abstract <P extends PersistentObject> RepositoryResultSet<P> findAll( UnitOfWork work, Class<P> entityClass );

   public abstract RepositoryResultSet<? extends PersistentObject> findByQuery( UnitOfWork work, Query query );

   public abstract Integer add( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity );

   public abstract void update( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity );

   public abstract void delete( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity );

   public abstract String getDriverClass();

   public abstract String getConnectionUrl();

   public abstract String getUserName();

   public abstract String getPassword();

   void closeSessionFor( DefaultUnitOfWork work ) {
      SessionContext sessionContext = ongoingSessions.get( work );
      try{
         sessionContext.close();
         finishSessionContext( sessionContext );
      }catch( TransientObjectException e ){
         throw new UnsavedObjectException( e );
      }catch( DataException e ){
         throw new PersistenceProviderException( "closeSessionFor", e );
      }catch( StaleStateException e ){
         throw new PersistenceProviderException( "closeSessionFor", e );
      }catch( Exception e ){
         e.printStackTrace();
      }finally{
         ongoingSessions.remove( work );
      }
   }

   void discardSessionFor( UnitOfWork work ) {
      SessionContext sessionContext = ongoingSessions.get( work );
      sessionContext.discard();
      finishSessionContext( sessionContext );
      ongoingSessions.remove( work );
   }

   // Protected, Private helper methods
   protected SessionContext getSessionContextFor( UnitOfWork work ) {
      if( ongoingSessions.containsKey( work ) )
         return ongoingSessions.get( work );
      else{
         SessionContext sessionContext = startNewSessionContext( work );
         sessionContext.open();
         ongoingSessions.put( work, sessionContext );
         work.registerPersistenceProvicer( this );
         return sessionContext;
      }
   }

   protected abstract SessionContext startNewSessionContext( UnitOfWork work );

   protected abstract void finishSessionContext( SessionContext context );

   // Getters and Setters

   // Obsolate methods
   // public abstract void replicate(String entityClass, Object entity, ReplicationMode mode);

   /*
    * public void addCollection( Object entity, String propertyName, Collection value ) { propertyName = Character.toUpperCase(propertyName.charAt(0)) +
    * propertyName.substring(1); try { Method getterMethod = entity.getClass().getMethod("get" + propertyName, (Class[]) null); Collection getterResults =
    * (Collection) getterMethod.invoke( entity, (Object[]) null ); if (getterResults != null) getterResults.addAll( value ); else {
    * entity.getClass().getMethod("set" + propertyName, new Class[] { Set.class }).invoke(entity, new Object[] { value }); } } catch (Exception e) {
    * e.printStackTrace(); } } public void setProperties(Object entity, Map properties) { for (Iterator propertiesIterator = properties.entrySet().iterator();
    * propertiesIterator.hasNext();) { Map.Entry propertiesEntry = (Map.Entry) propertiesIterator.next(); if (propertiesEntry.getValue() instanceof Collection)
    * { addCollection( entity, (String) propertiesEntry.getKey(), (Collection) propertiesEntry.getValue() ); } else { setProperty(entity, (String)
    * propertiesEntry.getKey(), propertiesEntry.getValue()); } } }
    */

   // protected abstract Object get(Class entityClass, Object id);
   // protected abstract Object get(String query, Map param);
   // protected Object get(String query, Object param[]) { return get(query, QLUtility.mapFor(param)); }
   // protected Object get(String query) { return get(query, Collections.EMPTY_MAP); }
   // public abstract Collection find(String query, Map param);
   // public Collection find(String query, Object param[]) { return find(query, QLUtility.mapFor(param)); }
   // public Collection find(String query) { return find(query, Collections.EMPTY_MAP); }
   // public Session open() { return null; }
   // public void close() {}
   // public void begin() {}
   // public void commit() {}
   // public void rollback() {}
   //
   // public Object getId(Object entity) {
   // try {
   // if (entity != null) { return entity.getClass().getMethod("getId", (Class[]) null).invoke(entity, (Object[]) null); }
   // } catch (Exception e) {
   // e.printStackTrace();
   // }
   // return null;
   // }
   //
   // public void setProperty(Object entity, String propertyName, Object value) {
   // propertyName = Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
   // try {
   // entity.getClass().getMethod("set" + propertyName, new Class[] { value != null ? value.getClass() : Object.class }).invoke(entity,
   // new Object[] { value });
   // } catch (Exception e) {
   // e.printStackTrace();
   // }
   // }
}
