/*
Name: 
    - CoWorkerDataSheet_BaseDataView

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

package com.processpuzzle.party.artifact;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.domain.PersonName;

public class CoWorkerDataSheet_BaseDataView extends CustomFormView<CoWorkerDataSheet> {

   public CoWorkerDataSheet_BaseDataView( CoWorkerDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   @Override
   public void initializeView() {
      // TODO Auto-generated method stub
      
   }

   public String getPrefix() {
      return ((PersonName) parentArtifact.getPerson().getPartyName()).getPrefix();
   }

   public void setPrefix( String prefix ) {
      ((PersonName) parentArtifact.getPerson().getPartyName()).setPrefix( prefix );
   }

   public String getFamilyName() {
      return ((PersonName) parentArtifact.getPerson().getPartyName()).getFamilyName();
   }

   public void setFamilyName( String familyName ) {
      String givenName = ((PersonName) parentArtifact.getPerson().getPartyName()).getGivenName();
      String userName = parentArtifact.getPerson().getSystemUser().getUserName();
      updateName( familyName, givenName, userName );
   }

   public String getGivenName() {
      return ((PersonName) parentArtifact.getPerson().getPartyName()).getGivenName();
   }

   public void setGivenName( String givenName ) {
      String familyName = ((PersonName) parentArtifact.getPerson().getPartyName()).getFamilyName();
      String userName = parentArtifact.getPerson().getSystemUser().getUserName();
      updateName( familyName, givenName, userName );
   }

   public String getUserName() {
      return ((CoWorkerDataSheet) parentArtifact).getPerson().getSystemUser().getUserName();
   }

   public void setUserName( String userName ) {
      String familyName = ((PersonName) parentArtifact.getPerson().getPartyName()).getFamilyName();
      String givenName = ((PersonName) parentArtifact.getPerson().getPartyName()).getGivenName();
      updateName( familyName, givenName, userName );
   }

   public String getPassword() {
      return parentArtifact.getPerson().getSystemUser().getPassword();
   }

   public void setPassword( String password ) {
      parentArtifact.getPerson().getSystemUser().changePassword( password );
   }

   public String getSelectLocale() {
      if( parentArtifact.getPerson().getSystemUser() != null && parentArtifact.getPerson().getSystemUser().getPrefferedLocale() != null )
         return parentArtifact.getPerson().getSystemUser().getPrefferedLocale().getLanguage();
      return null;
   }

   public void setSelectLocale( String locale ) {
      InternalizationContext internalizationContext = applicationContext.getInternalizationContext();
      parentArtifact.getPerson().getSystemUser().setPrefferedLocale( internalizationContext.findLocaleByLanguage( locale ) );
   }

   public List<String> getLocales() {
      String key = PropertyKeys.INTERNALIZATION_AVAILABLE_LOCALES.getDefaultKey();
      StringTokenizer localeString = new StringTokenizer( (String) applicationContext.getProperty( key ), "|" );
            
      List<String> locales = new ArrayList<String>();
      while( localeString.hasMoreElements() ){
         locales.add( localeString.nextToken() );
      }
      return locales;
   }

   public void performAction() {
   }

   private void updateName( String familyName, String givenName, String userName ) {
      parentArtifact.renameName( givenName + "_" + userName + "_" + familyName );
      parentArtifact.getPerson().renameName( givenName + "_" + userName + "_" + familyName );
      ((PersonName) parentArtifact.getPerson().getPartyName()).setFamilyName( familyName );
   }

}
