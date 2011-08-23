// InvalidXmlResponseException.js

// constructor
function InvalidXmlResponseException( actionName ) {
   var MESSAGE = "The retrieved xml response in invalid."
   var fullText = MESSAGE + " Action: " + actionName;
   this.text = fullText;
   this.error = new Error(fullText);
   this.error.name = "InvalidXmlResponseException";
}