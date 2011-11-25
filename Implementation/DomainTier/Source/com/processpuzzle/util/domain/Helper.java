/*
Name: 
    - Helper 

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

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Helper {
	public static String getTodaysDate() {
		Calendar calendar = new GregorianCalendar();
		String month;
		String day;
		if ((calendar.get(Calendar.MONTH)+1)<10)	//januar a 0 
			month="0"+(calendar.get(Calendar.MONTH)+1);
			else month=Integer.toString((calendar.get(Calendar.MONTH)+1));
		if (calendar.get(Calendar.DAY_OF_MONTH)<10)
			day="0"+calendar.get(Calendar.DAY_OF_MONTH);
			else day=Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		
		String date = calendar.get(Calendar.YEAR)+"."+month+"."+day;
		return date;
	}
	

}
