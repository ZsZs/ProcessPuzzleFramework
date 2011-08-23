package com.processpuzzle.artifact.domain;

import hu.itkodex.commons.persistence.Entity;

import java.util.Calendar;
import java.util.Date;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class HTMLText extends GenericEntity<HTMLText> implements Entity, Comparable<HTMLText>{
   private String divId;
   private String text;
   private Date creationTimeStamp = Calendar.getInstance().getTime();

   public HTMLText() {}

   public HTMLText(String divId, String text) {
      this.divId = divId;
      this.text = text;
   }

   public String getDivId() {
      return divId;
   }

   public void setDivId(String divId) {
      this.divId = divId;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public Date getCreationTimeStamp() {
      return creationTimeStamp;
   }

   public void setCreationTimeStamp(Date creationTimeStamp) {
      this.creationTimeStamp = creationTimeStamp;
   }

   public int compareTo( HTMLText o) {
      if (!(o instanceof HTMLText)) return -1;
      HTMLText n = (HTMLText) o;
      if (n.getCreationTimeStamp().getTime()<creationTimeStamp.getTime()) return -1;
      if (n.getCreationTimeStamp().getTime()>creationTimeStamp.getTime()) return 1;
      return 0;
   }

   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public DefaultIdentityExpression getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}