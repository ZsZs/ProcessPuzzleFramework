/*
Name: 
    - MeasurementContext 

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

package com.processpuzzle.application.configuration.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.fundamental_types.quantity.domain.TimeUnit;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;
import com.processpuzzle.fundamental_types.quantity.domain.Units;
import com.processpuzzle.fundamental_types.quantity.money.domain.Currency;

public class MeasurementContext extends TransientApplicationContext implements ApplicationContext {
   private static Log log = LogFactory.getLog( MeasurementContext.class );
   private List<Unit> units = new ArrayList<Unit>();
   
//Constructors
   public MeasurementContext( Application application ){
      super( application );
   }

//Public accessors
   public Unit findUnitBySymbol( String symbol ) {
      for (Iterator<Unit> i=units.iterator();i.hasNext();){
         Unit u=(Unit)i.next();
         if (u.getSymbol().equals(symbol)) return u;
      }
      return null;
   }

   public Collection<Unit> findAllUnits() {
      return units;
   }
 
   //Protected, private helper methods
   @Override protected void setUpTransientComponents(){
      createUnits();
      createTimeUnits();
      createCurrencies();
      createSpecialUnits();
      log.info("MeasurementContext.setUp() - finished.");
   }
   
   @Override protected void tearDownTransientComponents() {
      units.clear();
   }
   
   private void createTimeUnits() {
      TimeUnit milliseconds = new TimeUnit("milliseconds", Units.MILLISECONDS);
      TimeUnit second = new TimeUnit("second", Units.SECOND);
      TimeUnit minute = new TimeUnit("minute", Units.MINUTE);
      TimeUnit hour = new TimeUnit("hour", Units.HOUR);
      TimeUnit day = new TimeUnit("day", Units.DAY);
      TimeUnit week = new TimeUnit("week", Units.WEEK);
      TimeUnit month = new TimeUnit("month", Units.MONTH);
      TimeUnit year = new TimeUnit("year", Units.YEAR);

      year.addConversionRatio(12d, month);
      year.addConversionRatio(52d,week);
      day.addConversionRatio(86400000d, milliseconds);
      day.addConversionRatio(86400d, second);
      day.addConversionRatio(1440d, minute);
      day.addConversionRatio(24d, hour);
      hour.addConversionRatio(3600000d, milliseconds);
      hour.addConversionRatio(3600d, second);
      hour.addConversionRatio(60d, minute);
      minute.addConversionRatio(60000d, milliseconds);
      minute.addConversionRatio(60d, second);
      second.addConversionRatio(1000d, milliseconds);
      week.addConversionRatio(7d, day);
      
      
      
      units.add(milliseconds);
      units.add(second);
      units.add(minute);
      units.add(hour);
      units.add(day);
      units.add(week);
      units.add(month);
      units.add(year);
   }

   private void createUnits() {
      Unit millimetre = new Unit("millimetre", Units.MILLIMETRE);
      Unit centimetre = new Unit("centimetre", Units.CENTIMETRE);
      Unit decimetre = new Unit("decimetre", Units.DECIMETRE);
      Unit metre = new Unit("metre", Units.METRE);
      Unit kilometre = new Unit("kilometre", Units.KILOMETRE);

      Unit gramm = new Unit("gramm", Units.GRAMM);
      Unit dekagramm = new Unit("dekagramm", Units.DEKAGRAMM);
      Unit kilogramm = new Unit("kilogramm", Units.KILOGRAMM);
      Unit quintal = new Unit("quintal", Units.QUINTAL);
      Unit ton = new Unit("ton", Units.TON);

      kilometre.addConversionRatio(1000000d, millimetre);
      kilometre.addConversionRatio(100000d, centimetre);
      kilometre.addConversionRatio(10000d, decimetre);
      kilometre.addConversionRatio(1000d, metre);
      metre.addConversionRatio(1000d, millimetre);
      metre.addConversionRatio(100d, centimetre);
      metre.addConversionRatio(10d, decimetre);
      decimetre.addConversionRatio(100d, millimetre);
      decimetre.addConversionRatio(10d, centimetre);
      centimetre.addConversionRatio(10d, millimetre);

      ton.addConversionRatio(1000000d, gramm);
      ton.addConversionRatio(100000d, dekagramm);
      ton.addConversionRatio(1000d, kilogramm);
      ton.addConversionRatio(10d, quintal);
      quintal.addConversionRatio(100000d, gramm);
      quintal.addConversionRatio(10000d, dekagramm);
      quintal.addConversionRatio(100d, kilogramm);
      kilogramm.addConversionRatio(1000d, gramm);
      kilogramm.addConversionRatio(100d, dekagramm);
      dekagramm.addConversionRatio(10d, gramm);

      units.add(millimetre);
      units.add(centimetre);
      units.add(decimetre);
      units.add(metre);
      units.add(kilometre);
      units.add(gramm);
      units.add(dekagramm);
      units.add(kilogramm);
      units.add(quintal);
      units.add(ton);

   }

   private void createCurrencies() {
      Currency huf = new Currency("Hungarian Forint", "HUF");
      Currency usd = new Currency("United States Dollar", "USD");
      Currency eur = new Currency("Euro", "EUR");
      Currency gbp = new Currency("Pound Sterling", "GBP");

      units.add(huf);
      units.add(usd);
      units.add(eur);
      units.add(gbp);
   }
   
   private void createSpecialUnits() {
      Unit piece = new Unit("piece", Units.PIECE);
      Unit person = new Unit("person", Units.PERSON);
      
      units.add(piece);
      units.add(person);
   }
}
