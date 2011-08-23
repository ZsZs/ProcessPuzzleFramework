// JavaScript Document
// Generic purpose collection class

function Collection () {
	var objectArray = new Array();	  	//Associative array
//	var count = 0;						//Total numbers of items
	var cursor = -1;					//initialize cursor
	var error = false;
	
	//public accessors
	this.getCountOfObjects = function () {return objectArray.length;}
	
	//public mutatators
    this.add = _Add;					//adds an item and key pair to the collection 
	this.removeAll = function() {objectArray = new Array();}	//removes all item and key pairs
	this.remove = _remove;				//removes the item and key pair
	this.exists = _exists;				//returns true if the key exist, otherwise false
	this.item = _item;					//returns the item associated with the key
	this.getItemByIndex = _GetItemByIndex;	
	this.getNext = _GetNext;			//returns next object from the collection
	this.getIndexOf = _GetIndexOf;		//returns the index of given object
	this.moveFirst = function() {cursor = -1;}		//moves cursor to the first object
	this.moveLast = function() {cursor = count -1;}	//moves cursor to the last object

	function _Add(theKey, theObject) {
		var entry = new CollectionEntry(theKey, theObject);
		var index = _GetIndexOfKey(theKey);
		if(index) {
			objectArray[index] = entry;
		}else {
			objectArray[objectArray.length] = entry;
		}
	}

	//returns the object in the given position
	function _GetItemByIndex (theIndex) {
		if(theIndex < 0 || theIndex >= objectArray.count) returnValue = null;
		else return objectArray[theIndex].value;

/*
			var i = 0;
			for(var property in objectArray) {
				 //check if the property is not a property of Object class.
				if ((Object[property] == null) && (objectArray[property] != null)) {
					if (theIndex == i) {
//						returnValue = objectArray[property];
						return objectArray[property];
					}
					i++;
				}
			}
*/
	}

	//returns the object next to the cursor
	function _GetNext () {
		if(!(cursor >= this.getCountOfObjects() -1)) {	return _GetItemByIndex(++cursor);}
		else return null;
	}

	function _exists (theKey) {
		if( theKey == null || _GetIndexOfKey(theKey) == null ) return false;
		else return true;
	}

	function _item (theKey) {
		var index = _GetIndexOfKey(theKey);

		if(index != null && index >= 0) return objectArray[index].value;
		else return null;
	}

	function _GetIndexOf (theObject) {
		for( var i = 0; i < objectArray.length; i++ ) {
			if( objectArray[i].value == theObject) return i;
		}
		return null;
/*
		var i = 0;
		for (var property in objectArray) {
			//check if the property is not a property of Object class.
			if ((Object[property] == null) && (objectArray[property] != null)) { 
				if (theObject == objectArray[property]) {return i;}
				else i++;
			}
		}
		return 'undefined';
*/
	}

	function _GetIndexOfKey (theKey) {
		for( var i = 0; i < objectArray.length; i++ ) {
			if( objectArray[i].key == theKey) return i;
		}
		return null;
		
/*
		var i = 0;
		for (var property in objectArray) {
			//check if the property is not a property of Object class.
			if ((Object[property] == null) && (objectArray[property] != null)) { 
				if (theKey == property) {return i;}
				else i++;
			}
		}
		return null;
*/
	}

	function _remove (theKey) {
		var index = _GetIndexOfKey(theKey);
		if(index != null) objectArray.splice( index, 1 );
		else return null;
/*
		if(theKey == null || objectArray[theKey] == null) return null;

		var theObject = objectArray[theKey];
		objectArray[theKey] = null;
		objectArray.splice(_GetIndexOfKey(theKey), 1);
		--count;
		return theObject;
*/
	}	
}

function CollectionEntry (key, value) {
	AssertUtil.assertParamNotNull(key, "key");
	AssertUtil.assertParamNotNull(value, "value");

	this.key = key;
	this.value = value;
}