// UndefinedTreeDefinitionException.js

// constructor
function UndefinedTreeDefinitionException( ) {
   var MESSAGE = "TreeWidget: Could not find out tree definition."
   var fullText = MESSAGE;
   this.text = fullText;
   this.error = new Error(fullText);
   this.error.name = "UndefinedTreeDefinitionException";
}