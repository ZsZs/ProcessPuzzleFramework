package com.processpuzzle.user_session.domain;

import java.io.Serializable;
import java.util.List;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.User;

/**
 * A generic application-wide session, which can store arbitrary
 * key, value pairs - as long as they're serializable. It is a
 * requirement that the transitive closure of keys and values
 * added to an ApplicationSession are Serializable.
 *
 * @author kkj
 * @since 17-01-2005
 * @version $revision: $
 */
public interface UserSession extends Serializable {

    /**
     * Store a value in the session under the specified key
     * @param key The key to identify the value
     * @param value Some value
     */
    void setAttribute(Serializable key, Serializable value);

    /**
     * Get the named attribute from the session.
     * @param key The key of the value to get
     * @return The value or null if no such value exists
     */
    Serializable getAttribute(Serializable key);

    /**
     * @return The list of known keys in the session
     */
    List keySet();
    
    public User getUser();
    public Application getApplication();
}
