/*
Name: 
    - ListQueryView

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

/*
 * Created on Jul 17, 2006
 */
package com.processpuzzle.artifact.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.artifact_type.domain.ListQueryViewType;
import com.processpuzzle.persistence.query.domain.DefaultQuery;

public abstract class ListQueryView<L extends ArtifactList<?>> extends CustomFormView<L> {
   protected Map<String, DefaultQuery> preDefinedQueries = new HashMap<String, DefaultQuery>();
   protected List<String> customQueryProperties = new ArrayList<String>();
   protected String customQuery;

   public ListQueryView( L artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public void initializeView() {
      setUpPreDefinedQueries();
   }

   protected void setUpPreDefinedQueries() {
      ListQueryViewType viewType = (ListQueryViewType) type;
      for( Iterator<DefaultQuery> iter = viewType.getPreDefindedQueries().iterator(); iter.hasNext(); ){
         DefaultQuery query = (DefaultQuery) iter.next();
         preDefinedQueries.put( query.getName(), query );
      }
   }

   public abstract List<?> getQueryProperties();

   public abstract String getTargetArtifact();

   public Map<String, DefaultQuery> getPreDefinedQueries() {
      if( preDefinedQueries.size() == 0 )
         setUpPreDefinedQueries();
      return preDefinedQueries;
   }

   public String getData( String method, Map parameters ) {
      StringBuffer responseXml = new StringBuffer();
      responseXml.append( "<queries>" );
      for( Iterator<String> iter = preDefinedQueries.keySet().iterator(); iter.hasNext(); ){
         String queryName = (String) iter.next();
         DefaultQuery query = (DefaultQuery) preDefinedQueries.get( queryName );

         responseXml.append( "<query id='" + query.getId() + "'>" );
         responseXml.append( "<name>" + ProcessPuzzleContext.getInstance().getText( query.getName(), getPreferredLanguage() ) + "</name>" );
         // responseXml.append("<statement>" + query.getStatement() + "</statement>");
         responseXml.append( "</query>" );
      }
      responseXml.append( "</queries>" );

      return responseXml.toString();
   }

   public String getCustomQuery() {
      return customQuery;
   }
}