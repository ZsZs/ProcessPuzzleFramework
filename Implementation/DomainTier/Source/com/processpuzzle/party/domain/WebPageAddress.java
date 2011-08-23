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
