/*
Name: 
    - TimeStamp

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

public class TimeStamp {
   private java.sql.Timestamp sqlValue;
   
   public TimeStamp( java.sql.Timestamp sqlTimeStamp ) {
      this.sqlValue = sqlTimeStamp;
   }
   
   public static java.util.Date toDate( java.sql.Timestamp timestamp ) {
      long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
      return new java.util.Date( milliseconds );
   }
   
   public java.util.Date getDate() {
      return toDate( this.sqlValue );
   }
}
