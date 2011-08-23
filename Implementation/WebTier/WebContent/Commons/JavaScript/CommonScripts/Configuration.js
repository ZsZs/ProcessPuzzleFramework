// Configuration.js

// Constructor
function Configuration() {
//private contants

//private instance variables
	var defaultLocale;
	var possibleLanguages = new Collection();
	var bundlePath;
	var locale;
	var properties = new Collection();

//public accessor methods
	this.getDefaultLocale = function() {return defaultLocale;}
	this.getPossibleLanguages = function () {return possibleLanguages;}
	this.getBundlePath = function() {return bundlePath;}
	this.getLocale = function() {return locale;}
	this.getProperty = function (key) {return properties.item(key);}

//public mutator methods
	this.setDefaultLocale = function(locale) {defaultLocale = locale;}
	this.addPossibleLanguage = function(language) {possibleLanguages.add(language,"");}
	this.setBundlePath = function(newBundlePath) {bundlePath = newBundlePath;}
	this.setLocale = function(newLocale) {locale = newLocale;}
	this.addProperty = function(key, value) {properties.add(key, value);}

//private methods
}