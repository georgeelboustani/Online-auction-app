$().ready( function(){
	$( "#choice" ).buttonset();
	$('#dp').datepicker({
	    beforeShow: function(input, inst)
	    { inst.dpDiv.css({marginTop: -input.offsetHeight + 'px', marginLeft: input.offsetWidth + 'px'}); }
	});
	/* initial hide for registration content */
	$('#rego').hide();
	$('#formSigninAlert').hide();
	
	/* radio button switch between content */
	$('#choice').click(function(){
		if($('#choice1').is(':checked')){
			$('#rego').hide("blind",1000).delay(1200);
			$('#login').show("blind",1000);
		}else if($('#choice2').is(':checked')) {
			$('#login').hide("blind",1000).delay(1200);
			$('#rego').show("blind",1000);
		}
	});

	$('#loginSubmit').click(function(event) {
		var login = new Object();
		login.signinUsername = $('#formSigninUsername').val();
		login.signinPassword= $('#formSigninPassword').val();
		
     	var dataString = "ajax=login&data=" + JSON.stringify(login);
     	
     	$.ajax({
     		url: "ControllerServlet",
            type: 'POST',
            dataType: 'json',
            data: dataString,
            success: function(data) {
            	if(data.success){
            		window.location.replace(data.redirect);
            	}else{
            		$('#formSigninAlert').show().html('<div class="alert alert-danger">'+
            				data.message+'</div>');
            	}
            }
     	});
    });
});