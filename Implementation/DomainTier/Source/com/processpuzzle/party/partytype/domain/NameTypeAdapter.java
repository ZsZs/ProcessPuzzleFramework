package com.processpuzzle.party.partytype.domain;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class NameTypeAdapter extends XmlAdapter<String, String> {

   @Override
   public String unmarshal( String v ) throws Exception {
      return v;
   }

   @Override
   public String marshal( String v ) throws Exception {
      return v;
   }

}
