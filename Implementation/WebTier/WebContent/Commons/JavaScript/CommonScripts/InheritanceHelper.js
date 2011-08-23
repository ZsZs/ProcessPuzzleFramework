// InheritanceHelper.js

Object.prototype.parent = function(type) {
  var p = new Object();
  for (x in type.prototype) {
    if (x != "parent") {
      p[x.toString()] = this[x.toString()];          
    }
  }   
  return p;  
}
