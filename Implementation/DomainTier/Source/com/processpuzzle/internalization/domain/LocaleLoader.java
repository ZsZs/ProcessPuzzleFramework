/*
Name: 
    - LocaleLoader

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

package com.processpuzzle.internalization.domain;

import java.text.DateFormat;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.resource.domain.XmlDataLoader;
import com.processpuzzle.application.resource.domain.XmlDataLoaderException;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleParseException;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;
import com.processpuzzle.fundamental_types.quantity.money.domain.Currency;

public class LocaleLoader extends XmlDataLoader {
   InternalizationContext i18Context;
   MeasurementContext measurementContext;

   public LocaleLoader( String path, InternalizationContext i18Context, MeasurementContext measurementContext ) {
      super( path );
      this.measurementContext = measurementContext;
      this.i18Context = i18Context;
      /*
       * TODO We don't use the repository anymore, we use the InternalizationContext instead. This part has to be removed!
       */
      // ProcessPuzzleContext config = ProcessPuzzleContext.getInstance();
      // try {
      // config.setUp( TestConfigurationConstants.CONFIGURATION_PROPERTY_FILE );
      // } catch (ConfigurationSetUpException e) {
      // e.printStackTrace();
      // }
      // repository = config.getMeasurementContext();
   }

   // @Deprecated
   // public void loadData(){
   // super.loadData();
   // loadJavaLocales();
   // loadLocales();
   // }
   // @Deprecated
   // private void loadJavaLocales(){
   // Locale defaultLocales[]=Locale.getAvailableLocales();
   // //UnitOfWork work = new UnitOfWork(true);
   // for (int i=0;i<defaultLocales.length;i++){
   // ProcessPuzzleLocale tmpLocale=new ProcessPuzzleLocale(defaultLocales[i]);
   // //System.out.println(tmpLocale.getLanguage()+"-"+tmpLocale.getCountry()+"-"+tmpLocale.getVariant());
   // //repository.add(tmpLocale);
   // }
   // //work.finish();
   // }

   public void loadLocaleDefinition( ProcessPuzzleLocale locale ) {
      super.loadData();

      Document doc = getDocument();
      Element root = doc.getRootElement();
      for( Iterator<?> i = root.elementIterator( "locale" ); i.hasNext(); ){
         Element localeElement = (Element) i.next();
         try{
            if( localeElement.attributeValue( "language" ).equals( locale.getLanguage() )
                  && localeElement.attributeValue( "country" ).equals( locale.getCountry() ) ){
               loadQuantityFormatElements( localeElement.element( "quantityFormatSpecifier" ), locale );
               loadDateFormatElements( localeElement.element( "dateFormatSpecifier" ), locale );
               loadCurrencyElements( localeElement.element( "currency" ), locale );
               return;
            }
         }catch( NullPointerException e ){
            e.printStackTrace();
         }catch( Exception e ){
            e.printStackTrace();
         }
      }
      throw new LocaleDefinitionNotFoundException( locale.getLanguage(), locale.getCountry() );
   }

   private void loadCurrencyElements( Element root, ProcessPuzzleLocale locale ) {
      if( root == null )
         return;
      Attribute nameAttr = root.attribute( "name" );
      Attribute symbolAttr = root.attribute( "symbol" );

      if( nameAttr != null && symbolAttr != null ){
         Unit u = measurementContext.findUnitBySymbol( symbolAttr.getValue() );
         if( u != null )
            locale.setLegalTender( (Currency) u );
      }else
         return;

   }

   // @Deprecated
   // private void loadLocales() throws XmlDataLoaderException{
   // Document doc = getDocument();
   // Element root=doc.getRootElement();
   // // Iterating through the definied locales
   // for ( Iterator i = root.elementIterator("locale"); i.hasNext(); ) {
   // Element localeElement=(Element)i.next();
   // ProcessPuzzleLocale newLocale;
   //			
   // String localeCountry=localeElement.attributeValue("country");
   // String localeLanguage=localeElement.attributeValue("language");
   // String localeVariant=localeElement.attributeValue("variant");
   // String localeDefault=localeElement.attributeValue("default");
   //			
   // if (localeLanguage==null) {
   // throw new XmlDataLoaderException(this,"Locale language not found!");
   // }else{
   // localeLanguage=localeLanguage.toLowerCase();
   // }
   // if (localeCountry!=null) localeCountry=localeCountry.toUpperCase();
   //			
   // newLocale=new ProcessPuzzleLocale(localeLanguage,localeCountry,localeVariant);

   // if ((localeDefault!=null) && (localeDefault.equals("true")) && (localeCountry!=null) && (localeVariant!=null)){
   // if (!repository.defaultExists(localeLanguage,localeCountry)){
   // newLocale.setDefault();
   // }else{
   // throw new XmlDataLoaderException(this,"Duplicate default value for locale: "+localeLanguage+"_"+localeCountry+"! Cannot insert:"+localeVariant);
   // }
   // }
   //			
   // Element quantityFormatElement=localeElement.element("quantityFormatSpecifier");
   // loadQuantityFormatElements(quantityFormatElement,newLocale);
   //            
   // Element currencyElement=localeElement.element("currency");
   // newLocale.setLegalTender(currencyElement.attributeValue("symbol"));
   // loadDateFormatElements(localeElement.element("dateFormatSpecifier"),newLocale);
   // //System.out.println(newLocale.getLanguage);
   // //UnitOfWork work = new UnitOfWork(true);
   // //repository.add( newLocale);
   // //work.finish();
   // }
   // }

   // private void loadCurrencyElements(Element root, ProcessPuzzleLocale locale){
   // if (root==null) return;
   // locale.setLegalTender(root.attributeValue("symbol"));
   // }

   private void loadQuantityFormatElements( Element root, ProcessPuzzleLocale locale ) {
      if( root == null )
         return;
      Attribute decimalSeparatorAttribute = root.attribute( "decimalSeparator" );
      if( decimalSeparatorAttribute != null )
         locale.getQuantityFormat().setDecimalSeparator( decimalSeparatorAttribute.getText().charAt( 0 ) );

      Attribute groupingSeparatorAttribute = root.attribute( "groupingSeparator" );
      if( groupingSeparatorAttribute != null )
         locale.getQuantityFormat().setGroupingSeparator( groupingSeparatorAttribute.getText().charAt( 0 ) );
      if( groupingSeparatorAttribute != null && decimalSeparatorAttribute != null ){
         if( groupingSeparatorAttribute.getText().charAt( 0 ) == decimalSeparatorAttribute.getText().charAt( 0 ) ){
            throw new ProcessPuzzleParseException( Character.toString( groupingSeparatorAttribute.getText().charAt( 0 ) )
                  + Character.toString( decimalSeparatorAttribute.getText().charAt( 0 ) ), "Same Separators!", null );
         }
      }
      try{
         Element maxIntElement = root.element( "maxIntDigits" );
         if( maxIntElement != null )
            locale.getQuantityFormat().setMaximumIntegerDigits( Integer.parseInt( maxIntElement.getText() ) );
         Element minIntElement = root.element( "minIntDigits" );
         if( minIntElement != null )
            locale.getQuantityFormat().setMinimumIntegerDigits( Integer.parseInt( minIntElement.getText() ) );

         Element maxFractElement = root.element( "maxFractDigits" );
         if( maxFractElement != null )
            locale.getQuantityFormat().setMaximumFractionDigits( Integer.parseInt( maxFractElement.getText() ) );

         Element minFractElement = root.element( "minFractDigits" );
         if( minFractElement != null )
            locale.getQuantityFormat().setMinimumFractionDigits( Integer.parseInt( minFractElement.getText() ) );
      }catch( NumberFormatException e ){
         throw new XmlDataLoaderException( this, "Non-Integer values!", e );
      }

   }

   private void loadDateFormatElements( Element root, ProcessPuzzleLocale locale ) {
      if( root == null )
         return;

      Element dateElement = root.element( "dateFormat" );
      Element timeElement = root.element( "timeFormat" );

      if( (dateElement == null) || (timeElement == null) )
         return;

      Element mediumDateElement = dateElement.element( "mediumPattern" );
      Element mediumTimeElement = timeElement.element( "mediumPattern" );

      if( mediumDateElement == null || mediumTimeElement == null )
         return;
      locale.getDateFormat().setDatePattern( mediumDateElement.getText() );
      locale.getDateFormat().setTimePattern( mediumTimeElement.getText() );

      Element fullDateElement = dateElement.element( "fullPattern" );
      Element longDateElement = dateElement.element( "longPattern" );
      Element shortDateElement = dateElement.element( "shortPattern" );

      if( fullDateElement != null ){
         locale.getDateFormat().setDatePattern( fullDateElement.getText(), DateFormat.FULL );
      }
      if( longDateElement != null ){
         locale.getDateFormat().setDatePattern( longDateElement.getText(), DateFormat.LONG );
      }
      if( shortDateElement != null ){
         locale.getDateFormat().setDatePattern( shortDateElement.getText(), DateFormat.SHORT );
      }

      Element fullTimeElement = timeElement.element( "fullPattern" );
      Element longTimeElement = timeElement.element( "longPattern" );
      Element shortTimeElement = timeElement.element( "shortPattern" );

      if( fullTimeElement != null ){
         locale.getDateFormat().setTimePattern( fullTimeElement.getText(), DateFormat.FULL );
      }
      if( longTimeElement != null ){
         locale.getDateFormat().setTimePattern( longTimeElement.getText(), DateFormat.LONG );
      }
      if( shortTimeElement != null ){
         locale.getDateFormat().setTimePattern( shortTimeElement.getText(), DateFormat.SHORT );
      }
   }
}
