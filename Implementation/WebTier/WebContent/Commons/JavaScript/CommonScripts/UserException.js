/**
ProcessPuzzle User Interface
Backend agnostic, desktop like configurable, browser font-end based on MochaUI.
Copyright (C) 2011  Joe Kueser, Zsolt Zsuffa

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

//This page defines a couple of custom exceptions

function CustomError( message ) {
	var error = new Error(message);
	this.toString = function(){
	    return error.toString();
	}
	
	this.printStackTrace = function(){
		var result = "";
		for (var i =0;i<stackTrace.length;i++) {
			result += "\t-" + stackTrace[i] + "\n"; 
		}
		return result;
 	}
}

function AssertException(message){
	this.base = CustomError;
	this.base("( AssertException ) " + message);
}
AssertException.prototype = CustomError;

function DuplicatedItemException( itemName ) {
   this.error = new Error("The given item: '" + itemName + "' is duplicated.");
   this.error.name = "DuplicatedItemException";
}

function FileNotFoundException(message, source){
	this.base = CustomError;
	this.base("( FileNotFoundException ) " + message);
}
FileNotFoundException.prototype = CustomError;

function IllegalArgumentException(message) {
	this.base = CustomError;
	this.base("( IllegalArgumentException ) " + message);
}
IllegalArgumentException.prototype = CustomError;

function IllegalMethodCallException( message ) {
	this.base = CustomError;
	this.base("( IllegalArgumentException ) " + message );
}
IllegalMethodCallException.prototype = CustomError;

function IndexOutOfBoundsException(message, source){
	this.base = CustomError;
	this.base("( IllegalArgumentException ) " + message);
}
IndexOutOfBoundsException.prototype = CustomError;

function InvalidParameterException (parameters) {
   this.error = new Error("One or more parameters: '" + parameters + "' passed to the funtion is invalid.");
   this.error.name = "InvalidParameterException";
}

function MissingViewsInDocumentProperyFileException (propertyFile) {
   this.error = new Error("The document property file: '" + propertyFile + "' doesn't contains 'Views' section.");
   this.error.name = "MissingViewsInDocumentProperyFileException";
}

function MissingArtifactTypeInDocumentProperyFileException (propertyFile) {
   this.error = new Error("The artifact type definition file: '" + propertyFile + "' doesn't contains 'ArtifactType' section.");
   this.error.name = "MissingArtifactTypeInDocumentProperyFileException";
}

function PreconditionFailureException (conditionStatement) {
   this.error = new Error("Precondition: '" + conditionStatement + "' was not satisfied.");
   this.error.name = "PreconditionFailureException";
}

function UndefinedItemException( itemName ) {
	   this.error = new Error("The given item: '" + itemName + "' is not found.");
	   this.error.name = "UndefinedItemException";
}

function UndefinedXmlResourceException( xmlResourceName ) {
   this.error = new Error("The given xml resource: '" + xmlResourceName + "' doesn't exists.");
   this.error.name = "UndefinedXmlResourceException";
}

function UndefinedDocumentPropertyFileException (documentName) {
   this.error = new Error("The document: '" + documentName + "' doesn't have corresponding property (xml) file.");
   this.error.name = "UndefinedDocumentPropertyFileException";
}

function UndefinedArtifactTypeException (artifactType, definitionFile) {
   this.error = new Error("The artifact type: '" + artifactType + "' can't be found in : " + definitionFile + " file.");
   this.error.name = "UndefinedArtifactTypeException";
}

function UserException (message, method, exceptionName) {
   this.message=message;
   this.method=method;
   this.name = (exceptionName ? exceptionName : "UserException");
}
