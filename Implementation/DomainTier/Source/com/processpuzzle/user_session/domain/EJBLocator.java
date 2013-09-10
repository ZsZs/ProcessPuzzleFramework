/*
Name: 
    - EJBLocator

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
///**
// * $Id: EJBLocator.java,v 1.3 2004/10/28 09:54:36 finn Exp $
// */
//
//package com.processpuzzle.framework.application_configuration.domain.business_delegate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.ejb.EJBHome;
//import javax.ejb.EJBLocalHome;
//import javax.naming.Context;
//import javax.naming.NamingException;
//import javax.rmi.PortableRemoteObject;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
///**
// * <a href="http://java.sun.com/blueprints/corej2eepatterns/Patterns/ServiceLocator.html">Service Locator </a> for
// * EJBs. This caches home interfaces for better performance so users should reuse instances. Is used by the
// * Business Delegates.
// */
//public class EJBLocator {
//    private static Log log = LogFactory.getLog(EJBLocator.class);
//
//    private Context jndiContext;
//    private Map homes = new HashMap();
//    private Map localhomes = new HashMap();
//
//    /**
//     * Initializes an EJBLocator. The jndiContext argument should be properly initialized to perform lookups, for
//     * example: <code>
//     * Properties env = new Properties();
//     * env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
//     * env.put(Context.PROVIDER_URL, "jnp://localhost:1099/");
//     * env.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
//     * Context jndiContext = new InitialContext(env);
//     * EJBLocator locator = new EJBLocator(jndiContext);
//     * </code>
//     * @param jndiContext
//     *            context to use for lookups.
//     */
//    public EJBLocator(Context jndiContext) {
//        this.jndiContext = jndiContext;
//    }
//
//    /**
//     * Performs a lookup for an EJBHome. Caches results for better performance.
//     * @param jndiName
//     *            the JNDI name of the EJBHome to lookup.
//     * @param homeClass
//     *            the class type of the EJBHome to lookup (for narrowing).
//     * @return an EJBHome
//     */
//    public synchronized EJBHome getHome(String jndiName, Class homeClass) {
//        log.debug("getHome(jndiName=" + jndiName + ", homeClass=" + homeClass.getName() + ")");
//        try {
//            EJBHome serviceHome = (EJBHome) homes.get(jndiName);
//            if (serviceHome == null) {
//                Object objref = jndiContext.lookup(jndiName);
//                serviceHome = (EJBHome) PortableRemoteObject.narrow(objref, homeClass);
//                homes.put(jndiName, serviceHome);
//            }
//            return serviceHome;
//        } catch (NamingException e) {
//            throw new RuntimeException("could not lookup home: " + jndiName, e);
//        }
//    }
//
//    /**
//     * Performs a lookup for an EJBLocalHome. Caches results for better performance.
//     * @param jndiName
//     *            the JNDI name of the EJBLocalHome to lookup.
//     * @return an EJBLocalHome
//     */
//    public synchronized EJBLocalHome getLocalHome(String jndiName) {
//        log.debug("getLocalHome(jndiName=" + jndiName + ")");
//        try {
//            EJBLocalHome serviceHome = (EJBLocalHome) localhomes.get(jndiName);
//            if (serviceHome == null) {
//                serviceHome = (EJBLocalHome) jndiContext.lookup(jndiName);
//                localhomes.put(jndiName, serviceHome);
//            }
//            return serviceHome;
//        } catch (NamingException e) {
//            throw new RuntimeException("could not lookup local home: " + jndiName, e);
//        }
//    }
//}
