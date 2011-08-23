/*
 * Created on Jul 2, 2006
 */
package com.processpuzzle.application.security.domain;

import com.processpuzzle.application.security.domain.AccessControlledObject;
import com.processpuzzle.application.security.domain.AccessRight;

/**
 * @author zsolt.zsuffa
 */
public class AnAccessControlledObject implements AccessControlledObject {
	private Integer id = null;

	private String name = null;

	public AnAccessControlledObject(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void addRight(AccessRight right) {
	}

	public void removeRight(AccessRight right) {
	}

	public void setName(String name) {
		this.name = name;
	}
}
