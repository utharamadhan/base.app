$(function(){
	if($('#template').length>0){
		$('.template_part').html($('#template').clone().wrap('<div></div>').parent().html());
	}
});

/* Start create plugin jquery */
(function ( $ ) {
	/* Start for function disable class mrdy_btn */
	$.fn.disableBtn = function(options) {
		var settings = $.extend({
	        // These are the defaults.
			overlay: "mrdy_overlay",
			enabled: true,
			callback: function(param) {}
	    }, options );
		
		var elem = this;
		
		if(settings.enabled){
			if($('.'+settings.overlay).length>0){
				$('.'+settings.overlay).remove();
			}
			elem.css({'opacity':'','filter': ''});
		}else{
			if($('.'+settings.overlay).length==0){
				$('body').prepend('<div style="position: absolute; height: 100%; width: 100%; z-index: 999999999; background: transparent !important;" class="'+settings.overlay+'"></div>')
			}
			elem.css({'opacity':'0.3','filter': 'alpha(opacity=50)'});
		}
		
		settings.callback.call(this);
	};
	/* End for function disable class mrdy_btn */
	
	
	
}( jQuery ));
/* End create plugin jquery */