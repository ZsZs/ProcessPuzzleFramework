/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

public class EmailAddress extends Address {
	private String emailAddress;
	
	public EmailAddress() {
		super();
	}

	public void setEmailAddress(String emailAddress){
		this.emailAddress=emailAddress;
	}
	
	public String getEmailAddress(){
		return emailAddress;
	}
	
	public EmailAddress(String emailAddress) {
			this.emailAddress=emailAddress;
	}
	public boolean equals(Object o)
	{
		if(!(o instanceof WebPageAddress)) return false;
		WebPageAddress n = (WebPageAddress)o;
		return ((this.getEmailAddress().equals(n.getWebPageAddress())));
	}
	public int hashCode()
	{
		int emailAddressHash;
		if (getEmailAddress()==null) emailAddressHash=0; 
			else emailAddressHash=this.getEmailAddress().hashCode(); 
		
		return emailAddressHash*1000+emailAddressHash;
	}
	
	public int compareTo( Object o )
	{
		if(!(o instanceof EmailAddress)) return -1;
		EmailAddress n = (EmailAddress)o;
		int c;
		if((c = getEmailAddress().compareTo(n.getEmailAddress()))!=0) return c;
		return 0;
	}
}
