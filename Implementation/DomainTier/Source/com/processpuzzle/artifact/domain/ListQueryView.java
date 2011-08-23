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