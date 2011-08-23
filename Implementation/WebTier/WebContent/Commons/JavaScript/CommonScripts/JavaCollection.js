// JavaCollection.js

/**
collection.js

version 1.2
author  treeroot
since   2005-5-24

Classes:
Collections
Arrays
ArrayList
SortedList extends ArrayList
HashMap
HashSet
*/

/****************
Collections
NOTE:sort() return a new List
****************/
function Collections(){}
Collections.sort=function(){
 if(arguments.length==1){
  var s=new SortedList();
  s.addAll(arguments[0]);
  return s;
 }
 else if(arguments.length==2){
  var s=new SortedList();
  s.setComparator(arguments[1]);
  s.addAll(arguments[0]);
  return s;
 }
 else 
  throw "IllegalArgument";
}
/***************
Arrays
****************/
function Arrays(){}
Arrays.asList=function(arr){
 return new ArrayList(arr);
}


//ListIterator
function ListIterator(table,len){
    this.table=table;
 this.len=len;                          
    this.index=0;
  
 this.hasNext=function() {
  return this.index< this.len;
    }

    this.next=function() { 
  if(!this.hasNext())
   throw "No such Element!";
  return this.table[this.index++];
    }
}

/********************
ArrayList
********************/
function ArrayList(){
 this.buffer=new Array();
 if(arguments.length>0) this.buffer=arguments[0];
 this.length=this.buffer.length;
}
ArrayList.prototype.hashCode=function(){
 var h=0;
 for(var i=0;i<this.lengh;i++)
  h+=this.buffer[i].hashCode();
 return h;
}
 
ArrayList.prototype.size=function(){
 return this.length;
}

ArrayList.prototype.clear=function(){
 for(var i=0;i<this.length;i++) this.buffer[i]=null;
 this.buffer.length=0;
 this.length=0;
}

ArrayList.prototype.isEmpty=function(){
 return this.length==0;
}
 
ArrayList.prototype.toArray=function(){
 var copy=new Array();
 for(var i=0;i<this.length;i++){
  copy[i]=this.buffer[i];
 }
 return copy;
}
ArrayList.prototype.get=function(index){
 if(index>=0 && index<this.length)
  return this.buffer[index];
 return null;
}

ArrayList.prototype.remove=function(param){
 var index=0;
  
 if(isNaN(param)){
  index=this.indexOf(param);
 }
 else index=param;
   
 if(index>=0 && index<this.length){
  for(var i=index;i<this.length-1;i++)
   this.buffer[i]=this.buffer[i+1];
   this.length-=1;
   return true;
 }
 else return false;
}
  
ArrayList.prototype.add=function(){
 var args=arguments;
 if(args.length==1){
  this.buffer[this.length++]=args[0];
  return true;
 }
 else if(args.length==2){
  var index=args[0];
  var obj=args[1];
  if(index>=0 && index<=this.length){
   for(var i=this.length;i>index;i--)
    this.buffer[i]=this.buffer[i-1];
   this.buffer[i]=obj;
   this.length+=1;
   return true;
  }
 }
 return false;
}

ArrayList.prototype.indexOf=function(obj){
 for(var i=0;i<this.length;i++){
  if(this.buffer[i].equals(obj)) return i;
 }
 return -1;
}

ArrayList.prototype.lastIndexOf=function(obj){
 for(var i=this.length-1;i>=0;i--){
  if(this.buffer[i].equals(obj)) return i;
 }
 return -1;
}

ArrayList.prototype.contains=function(obj){
 return this.indexOf(obj)!=-1;
}

ArrayList.prototype.equals=function(obj){
 if(this.size()!=obj.size()) return false;
 for(var i=0;i<this.length;i++){
  if(!obj.get(i).equals(this.buffer[i])) return false;
 }
 return true;
}

ArrayList.prototype.addAll=function(list){
 var mod=false;
 for(var it=list.iterator();it.hasNext();){
  var v=it.next();
  if(this.add(v)) mod=true;
 }
 return mod;  
}
 
ArrayList.prototype.containsAll=function(list){
 for(var i=0;i<list.size();i++){
  if(!this.contains(list.get(i))) return false;
 }
 return true;
}

ArrayList.prototype.removeAll=function(list){
 for(var i=0;i<list.size();i++){
  this.remove(this.indexOf(list.get(i)));
 }
}

ArrayList.prototype.retainAll=function(list){
 for(var i=this.length-1;i>=0;i--){
  if(!list.contains(this.buffer[i])){
   this.remove(i);
  }
 }
}

ArrayList.prototype.subList=function(begin,end){
 if(begin<0) begin=0;
 if(end>this.length) end=this.length;
 var newsize=end-begin;
 var newbuffer=new Array();
 for(var i=0;i<newsize;i++){
  newbuffer[i]=this.buffer[begin+i];
 }
 return new ArrayList(newbuffer);
}
ArrayList.prototype.set=function(index,obj){
 if(index>=0 && index<this.length){
  temp=this.buffer[index];
  this.buffer[index]=obj;
  return temp;
 }
}

ArrayList.prototype.iterator=function iterator(){
 return new ListIterator(this.buffer,this.length);
}

/*****************************
SortedList extends ArrayList
*****************************/
function SortedList(){
   this.com=null;
}
SortedList.prototype=new ArrayList();
SortedList.prototype.setComparator=function(comp){
 if(this.length!=0) throw "Only can be set when list is empty";
 this.com=comp;
}

SortedList.prototype.getComparator=function(){
 return this.com;
}
 
//override
SortedList.prototype.add=function(obj){
 var index = this.indexOf(obj);
 for(var i=this.length;i>index;){
  this.buffer[i]=this.buffer[--i];
 }

 this.buffer[index]=obj;
 this.length++; 
}
//override
SortedList.prototype.indexOf=function(obj){
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
//override
SortedList.prototype.contains=function(obj){
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
//override
SortedList.prototype.subList=function(begin,end){
 var sl=new SortedList();
 s1.setComparator(this.com);
 var sub=ArrayList.prototype.subList(begin.end);
 sl.addAll(sub);
 return sl;
}




/*************************
HashSet
**************************/

function HashSetIterator(ite){
    this.it=ite;
  
 this.hasNext=function() {
  return this.it.hasNext();
    }

    this.next=function() { 
  return this.it.next().getKey();
    }
}

function HashSet(){  
 this.map=new HashMap();
}
HashSet.NULL=new Number("!THIS IS NULL!");


HashSet.prototype.size=function(){
 return this.map.size();
}

HashSet.prototype.isEmpty=function() {
 return this.map.isEmpty();
}

HashSet.prototype.contains=function(o) {
 return this.map.containsKey(o);
}

HashSet.prototype.add=function(o){
 return this.map.put(o,HashSet.NULL)==null;
}

HashSet.prototype.addAll=function(set){
 var mod=false;
 for(var it=set.iterator();it.hasNext();){
  if(this.add(it.next())) mod=true;
 }
 return mod;
}

HashSet.prototype.remove=function(o) {
 return this.map.remove(o).equals(HashSet.NULL);
}

HashSet.prototype.clear=function() {
 this.map.clear();
}

HashSet.prototype.iterator=function(){
 return new HashSetIterator(this.map.iterator());
}

HashSet.prototype.equals=function(o) {
 if(!this.typeMatches(o)) return false;
 if (o.size() != this.size()) return false;
 for(var it=this.iterator();it.hasNext();){
  if(!o.contains(it.next())) return false;
 }
 return true;
}

HashSet.prototype.hashCode=function() {
 var h=0;
 for(var it=this.iterator();it.hasNext();){
  h+=it.next().hashCode();
 }
 return h;
}

HashSet.prototype.toArray=function(){
 var arr=new Array();
 var i=0;
 for(var it=this.iterator();it.hasNext();){
  arr[i++]=it.next();
 }
 return arr;
}

