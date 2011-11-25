/*
Name: 
    - EmailAddress 

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
