package com.processpuzzle.sharedfixtures.domaintier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hsqldb.Server;
import org.hsqldb.server.ServerConstants;

import com.processpuzzle.fundamental_types.domain.InfrastructureException;
import com.processpuzzle.application.domain.SingletonRegistry;

public class HSQLTestDataBase {
   private static Server server;
   private static final Log log = LogFactory.getLog( HSQLTestDataBase.class );
   private boolean isRunning = false;
   
   public static HSQLTestDataBase getInstance() {
      return (HSQLTestDataBase) SingletonRegistry.getInstance( HSQLTestDataBase.class );
   }

   public void setUp() {
      if( !isRunning ) {
         startupHSQLDB( "test_db", "test_db" );
         isRunning = true;
      }
   }
   
   public void tearDown() {
      if( isRunning ) {
         shutdownHSQLDB();
         isRunning = false;
      }
   }
   
   /**
    * Starts the HSQLDB server in process at the location specified.
    * 
    * @param location
    *           the location to create the new database on disk
    * @param alias
    *           the alias to give the DB
    */
   private static void startupHSQLDB(final String location, final String alias) {
      try {
         log.info("Starting up HSQLDB Server.");
         // jdbcUrl = DATABASE_URL + alias;
         server = new Server();

         if (log.isInfoEnabled()) {
            log.info("Location: " + location);
            log.info(server.getProductName() + " " + server.getProductVersion());
         }

         server.setDatabaseName(0, alias);
         server.setDatabasePath(0, "file:" + location + ";runtime.gc_interval=10000;hsqldb.default_table_type=cached;");
         server.setLogWriter(null);
         server.setErrWriter(null);
         server.setSilent(true);
         server.setTrace(false);
         server.start();

         System.getProperties().put("db.product", server.getProductName());
         System.getProperties().put("db.version", server.getProductVersion());
         System.getProperties().put("db.address", server.getAddress());

      } catch (RuntimeException ex) {
         log.error(ex.getMessage(), ex);
         throw new InfrastructureException(ex);
      }
   }
   
   /**
    * Shuts down the HSQLDB that is running in process.
    */
   private static void shutdownHSQLDB() {
      try {
         if (server != null) {
            log.info("Shutting down HSQLDB Server.");
            server.stop();
            while (server.getState() != ServerConstants.SERVER_STATE_SHUTDOWN) {
               try {
                  log.debug("Sleeping waiting for server to stop");
                  Thread.sleep(100);
               } catch (InterruptedException e) {
                  log.debug("Interrupted");
               }
            }
            server = null;
         }

      } catch (RuntimeException ex) {
         log.error(ex.getMessage(), ex);
         throw new InfrastructureException(ex);
      }
   }

}
