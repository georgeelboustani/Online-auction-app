$(function(){
	$( "#choice" ).buttonset();
	$('#dp').datepicker({
	    beforeShow: function(input, inst)
	    { inst.dpDiv.css({marginTop: -input.offsetHeight + 'px', marginLeft: input.offsetWidth + 'px'}); }
	});
	/* initial hide for registration content */
	$('#rego').hide();
	
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
		login.signinRememberme = $('#formSigninRememberme').is(':checked');
     	var dataString = "ajax=login&data=" + JSON.stringify(login);
     	alert(JSON.stringify(dataString));
     	
     	$.ajax({
     		url: "ControllerServlet",
            type: 'POST',
            dataType: 'json',
            data: dataString,
            success: function (data, status) {
            	
            },error:function(data,status,er) {
            	
            },beforeSend: function(jqXHR, settings){
            	
            },complete: function(jqXHR, textStatus){
            	
            }
     	});
    });
});