// XmlResource.js
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

var XmlResource = new Class({
   Extends: Request,

   options: {
      nameSpaces: "xmlns:pp='http://www.processpuzzle.com'",
      
      onComplete: function( responseAsText ) { 
         if( responseAsText ) {
            this.xmlAsText = responseAsText;
            
            if( this.options.parseOnComplete ) {
               this.xmlDoc = Sarissa.getDomDocument();  
               this.xmlDoc = (new DOMParser()).parseFromString( responseAsText, "text/xml" );  
            }
         }
      },
      
      onException : function( headerName, value ) {
         //console.log( "Request header failed: '" + value + "'" );
      },
      
      onFailure : function( xhr ) {
         //console.log( "Request failed. status: '" + xhr.status + "'" );
      },
      
      parseOnComplete : true
   },
   
   // Constructor
   initialize: function ( uri, options ) {
      // parameter assertions
      AssertUtil.assertParamNotNull( uri, "uri");
      
      this.parent( options );
      this.options.url = uri;
      this.options.method = 'get';
      this.options.async = false;
      this.xmlAsText = null;
      this.xmlDoc = null;

      this.refreshResource();
      
      //console.log('XmlResource is initialized.');
   },
   
   // Public accessor and mutator methods
   determineAttributeValue : function( xmlElement, attributeName ) {
      return XmlResource.determineAttributeValue( xmlElement, attributeName );
   },
   
   determineNodeText : function( xmlElement ) {
      return XmlResource.determineNodeText( xmlElement );
   },
   
   isSuccess: function() { // Determines if an XMLHttpRequest was successful or not
       try {
           // IE error sometimes returns 1223 when it should be 204 so treat it as success, see #1450
           return !this.status && location.protocol === "file:" ||
               // Opera returns 0 when status is 304
               ( this.status >= 200 && this.status < 300 ) ||
               this.status === 304 || this.status === 1223 || this.status === 0;
       } catch(e) {}

       return false;
   },
   
   selectNode : function( selector, subNode ) {
      var selectedNodes = this.selectNodes( selector, subNode );
      if( selectedNodes )  return selectedNodes[0];
      else return null;
   }, 
   
   selectNodes : function( selector, subNode ) {
      var subjectNode = subNode != null ? subNode : this.xmlDoc;
      
      var foundXmlNodes = null;
      try {
         var foundXmlNodes = subjectNode.selectNodes( selector );
      }catch(e) {
         //console.log( e );
         //console.log( "Namespaces: " + subjectNode.getProperty( "SelectionNamespaces" ));
         return null;
      }
      return foundXmlNodes;
   },
   
   selectNodeText : function( selector, subNode ) {
      var selectedElements = this.selectNodes( selector, subNode );
      if( selectedElements.length > 0 && selectedElements[0] ) {
         return selectedElements[0].data;
      }else return null;
   }, 
   
   refreshResource: function(){
      try {
         this.send( this.options.url );
      }catch( e ){
         throw new UndefinedXmlResourceException( this.options.url );
      }
      
      if( this.options.parseOnComplete && this.xmlDoc == null )
         throw new UndefinedXmlResourceException( this.options.url );
      
      if( this.xmlDoc ) {
         this.xmlDoc.setProperty("SelectionNamespaces", "xmlns:xsl='http://www.w3.org/1999/XSL/Transform'  " + this.options.nameSpaces ); 
         this.xmlDoc.setProperty("SelectionLanguage", "XPath");
      }
   },
   
   transform: function( xslt ){},
   
   // Properties
   getDocument: function() { return this.xmlDoc; },
   getUri: function() { return this.options.url; },
   getXmlAsText: function() { return this.xmlAsText; },
   isAsync: function() { return this.options.async; }
   
   //Private helper methods
});

//Static methods
XmlResource.determineAttributeValue = function( xmlElement, attributeName ) {
   var attributeElement = xmlElement.selectNodes( "@" + attributeName );
   if( attributeElement && attributeElement.length == 1 ) return attributeElement[0].nodeValue;
   else return null;
};

XmlResource.determineNodeText = function( xmlElement ) {
   if( xmlElement ) return Sarissa.getText( xmlElement );
   else return null;
};

XmlResource.selectNode = function( selector, xmlElement ){
   var selectedNodes = XmlResource.selectNodes( selector, xmlElement );
   if( selectedNodes )  return selectedNodes[0];
   else return null;
};

XmlResource.selectNodes = function( selector, xmlElement ){
	var selectedElements = null;
	try{
		selectedElements = xmlElement.selectNodes( selector );
	}catch( e ){
		log4javascript.getDefaultLogger().debug( "Selector: '" + selector + "' caused excection: " + e.message );
		return null;
	}
	return selectedElements;
};

XmlResource.selectNodeText = function( selector, xmlElement, nameSpaces ) {
   var selectedElement = XmlResource.selectNode( selector, xmlElement, nameSpaces );
   return XmlResource.determineNodeText( selectedElement );
};

Browser.Request = function(){
   return $try( function(){
      if( Browser.Engine.trident && window.location.protocol == "file:" )
         return new ActiveXObject('Microsoft.XMLHTTP');
      else 
         return new XMLHttpRequest();
   }, function(){
         return new ActiveXObject('MSXML2.XMLHTTP');
   }, function(){
      return new ActiveXObject('Microsoft.XMLHTTP');
   });
};

function XMLDocument () {
   var xmlDoc = null;

   if(document.implementation && document.implementation.createDocument)
   {  xmlDoc = document.implementation.createDocument("", "", null);

   }
   else if( window.ActiveXObject )
   {  xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
      xmlDoc.async = false;
   }
   else
   {  alert('Your browser can\'t handle this script');
      return;
   }
   
   return xmlDoc;
}

function TransformXML( xmlFileName, xslFileName ) {
   var xml = new XmlResource( xmlFileName );
   var xsl = new XmlResource( xslFileName );
   var resultXml = XMLDocument();
   xml.transformNodeToObject(xsl,resultXml);
   return resultXml;
}

function XMLTransformator( xmlFileName, xslFileName ) {
   var sourceXML = new XML();
   sourceXML.load(  xmlFileName );
   
   var xslt = new XSLT( xslFileName );
   
   var resultXML = new XML();
   
   this.getSourceXML = function() { return sourceXML; }
   this.getResultXML = function() { return resultXML; }
   this.transform = _Transform;
   
   function _Transform() {
      resultXML = new XML( xslt.transform( sourceXML ));
      return resultXML.getDOM();
   }
}

function RemoveWhitespaceNodesFromXML( xml ) {
   var notWhitespace = /\S/;
   for( var i=0; i < xml.childNodes.length; i++ ) {
      if(xml.childNodes[i].nodeType == 3 && !notWhitespace.test(xml.childNodes[i].nodeValue)) {
         xml.removeChild(xml.childNodes[i]);
         i--;
      } else RemoveWhitespaceNodesFromXML(xml.childNodes[i]);
   }
}