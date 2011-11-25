/*
Name: 
    - AddressFactory 

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

package com.processpuzzle.party.domain;

import com.processpuzzle.address.domain.Settlement;
import com.processpuzzle.address.domain.ZipCode;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.persistence.domain.GenericFactory;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class AddressFactory extends GenericFactory<Address> {	

   public EmailAddress createEmailAddress(String emailAddress) {
      PartyRepository partyRepository = (PartyRepository)ProcessPuzzleContext.getInstance().getRepository(PartyRepository.class);
      DefaultUnitOfWork work=new DefaultUnitOfWork(true);
      Party party=partyRepository.findPartyByAddress(work, emailAddress);
      work.finish();
      if( party == null )
      {
         return new EmailAddress(emailAddress);
      }
      else throw new EntityIdentityCollitionException(EmailAddress.class.getName(), emailAddress, EmailAddress.class.getSimpleName());
   }
   
   public WebPageAddress createWebPageAddress(String webPageAddress) {
      PartyRepository partyRepository = (PartyRepository)ProcessPuzzleContext.getInstance().getRepository(PartyRepository.class);
      DefaultUnitOfWork work=new DefaultUnitOfWork(true);
      Party party=partyRepository.findPartyByAddress(work, webPageAddress);
      work.finish();
      if( party == null )
      {
         return new WebPageAddress(webPageAddress);
      }
      else throw new EntityIdentityCollitionException(WebPageAddress.class.getName(), webPageAddress, WebPageAddress.class.getSimpleName());
}

   public TelecomAddress createTelecomAddress( String theCountryCode, String theAreaCode, String theNumber ) {
      TelecomAddress telecomaddress = new TelecomAddress( theCountryCode, theAreaCode, theNumber );
      return telecomaddress;
   }

   public GeographicAddress createGeographicAddress( String street, String buildingNumber, ZipCode zipCode, Settlement settlement ) {
      GeographicAddress geographicAddress = new GeographicAddress( street, buildingNumber, zipCode, settlement );
      return geographicAddress;
   }

/*   
	public static GeographicAddress createGeographicAddress(Settlement settlement) {
		return new GeographicAddress(settlement);	
	}

    */
}
