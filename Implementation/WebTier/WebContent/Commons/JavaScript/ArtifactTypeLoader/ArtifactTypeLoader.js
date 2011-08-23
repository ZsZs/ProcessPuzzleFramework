// ArtifactTypeLoader.js

// ArtifactTypeLoader
function ArtifactTypeLoader() {
	var TYPE_TAG_NAME = "artifactType";
	var TYPE_ID_ATTRIBUTE_NAME = "name";

	//private instance variables
	var typeMap = new HashMap();

	//public accessors methods
	this.getCountOfTypes = function() {return typeMap.size();}
	this.getTypesAsMap = function() {return typeMap;}

	//public mutators methods
	this.loadTypesFromFile = _LoadTypesFromFile;
	this.loadTypesFromXml = _LoadTypesFromXml;
	this.put = _Put;
	this.get = _Get;
	this.contains = _Contains;
	this.remove = _Remove;

	//private methods
	function _LoadTypesFromFile( xmlResourceName ) {
		var xml = new XmlResource( xmlResourceName );
		_LoadTypesFromXml( xml );
	}
	
	function _LoadTypesFromXml( xml ) {
		var artifactTypes = xml.getElementsByTagName(TYPE_TAG_NAME);
		for(var i=0; i<artifactTypes.length; i++) {
			var aType = artifactTypes[i];
			var typeName = aType.getAttribute(TYPE_ID_ATTRIBUTE_NAME);
			if(typeName!=null && typeName!=="")
				typeMap.put(typeName,aType);
		}
	}

	function _Put(typeName,type) {
		return typeMap.put(typeName,type);
	}

	function _Get(typeName) {
		return typeMap.get(typeName);
	}

	function _Contains(typeName) {
		return typeMap.containsKey(typeName);
	}

	function _Remove(typeName) {
		return typeMap.remove(typeName);
	}
}