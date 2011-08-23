// map.js
// author: Pedro Ribeiro Junior
// version: $Id: Map.js,v 1.1 2005/01/02 22:54:31 pedro761307 Exp $

/**
*/
  function Map(){
    var keys = new Array();
    this.contains = function(key){
       AssertUtil.assertParamNotNull(key, "key");
       var entry = findEntry(key);
       return !(entry == null || entry instanceof NullKey);
    }
    this.get = function(key) {
     var entry = findEntry(key);
     if ( !(entry == null || entry instanceof NullKey) )
        return entry.value;
      else
        return null;
    }
    this.put = function(key, value) {
      AssertUtil.assertParamNotNull(key, "Map.put.key");
      AssertUtil.assertParamNotNull(value, "Map.put.value");
      var entry = findEntry(key);
      if (entry){
        entry.value = value;
      } else {
        addNewEntry(key, value);
      }
    };
    this.remove = function (key){
      AssertUtil.assertParamNotNull(key, "key");
      for (var i=0;i<keys.length;i++){
        var entry = keys[i];
        if (entry instanceof NullKey) continue;
        if (entry.key == key){
            keys[i] = NullKey;
        }
      }        
    }
    function findEntry(key){
      for (var i=0;i<keys.length;i++){
        var entry = keys[i];
        if (entry instanceof NullKey) continue;
        if (entry.key == key){
            return entry
        }
      }
      return null;
    }
    function addNewEntry(key, value){
        var entry = new Object();
        entry.key = key;
        entry.value = value;
        keys[keys.length] = entry; 
    }
  }
  //replace the entries of map in key array, removing the former value;  
  function NullKey(){
  }
  new NullKey();

