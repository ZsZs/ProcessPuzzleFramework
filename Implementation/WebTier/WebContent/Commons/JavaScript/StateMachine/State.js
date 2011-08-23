var State = new Class({
	initialize: function( name, description ){
		AssertUtil.assertParamNotNull( name, "name" );
		
		this.name = name;
		this.description = description;
		this.parentState = null;
	}
});