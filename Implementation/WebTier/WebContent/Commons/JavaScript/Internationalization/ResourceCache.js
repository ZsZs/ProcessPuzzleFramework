/*
name: ResourceCache
script: ResourceCache.js
description: Mantains a HashMap for internationalization resource items.
copyright: (c) 2010 IT Codex Llc., <http://itkodex.hu/>.
license: MIT-style license.

todo:
  - 

requires:
  - MooTools/mootools-core.1.3
  - ProcessPuzzle/JavaCompatibility
  - ProcessPuzzle/JavaCollection

provides: [ProcessPuzzle.ResourceCache]
*/

var ResourceCache = new Class({

	initialize : function(){
	//parameter assertions

	//private instance variables
  	this.resources = new HashMap();
	this.self = this;
	},
	
	clear : function(){
	  this.resources.clear(); 
	},

	//public accessors methods
	get : function( name, type ) {
	    var resourceKey = new ResourceKey( name, type );
	    if( !this.resources.containsKey( resourceKey ) ) {
	        throw new IllegalArgumentException( "no such key: " + resourceKey );
	    }
	    return this.resources.get( resourceKey );
	},
	
	//public mutators methods
	put : function( resourceKey, resourceValue ){
		this.resources.put( resourceKey, resourceValue );
	}

	//private methods
});
