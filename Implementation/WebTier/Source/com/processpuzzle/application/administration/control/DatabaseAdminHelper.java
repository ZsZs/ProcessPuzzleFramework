package com.processpuzzle.application.administration.control;

import java.util.ArrayList;

public class DatabaseAdminHelper {

	ArrayList<?> versions = new ArrayList();
	public void retreiveDatas(ArrayList<?> versions) {
		this.versions = versions;		
	}
	
	public ArrayList<?> getVersions() {
		return versions;
	}
	
    public int getVersionSize(){
       if( versions == null ) return 0;
       else return versions.size();
    }
}
