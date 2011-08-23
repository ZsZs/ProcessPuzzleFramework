// JavaString.js

/**
use as java.lang.String for java programmer!
becasuse String is a Object of JavaScirpt,we named it as JavaString!
String is used most commonly in JavaScript
we can use JavaString in ArrayList,HashMap,HashSet etc!
*/
function testJavaString(){
 alert("JavaStrint test begin:");
 try{
  var javaStr=new JavaString("Hello World");
  
  assert(javaStr.length()==11);
  
  assert(javaStr.charAt(2)=='l');
  
  assert(javaStr.equals(new JavaString("Hello World")));
  
  assert(javaStr.equalsIgnoreCase("hello world"));
  
  
  assert(javaStr.compareTo("Hello")>0);
  assert(javaStr.compareTo("MX")<0);
  assert(javaStr.compareTo("Hello World")==0);
  
  assert(javaStr.startsWith("Hello "));
  
  assert(javaStr.endsWith("orld"));
  
  assert(javaStr.indexOf("o")==4);
  
  assert(javaStr.lastIndexOf("o")==7);
  
  assert(javaStr.concat(" WSG").equals(new JavaString("Hello World WSG")));

  assert(javaStr.replace("o","").equals(new JavaString("Hell World")));

  assert(javaStr.replaceAll("o","").equals(new JavaString("Hell Wrld")));

  assert(javaStr.matches("Hell.+"));

  assert(javaStr.split(" ").length==2);
  assert(javaStr.split("o")[1]==" W");
  assert(javaStr.toCharArray().length==11);
  assert(javaStr.toCharArray()[4]=="o");

  assert(javaStr.toString()=="Hello World");
  assert(javaStr.toUpperCase().equals(new JavaString("HELLO WORLD")));
  assert(javaStr.toLowerCase().equals(new JavaString("hello world")));

 }
 catch(e){
  alert(e);
 }
 alert("JavaString test end");
}

function JavaString(val){
    private:
 this.value=val;
 
   public:
 this.length=length;
 function length(){
  return this.value.length;
 }
    this.charAt=charAt;
 function charAt(index){
  return this.value.charAt(index);
 }

    this.equals=equals;
 function equals(str){
  return this.value==str.value;
 }
 
    this.equalsIgnoreCase=equalsIgnoreCase;
 function equalsIgnoreCase(str){
  return this.value.toUpperCase()==str.toUpperCase();
 }
 
 this.compareTo=compareTo;
 function compareTo(str){
  if(this.value==str.value) return 0;
  else if(this.value>str.value) return 1;
  else return -1;
 }
    
 this.compareToIgnoreCase=compareToIgnoreCase;
 function compareToIgnoreCase(str){
  var a=this.value.toUpperCase();
  var b=str.toUpperCase();
  if(a==b) return 0;
  else if(a>b) return 1;
  else return -1;
 }
    
    this.startsWith=startsWith;
 function startsWith(prefix){
  return this.value.substring(0,prefix.length)==prefix;
 }
 
    this.endsWith=endsWith;
 function endsWith(suffix){
  return this.value.substring(this.value.length-suffix.length)==suffix;
 }
 
 this.hashCode=hashCode;
 function hashCode(){
  var h=0;
  for (var i = 0; i < this.value.length; i++) {
   h = 31*h + this.value.charCodeAt(i);
  }
  return h;
 }
   
 this.indexOf=indexOf;
 function indexOf(ch){
  return this.value.indexOf(ch);
 }

    this.lastIndexOf=lastIndexOf;   
    function lastIndexOf(ch){
  return this.value.lastIndexOf(ch);
 }

 
 this.substring=substring;
 function substring(){
  var args=substring.arguments;
  var begin=args[0];
  
  if(args.length>1) return new JavaString(this.value.substring(begin,args[1]));
  else return new JavaString(this.value.substring(begin));
 }


    this.concat=concat;
 function concat(str){
  return new JavaString(this.value+str);
 }
 
 this.replace=replace;
 function replace(oldStr,newStr){
  return new JavaString(this.value.replace(oldStr,newStr));
 }
 
    
 this.matches=matches;
 function matches(regex){
  return this.value.match(regex)!=null;
 }
 
    this.replaceFirst=replaceFirst;
 function replaceFirst(regex,replacement){
  return new JavaString(this.value.replace(regex,replacement)); 
 }
  
    this.replaceAll=replaceAll;
 function replaceAll(regex,replacement){
  var va=this.value;
  var temp=va.replace(regex,replacement);
  while(temp!=va){
   va=temp;
   temp=va.replace(regex,replacement);
  }
  return new JavaString(va);
 }
  
    
    this.split=split;
 function split(sep){
  return this.value.split(sep);
 }
  
    this.toLowerCase=toLowerCase;
 function toLowerCase(){
  return new JavaString(this.value.toLowerCase());
 }

    this.toUpperCase=toUpperCase;
 function toUpperCase(){
  return new JavaString(this.value.toUpperCase());
 }

    this.toCharArray=toCharArray;
 function toCharArray(){
  var charArr=new Array();
  for(var i=0;i<this.value.length;i++) charArr[i]=this.value.charAt(i);
  return charArr;
 }
 
 this.toString=toString;
 function toString(){
  return this.value;
 } 
}

