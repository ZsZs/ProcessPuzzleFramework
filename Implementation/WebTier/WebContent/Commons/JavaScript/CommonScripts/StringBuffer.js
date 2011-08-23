/*
###########################################################################
# COACH: Component Based Open Source Architecture for                     #
#        Distributed Telecom Applications                                 #
# See:   http://www.objectweb.org/                                        #
#                                                                         #
# Copyright (C) 2003 Lucent Technologies Nederland BV                     #
#                    Bell Labs Advanced Technologies - EMEA               #
#                                                                         #
# Initial developer(s): Wim Hellenthal                                    #
#                                                                         #
# This library is free software; you can redistribute it and/or           #
# modify it under the terms of the GNU Lesser General Public              #
# License as published by the Free Software Foundation; either            #
# version 2.1 of the License, or (at your option) any later version.      #
#                                                                         #
# This library is distributed in the hope that it will be useful,         #
# but WITHOUT ANY WARRANTY; without even the implied warranty of          #
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU        #
# Lesser General Public License for more details.                         #
#                                                                         #
# You should have received a copy of the GNU Lesser General Public        #
# License along with this library; if not, write to the Free Software     #
# Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA #
###########################################################################
*/

/**
 * This class can be used to create a string from multiple substrings. It seems to be faster then creating a string the
 * following way: <pre>
 * var s = " mary " + "had " + "a " + "little " + "lamb";
 * </pre>
 * @constructor
 */
function StringBuffer(str)
{	
	this.text = new Array();

	/**
	 * Appends a string to the string buffer.
	 *
	 * @param str String to append.
	 */
	this.append = function (theString, theOffset, theLength) {
		var offset = (theOffset == null ? 0 : theOffset);
		var length = (theLength == null ? theString.length : theLength);
		this.text[this.text.length] = theString.substr(offset, length);
	}

	/**
	 * Returns a string representation of the string buffer content.
	 *
	 * @returns The string buffer content.
	 */
	this.toString = function ()
	{
		return this.text.join("");
	}

	/**
	 * Clears the string buffer.
	 */
	this.clear = function ()
	{
		delete this.text;
		this.text = null;
		this.text = new Array;
	}

	this.setLength = function (newLength) {this.text.length = newLength;}
	
	if(str != null) this.append(str);
}