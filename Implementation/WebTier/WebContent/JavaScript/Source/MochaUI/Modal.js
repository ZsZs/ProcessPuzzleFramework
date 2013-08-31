/*
---

name: Modal

script: Modal.js

description: Create modal dialog windows.

copyright: (c) 2007-2009 Greg Houston, <http://greghoustondesign.com/>.	

license: MIT-style license.	

See Also: <Window>

requires:
  - MochaUI/MUI
  - MochaUI/MUI.Windows

provides: [MUI.Modal]

...
*/

//= require ../MochaUI/Core.js
//= require ../MochaUI/Dock.js
//= require ../MochaUI/Layout.js
//= require ../MochaUI/Window.js

MUI.files[MUI.path.source + 'Window/Modal.js'] = 'loaded';

MUI.Modal = new Class({

	Extends: MUI.Window,
	
	options: {
		type: 'modal'
	},	
	
	initialize: function(options){
		
		if (!$('modalOverlay')){
			this.modalInitialize();
		
			window.addEvent('resize', function(){
				this.setModalSize();
			}.bind(this));
		}		
		this.parent(options);

	},
	modalInitialize: function(){
		var modalOverlay = new Element('div', {
			'id': 'modalOverlay',
			'styles': {
				'height': document.getCoordinates().height,				
				'opacity': .6
			}
		}).inject(document.body);
		
		modalOverlay.setStyles({
				'position': Browser.Engine.trident4 ? 'absolute' : 'fixed'
		});
		
		modalOverlay.addEvent('click', function(e){
			var instance = MUI.Windows.instances.get(MUI.currentModal.id);
			if (instance.options.modalOverlayClose == true) {
				MUI.closeWindow(MUI.currentModal);
			}
		});
		
		if (Browser.Engine.trident4){
			var modalFix = new Element('iframe', {
				'id': 'modalFix',
				'scrolling': 'no',
				'marginWidth': 0,
				'marginHeight': 0,
				'src': '',
				'styles': {
					'height': document.getCoordinates().height
				}
			}).inject(document.body);
		}

		MUI.Modal.modalOverlayOpenMorph = new Fx.Morph($('modalOverlay'), {
			'duration': 150
		});
		MUI.Modal.modalOverlayCloseMorph = new Fx.Morph($('modalOverlay'), {
			'duration': 150,
			onComplete: function(){
				$('modalOverlay').hide();
				if (Browser.Engine.trident4){
					$('modalFix').hide();
				}
			}.bind(this)
		});
	},
	setModalSize: function(){
		$('modalOverlay').setStyle('height', document.getCoordinates().height);
		if (Browser.Engine.trident4){
			$('modalFix').setStyle('height', document.getCoordinates().height);
		}
	}

});
