/*
Name: 
    - TimePrecision

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

package com.processpuzzle.fundamental_types.domain;

public enum TimePrecision{
   YEAR( "year", 1), 
   MONTH( "month", 2), 
   WEEK( "week", 3), 
   DAY( "day", 4), 
   HOUR( "hour", 5), 
   MINUTE( "minute", 6), 
   SECOND( "second", 7), 
   MILLISECOND( "millisecond", 8);
   private static final int GREATHER = +1;
   private static final int EQUAL = 0;
   private static final int LESS = -1;
   private String name;
   private int value;
   
   TimePrecision( String name, int value ){
      this.name = name;
      this.value = value;
   }
   
   public int compare( TimePrecision otherPrecision ){
      if( value > otherPrecision.getValue() ) return GREATHER;
      if( value == otherPrecision.getValue() ) return EQUAL;
      if( value < otherPrecision.getValue() ) return LESS;
      return value;
   }
   public String getName() { return name; }
   public int getValue() { return value; }
}
