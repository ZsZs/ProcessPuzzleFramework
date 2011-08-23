package com.processpuzzle.user_session.domain;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;


/**
 * Utility for looking up factories from Spring. All factories are made avaiable
 * under the bean factory SPRING_BEAN_FACTORY and can be looked up using the
 * keys BEAN_XXX through standard Spring mechanisms. Rampart-common is packaged
 * with a default beanRefContext.xml file, which contains default implementations
 * for RequestContextFactory, Audit etc. To change the implementation, create a
 * new BEAN_CONTEXT xml file and make sure it precedes rampart-common.jar in the
 * classpath. Spring will then load your implementation instead.
 *
 * @author kkj
 * @since 13-01-2005
 * @version $Revision: 1.1.1.1 $
 */
public class ConfigHelper {
    public static final String SPRING_BEAN_FACTORY = "dk.rhos.fw.rampart.common.beanfactory";
    public static final String BEAN_REQUESTCONTEXTFACTORY = "RequestContextFactory";
    public static final String BEAN_AUDITOR = "Auditor";
    public static final String BEAN_CONTEXT = "beanRefContext.xml";

    private static BeanFactory beanFactory;

    static {
        BeanFactoryLocator bfl = ContextSingletonBeanFactoryLocator.getInstance(BEAN_CONTEXT);
        BeanFactoryReference bfr = bfl.useBeanFactory(SPRING_BEAN_FACTORY);
        beanFactory = bfr.getFactory();
    }

    /**
     * @return The currently loaded Spring bean factory
     */
    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * Get the named bean from the Spring bean factory
     * @param key The bean to lookup
     * @return The named bean
     */
    public static Object getBean(String key) {
        return beanFactory.getBean(key);
    }

    /**
     * @return The currently configured RequestContextFactory as loaded by Spring
     */
    public static UserRequestContextFactory getRequestContextFactory() {
        return (UserRequestContextFactory)beanFactory.getBean(BEAN_REQUESTCONTEXTFACTORY);
    }
}
