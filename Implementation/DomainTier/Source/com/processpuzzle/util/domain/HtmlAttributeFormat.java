package com.processpuzzle.util.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class HtmlAttributeFormat extends GenericEntity<HtmlAttributeFormat> {
   private String targetProperty;
   private String htmlElementName;
   private Integer startIndex;
   private Integer endIndex;
      
   public HtmlAttributeFormat(String targetProperty, String htmlElementName, Integer startIndex, Integer endIndex) {
      super();
      this.targetProperty = targetProperty;
      this.htmlElementName = htmlElementName;
      this.startIndex = startIndex;
      this.endIndex = endIndex;
   }
   
   public HtmlAttributeFormat() {}
   
   public Integer getEndIndex() {
      return endIndex;
   }
   public void setEndIndex(Integer endIndex) {
      this.endIndex = endIndex;
   }
   public String getHtmlElementName() {
      return htmlElementName;
   }
   public void setHtmlElementName(String htmlElementName) {
      this.htmlElementName = htmlElementName;
   }
   public Integer getStartIndex() {
      return startIndex;
   }
   public void setStartIndex(Integer startIndex) {
      this.startIndex = startIndex;
   }
   public String getTargetProperty() {
      return targetProperty;
   }
   public void setTargetProperty(String targetProperty) {
      this.targetProperty = targetProperty;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<HtmlAttributeFormat>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}
