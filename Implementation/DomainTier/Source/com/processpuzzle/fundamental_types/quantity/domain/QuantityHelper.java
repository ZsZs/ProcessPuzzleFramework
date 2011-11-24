/*
Name: 
    - QuantityHelper

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

package com.processpuzzle.fundamental_types.quantity.domain;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.user_session.domain.UserRequestManager;


public class QuantityHelper {
   
   public static Quantity quantityCreator(double amount, String symbol) {
      Unit unit = UserRequestManager.getInstance().getApplicationContext().getMeasurementContext().findUnitBySymbol(symbol);
      return new Quantity(Double.valueOf(amount), unit );
   }

   
   public static Unit unitFinder(String symbol) {
	  symbol=symbol.trim();
      Unit unit = UserRequestManager.getInstance().getApplicationContext().getMeasurementContext().findUnitBySymbol(symbol);
      return unit;
   }
   
   public static void setRatio(String from,String to,double ratio){
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      MeasurementContext measurementContext = applicationContext.getMeasurementContext();
	   Unit fromUnit = measurementContext.findUnitBySymbol( from );
	   Unit toUnit = measurementContext.findUnitBySymbol( to );
	   // Itt lehet elszamolva nagyon a dolog!!
	   fromUnit.addConversionRatio(ratio, toUnit);
	   //toUnit.addConversionRatio(ratio, fromUnit);
   }
}
