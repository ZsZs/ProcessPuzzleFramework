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
