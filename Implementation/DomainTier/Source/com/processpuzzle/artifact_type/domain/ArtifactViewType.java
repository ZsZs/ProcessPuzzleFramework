/*
Name: 
    - ArtifactViewType

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

import hu.itkodex.commons.persistence.Entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.processpuzzle.fundamental_types.domain.AssertionException;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

@XmlSeeAlso({
    ArtifactListViewType.class,
    ArtifactModificationsViewType.class,
    ArtifactPropertyViewType.class,
    RelatedArtifactsViewType.class,
    ArtifactCommentsViewType.class,
    ArtifactCustomFormViewType.class,
    ArtifactAccessRightsViewType.class,
    ListQueryViewType.class,
    ArtifactPrintViewType.class,
    EditableTextViewType.class
})
public class ArtifactViewType extends GenericEntity<ArtifactViewType> implements Entity {
	@XmlAttribute private String name;
	@XmlAttribute private String clientType;
	@XmlAttribute protected String viewClassName;
	@XmlAttribute protected String presentationUri;
	@XmlAttribute private boolean isDefault;
	@XmlAttribute private boolean isStatic;
	@XmlElement private String viewCaption;
	@XmlElement private String viewAccessUrl;
    @XmlElementWrapper(name = "associatedMenuItems") @XmlElement(name = "menuItem") private List<ArtifactMenu> associatedMenuItems;

	ArtifactViewType( String name, String presentationUri ) {
		if (name == null || name.equals(""))
			throw new AssertionException("ArtifactViewType.name can't be null or empty string");
		this.name = name;
		this.presentationUri = presentationUri;
	}

	ArtifactViewType( String name ) {
		this( name, null );
	}

	protected ArtifactViewType() { }

    //Public accessors
    public ArtifactMenu findMenu( String name ) {
       for( ArtifactMenu menu : associatedMenuItems ) {
          if( menu.getName().equals( name )) return menu;
       }
       return null;
    }
    
   public @Override <I extends DefaultIdentityExpression<ArtifactViewType>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   //Properties
    public String getName() { return name; }

	public List<ArtifactMenu> getAssociatedMenuItems() { return associatedMenuItems; }

	public boolean isDefault() { return isDefault; }

	public boolean isStatic() { return isStatic; }

	public String getViewCaption() { return viewCaption; }
	public void setViewCaption(String viewCaption) { this.viewCaption = viewCaption; }

	public String getPresentationUri() { return presentationUri; }
	public void setPresentationUri(String presentationUri) { this.presentationUri = presentationUri; }

	public String getViewClassName() { return viewClassName; }
	//public void setViewClassName( String viewClassName ) { this.viewClassName = viewClassName; }

	public String getViewAccessUrl() { return viewAccessUrl; }

	public String getClientType() { return clientType; }
	public void setClientType(String type) { this.clientType = type; }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }
}