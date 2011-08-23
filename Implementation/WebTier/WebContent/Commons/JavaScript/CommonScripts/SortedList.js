// SortedList.js

/**
* Binary Search List
*/
function testSortedList(){
 alert("SortedList test begin:");
 try{
  var list=new ArrayList();
  var s=new SortedList();
  s.add(new JavaString("WSG"));
  assert(s.size()==1);
  assert(s.indexOf(new JavaString("WSG"))==0);

  list.add(new JavaString("HELLO"));
  list.add(new JavaString("TOM"));
  list.add(new JavaString("BUSH"));
  s.add(new JavaString("SINA"));
  s.add(new JavaString("163"));
  s.add(new JavaString("CSDN"));
  s.addAll(list);
  assert(s.size()==7);
  assert(s.indexOf(new JavaString("SINA"))==4);
  assert(s.indexOf(new JavaString("SSN"))==5);
  assert(s.indexOf(new JavaString("SA"))==4);
  //var s2=s.subList(4,7);
  for(var i=0;i<s.size();i++){
   alert(s.get(i));
  }
 }
 catch(e){
  alert(e);
 }
 alert("SortedList test end");
}


function SortedList(){
//   private:
   this.buffer =new Array();
   var args=SortedList.arguments;
   if(args.length>0) this.buffer=args[0];
   this.length=this.buffer.length;
   com=null;
  
   function ListIterator(table,len){

        this.table=table;
  this.len=len;                          
        this.index=0;
  
  this.hasNext=hasNext;
  function hasNext() {
   return this.index< this.len;
        }

        this.next=next;
  function next() { 
   if(!this.hasNext())
    throw "No such Element!";
      return this.table[this.index++];
        }
 }
  
//   public:
 
   this.setComparator=setComparator;
   function setComparator(comp){
     if(this.length!=0) throw "Only can be set when list is empty";
  this.com=comp;
   }
   this.getComparator=getComparator;
   function getComparator(){
  return this.com;
   }

   this.hashCode=hashCode; 
   function hashCode(){
  var h=0;
  for(var i=0;i<this.length;i++)
   h+=this.buffer[i].hashCode();
  return h;
 }
  
 this.size=size;
 function size(){
  return this.length;
 }

 
 this.clear=clear;
 function clear(){
  this.length=0;
 }

 
 this.isEmpty=isEmpty;
 function isEmpty(){
  return this.length==0;
 }
 
 
 this.toArray=toArray;
 function toArray(){
  var copy=new Array();
  for(var i=0;i<this.length;i++){
   copy[i]=this.buffer[i];
  }
  return copy;
 }

 this.get=get;
 function get(index){
  if(index>=0 && index<this.length)
   return this.buffer[index];
  return null;
 }

 
 this.remove=remove;
 function remove(param){
  
  var index=0;
  
  if(isNaN(param)){
   index=this.indexOf(param);
  }
  else index=param;
  
  
  if(index>=0 && index<this.length){
   for(var i=index;i<this.length-1;i++)
    this.buffer[i]=this.buffer[i+1];
   this.length--;
   return true;
  }
  else return false;
 }
 
 this.add=add;
 function add(obj){
  
  var index = this.indexOf(obj);
  for(var i=this.length;i>index;){
   this.buffer[i]=this.buffer[--i];
  }

  this.buffer[index]=obj;
  this.length++; 

 }

 this.indexOf=indexOf;
 function indexOf(obj){
  
  if(this.length==0) return 0;
  
  var min=0,max=this.length-1;
  var mid=0;
 
  while(min<=max){
   
   mid = (min+max) >> 1;
   var c=0;
   if(this.com==null) c=obj.compareTo(this.buffer[mid]);
   else c=this.com.compare(obj,this.buffer[mid]);
    
   if(c==0){
    return mid;
   }
   else if(c<0){
    max=mid-1;
   }
   else{
    min=mid+1;
   }
  }
  mid =(min+max) >>1;
  return mid+1;
 }

 this.contains=contains;
 function contains(obj){
  if(this.length==0) return false;
  var min=0,max=this.length-1;
  var mid=0;
  while(min<=max){
   mid = (min+max) >> 1;
   var c=0;
   if(this.com==null) c=obj.compareTo(this.buffer[mid]);
   else  c=this.com.compare(obj,this.buffer[mid]);
   if(c==0){
    return true;
   }
   else if(c<0){
    max=mid-1;
   }
   else{
    min=mid+1;
   }
  }
  return false;
 }

 this.equals=equals;
 function equals(obj){
  if(this.size()!=obj.size()) return false;
  for(var i=0;i<this.length;i++){
   if(!obj.get(i).equals(this.buffer[i])) return false;
  }
  return true;
 }


 this.addAll=addAll;
 function addAll(list){
  var mod=false;
  for(var it=list.iterator();it.hasNext();){
   var v=it.next();
   if(this.add(v)) mod=true;
  }
  return mod;  
 }
 

 this.removeAll=removeAll;
 function removeAll(list){
  for(var i=0;i<list.size();i++){
   this.remove(this.indexOf(list.get(i)));
  }
 }
 
 
 this.retainAll=retainAll;
 function retainAll(list){
  for(var i=this.length-1;i>=0;i--){
   if(!list.contains(this.buffer[i])){
    this.remove(i);
   }
  }
 }

 this.subList=subList;
 function subList(begin,end){
  if(begin<0) begin=0;
  if(end>this.length) end=this.length;
  var newsize=end-begin;
  
  var newbuffer=new Array();
  for(var i=0;i<newsize;i++){
   newbuffer[i]=this.buffer[begin+i];
  }
  return new SortedList(newbuffer);
 }
 

 this.iterator=iterator;
 function iterator(){
  return new ListIterator(this.buffer,this.length);
 }
}

