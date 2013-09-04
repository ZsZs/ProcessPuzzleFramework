/*
Name: 
    - ArtifactListView

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

package com.processpuzzle.artifact.domain;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.artifact_type.domain.ArtifactListViewType;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.commons.persistence.PersistentObject;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.commons.persistence.query.Count;
import com.processpuzzle.commons.persistence.query.Query;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.PersistentLong;
import com.processpuzzle.persistence.query.domain.DefaultQuery;

public abstract class ArtifactListView<L extends Artifact<?>> extends ArtifactView<L> {
   private Logger log = LoggerFactory.getLogger( ArtifactListView.class );
   protected String queryStatement = "";
   protected int maxReturnSize = 25;
   protected int startQueryFrom = 0;
   protected int rowCount = 0;
   protected String orderBy = "";
   protected String order = "";
   protected String pureQuery = null;
   protected List<PropertyView<L>> listedArtifactsPropertyViews = new ArrayList<PropertyView<L>>();
   private Class<? extends Artifact<?>> listedArtifactClass;

   @SuppressWarnings("unchecked")
   public ArtifactListView( L artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
      String fullClassName = ((ArtifactListViewType) getType()).getListedArtifactType();
      try{
         listedArtifactClass = (Class<? extends Artifact<?>>) Class.forName( fullClassName );
      }catch( ClassNotFoundException e ){
         String message = "Listed artifact's class name: '" + fullClassName + "' is not valid. Seet the ArtifactType."; 
         log.error( message, e );
         throw new ArtifactViewException( message, e );
      }
   }

   public void query( String query, Integer start, Integer count ) {
      query( query, start, count, null, null );
   }

   public void query( String query ) {
      query( query, null, null, null, null );
   }

   @SuppressWarnings( "unchecked" )
   public void query() {
      String queryText = null;
      listedArtifactsPropertyViews.clear();
      if( !queryStatement.equals( "" ) )
         queryText = queryStatement;
      else {
         String fullClassName = ((ArtifactListViewType) getType()).getListedArtifactType();
         String className = fullClassName.substring( fullClassName.lastIndexOf( "." )+1);
         String alias = className.substring( 0, 1 ).toLowerCase() + className.substring( 1 );
         queryText = "select * from " + fullClassName + " as " + alias;         
      }

      pureQuery = queryText;
      this.rowCount = getRowCount( queryText );
      queryText = addOrderBy( queryText );

      log.info( "Query: " + queryText + ", Start:" + startQueryFrom + ", Count: " + rowCount + ", OrderBy: " + orderBy + ", Order: " + order );
      UnitOfWork work = new DefaultUnitOfWork( true );
      Query query = null;
      try{
         query = DefaultQuery.parseOQLStatement( queryText );
      }catch( ClassNotFoundException e ){
         String message = "Parsing query statement: '" + queryText + "' caused error."; 
         log.error( message, e );
         throw new ArtifactViewException( message, e );
      }
      
      query.setFirstResult( startQueryFrom );
      query.setMaxResults( maxReturnSize );
      
      RepositoryResultSet<Artifact> listedArtifacts = artifactRepository.findByQuery( work, query );
      work.finish();
      work = new DefaultUnitOfWork(true);
      for( Iterator<Artifact> iter = listedArtifacts.iterator(); iter.hasNext(); ){
         Artifact<L> artifact = (Artifact<L>) iter.next();
         //artifact = (Artifact<L>) artifactRepository.findById( work, artifact.getClass(), artifact.getId() );
         artifact.instantiateViews();
         listedArtifactsPropertyViews.add( artifact.getPropertyView() );
      }
      work.finish();
   }

   public String addOrderBy( String query ) {

      if( orderBy == null || orderBy.equals( "" ) ){
         orderBy = ((ArtifactListViewType) getType()).getOrderBy();
      }
      if( order == null || order.equals( "" ) ){
         order = ((ArtifactListViewType) getType()).getOrd();
      }

      if( order == null || order.equals( "" ) )
         order = "asc";

      StringTokenizer tokenizer = new StringTokenizer( query, " " );
      tokenizer.nextToken();
      tokenizer.nextToken();
      String variable = null;

      if( orderBy != null && !orderBy.equals( "" ) ){
         StringTokenizer orderByTokenizer = new StringTokenizer( orderBy, ", " );
         StringTokenizer orderTokenizer = new StringTokenizer( order, ", " );

         if( query.indexOf( "where" ) >= 0 ){
            variable = tokenizer.nextToken();
            query += " order by ";
            while( orderByTokenizer.hasMoreTokens() ){
               query += variable + "." + orderByTokenizer.nextToken() + " " + orderTokenizer.nextToken();
               if( orderByTokenizer.hasMoreTokens() )
                  query += ", ";
            }
         }else{
            query += " o order by ";
            while( orderByTokenizer.hasMoreTokens() )
               query += "o." + orderByTokenizer.nextToken() + " " + orderTokenizer.nextToken();
            if( orderByTokenizer.hasMoreTokens() )
               query += ", ";
         }
      }
      return query;
   }

   public List<PropertyView<L>> getListedArtifactsPropertyViews() {
      return listedArtifactsPropertyViews;
   }

   public int getRowCount( String queryText ) {
      DefaultQuery query = new DefaultQuery( listedArtifactClass );
      query.getAttributeFilter().addAggregateFunction( new Count( "name" ));
      RepositoryResultSet<? extends PersistentObject> resultSet = artifactRepository.findByQuery( (Query)query );
      Long count = ((PersistentLong) resultSet.getEntityAt( 0 )).getValue();
      return count.intValue();
   }

   public int getMaxReturnSize() {
      return maxReturnSize;
   }

   public int getRowCount() {
      return this.rowCount;
   }

   public void setMaxReturnSize( Integer maxReturnSize ) {
      if( (maxReturnSize != null) && (maxReturnSize.intValue() <= 1000) )
         this.maxReturnSize = maxReturnSize.intValue();
   }

   public void setOrderBy( String orderBy ) {
      this.orderBy = orderBy;
   }

   public void setOrder( String order ) {
      this.order = order;
   }

   public String getQueryStatement() {
      return queryStatement;
   }

   public void setQueryStatement( String queryStatement ) {
      this.queryStatement = (queryStatement != null && !queryStatement.equals( "" )) ? queryStatement : "";
   }

   public int getStartQueryFrom() {
      return startQueryFrom;
   }

   public void setStartQueryFrom( Integer startQueryFrom ) {
      if( (startQueryFrom != null) && (startQueryFrom.intValue() >= 1) )
         this.startQueryFrom = startQueryFrom.intValue();
   }

   public String getOrder() {
      return order;
   }

   public String getOrderBy() {
      return orderBy;
   }

   public String getPureQuery() {
      return pureQuery;
   }

   private void query( String query, Integer start, Integer count, String orderBy, String order ) {
      setQueryStatement( query );
      setStartQueryFrom( start );
      setMaxReturnSize( count );
      setOrderBy( orderBy );
      setOrder( order );
      query();
   }
}