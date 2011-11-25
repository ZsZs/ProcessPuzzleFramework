/*
Name: 
    - ActionList_ListQueryView 

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
 * Created on Oct 20, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.processpuzzle.artifact.domain.ListQueryView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.workflow.activity.domain.ActionPriorities;

/**
 * @author zsolt.zsuffa
 */
public class ActionList_ListQueryView extends ListQueryView<ActionList> {

   public ActionList_ListQueryView( ActionList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );

   }

   public List<String> getPriorities() {
      return Arrays.asList( ActionPriorities.getStateDescriptions() );
   }

   public void performAction() {
      if( method.equals( "buildCustomQuery" ) ){
         String prefix = "from ActionDataSheet";
         if( customQueryProperties.size() > 0 )
            prefix += " o where ";
         for( Iterator<?> iter = customQueryProperties.iterator(); iter.hasNext(); ){
            String queryProperty = (String) iter.next();
            prefix += queryProperty;
            if( iter.hasNext() )
               prefix += " and ";
         }
         customQuery = prefix;
      }
   }

   public void setSubject( String subject ) {
      if( !subject.equals( "" ) ){

      }
   }

   public String getProjectedBeginInterval() {
      return null;
   }

   public void setProjectedBeginInterval( String interval ) {
      if( !interval.equals( "-" ) ){
         String from = interval.substring( 0, interval.lastIndexOf( "-" ) );
         String to = interval.substring( interval.lastIndexOf( "-" ) + 1, interval.length() );
         if( !to.equals( "" ) )
            customQueryProperties.add( "o.action.projectedBegin.value between '" + from + "' and '" + to + "'" );
         else
            customQueryProperties.add( "o.action.projectedBegin.value= '" + from + "'" );
      }
   }

   public String getProjectedEndInterval() {
      return null;
   }

   public void setProjectedEndInterval( String interval ) {
      if( !interval.equals( "-" ) ){
         String from = interval.substring( 0, interval.lastIndexOf( "-" ) );
         String to = interval.substring( interval.lastIndexOf( "-" ) + 1, interval.length() );
         if( !to.equals( "" ) )
            customQueryProperties.add( "o.action.projectedEnd.value between '" + from + "' and '" + to + "'" );
         else
            customQueryProperties.add( "o.action.projectedEnd.value= '" + from + "'" );
      }
   }

   public String getRealBeginInterval() {
      return null;
   }

   public void setRealBeginInterval( String interval ) {
      if( !interval.equals( "-" ) ){
         String from = interval.substring( 0, interval.lastIndexOf( "-" ) );
         String to = interval.substring( interval.lastIndexOf( "-" ) + 1, interval.length() );
         if( !to.equals( "" ) )
            customQueryProperties.add( "o.action.realBegin.value between '" + from + "' and '" + to + "'" );
         else
            customQueryProperties.add( "o.action.realBegin.value= '" + from + "'" );
      }
   }

   public String getRealEndInterval() {
      return null;
   }

   public void setRealEndInterval( String interval ) {
      if( !interval.equals( "-" ) ){
         String from = interval.substring( 0, interval.lastIndexOf( "-" ) );
         String to = interval.substring( interval.lastIndexOf( "-" ) + 1, interval.length() );
         if( !to.equals( "" ) )
            customQueryProperties.add( "o.action.realEnd.value between '" + from + "' and '" + to + "'" );
         else
            customQueryProperties.add( "o.action.realEnd.value= '" + from + "'" );
      }
   }

   public void setPriority( String priority ) {
      if( !priority.equals( "" ) ){
         customQueryProperties.add( "o.action.priority= '" + priority + "'" );
      }
   }

   public List<?> getQueryProperties() {
      return null;
   }

   public String getTargetArtifact() {
      return null;
   }

}
