/*
Name: 
    - WebPageAddress

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

public class WebPageAddress extends Address {
   private String url;

   public WebPageAddress() {
      super();
   }

   public WebPageAddress(java.lang.String url, boolean isDefalut) {
      super(isDefalut);
      this.url = url;
   }
   
   public WebPageAddress(java.lang.String url) {
      this.url = url;
   }

   public void setWebPageAddress(String url) {
      this.url = url;
   }

   public String getWebPageAddress() {
      return url;
   }

   public boolean equals(Object o) {
      if (!(o instanceof WebPageAddress))
         return false;
      WebPageAddress n = (WebPageAddress) o;
      return ((this.getWebPageAddress().equals(n.getWebPageAddress())));
   }

   public int hashCode() {
      if (getWebPageAddress() != null)
         return this.getWebPageAddress().hashCode() * 1000 + this.getWebPageAddress().hashCode();
      else
         return 0;
   }

   public int compareTo(Object o) {
      if (!(o instanceof WebPageAddress))
         return -1;
      WebPageAddress n = (WebPageAddress) o;
      int c;
      if ((c = getWebPageAddress().compareTo(n.getWebPageAddress())) != 0)
         return c;
      return 0;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

}
