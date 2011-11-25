/*
Name: 
    - VersionTimePeriod 

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

package com.processpuzzle.util.domain;

import com.processpuzzle.fundamental_types.domain.TimePoint;

public class VersionTimePeriod implements Comparable<Object> {
   private Integer id;
   private TimePoint begin;
   private TimePoint end;

   public VersionTimePeriod() {
      super();
   }

   public void inlcudes() {}

   public TimePoint getBegin() {
      return begin;
   }

   public void setBegin( TimePoint begin ) {
      this.begin = begin;
   }

   public TimePoint getEnd() {
      return end;
   }

   public void setEnd( TimePoint end ) {
      this.end = end;
   }

   public Integer getId() {
      return id;
   }

   public int compareTo( Object o ) {
      VersionTimePeriod timePeriod = (VersionTimePeriod) o;
      int c = 0;
      if( (c = this.begin.compareTo( timePeriod.begin )) != 0 )
         return c;
      if( (c = this.end.compareTo( timePeriod.end )) != 0 )
         return c;
      return 0;
   }

}
