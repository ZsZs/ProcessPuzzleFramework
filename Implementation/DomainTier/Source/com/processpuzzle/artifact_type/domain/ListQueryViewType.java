/*
Name: 
    - ListQueryViewType

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

package com.processpuzzle.artifact_type.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.artifact.domain.ListQueryView;
import com.processpuzzle.persistence.query.domain.DefaultQuery;

@XmlRootElement(name = "listQueryViewType")
public class ListQueryViewType extends ArtifactViewType {
	private @XmlElementWrapper(name = "preDefinedQueries") @XmlElement(name = "query") List<DefaultQuery> preDefinedQueries = new ArrayList<DefaultQuery>();
	private @XmlAttribute(name = "targetPropertyView") String targetPropertyView;

	public List<DefaultQuery> getPreDefindedQueries() {
		return preDefinedQueries;
	}

	public void setPreDefindedQueries(List<DefaultQuery> preDefindedQueries) {
		this.preDefinedQueries = preDefindedQueries;
	}

	protected ListQueryViewType() {
		super();
	}

	public ListQueryViewType(String name, String viewClassName, String presentationUri) {
		super(name, presentationUri);
		this.setViewClassName(viewClassName);
	}

	public ListQueryViewType(String name) {
		super(name);
		this.setPresentationUri(null);
		this.setViewClassName(null);
	}

	public void setPresentationUri(String uri) {
		if (uri == null)
			this.presentationUri = "/BusinessImplementation/ProjectAdministration/ArtifactList_QueryView.jsp";
		else
			this.presentationUri = uri;
	}

	public void setViewClassName(String viewClassName) {
		if (viewClassName == null)
			this.viewClassName = ListQueryView.class.getName();
		else
			this.viewClassName = viewClassName;
	}

	public void addPredefinedQuery( String name, String description, String statement ) {
	   DefaultQuery query = new DefaultQuery( ArtifactType.class, name, description );
	   query.setPredefinedStatement( statement );
       this.preDefinedQueries.add( query );
	}

	public DefaultQuery findPredefinedQuery( String queryName ) {
      for( DefaultQuery query : preDefinedQueries ) {
         if( query.getName().equals( queryName ) ) return query;
      }
      return null;
   }

   public String getTargetPropertyView() {
		return targetPropertyView;
	}

	public void setTargetPropertyView(String targetPropertyView) {
		this.targetPropertyView = targetPropertyView;
	}
	
   @Override
   public String getViewClassName() {
      if( viewClassName == null ) viewClassName = ListQueryView.class.getName();
      return viewClassName;
   }   
}
