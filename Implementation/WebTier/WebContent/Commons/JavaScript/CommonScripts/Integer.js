// Integer.js

/**
* Integer for Java 
* offen used for HashMap,HashSet, as same to Java!
*/
function testInteger(){
 alert("Integer test begin:");
 try{
  var i=new Integer(12);
  var j=new Integer(14);
  var k=new Integer(12);
  assert(i.intValue()==12);
  assert(i.toString()=="12");
  assert(i.hashCode()==12);
  assert(i.equals(k));
  assert(i.compareTo(j)<0);
 }
 catch(e){
  alert(e);
 }
 alert("Integer test end");
}
function Integer(num){
 private:
 this.value=parseInt(num);
 public:

 this.intValue=intValue;
 function intValue(){
  return this.value;
 }

 this.toString=toString;
 function toString(){
  return ""+this.value;
 }

 this.hashCode=hashCode;
 function hashCode(){
  return this.value;
 }

 this.equals=equals;
 function equals(obj){
  return this.intValue()==obj.intValue();
 }
 
 this.compareTo=compareTo;
 function compareTo(obj){
  var i=this.value-obj.value;
  return (i==0)?0:(i>0)?1:-1;
 }
}
