$().ready( function(){
	
//	//disable form submission on enter
	$('form').bind("keyup", function(e) {
		  var code = e.keyCode || e.which; 
		  if (code  == 13) {               
		    e.preventDefault();
		    return false;
		  }
		});
	$('form').submit(false);
	
	$( "#choice" ).buttonset();
	
	$('#formRegoBirthDate').datepicker({
		dateFormat: "dd/mm/yy",
		beforeShow: function(input, inst){
	    	inst.dpDiv.css({marginTop: -input.offsetHeight + 'px', marginLeft: input.offsetWidth + 'px'}); 
	    }
	});
	
	/* initial hide for registration content */
	$('#rego').hide();
	$('#formSigninAlert').hide();
	$('#formRegoAlert').hide();
	
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
     	var loginDataString = "ajax=authenticate&data=" + JSON.stringify(login);
     	
     	$.ajax({
     		url: "controller",
            type: 'POST',
            dataType: 'json',
            data: loginDataString,
            success: function(data) {
            	if(data.success){
            		window.location.replace(data.redirect);
            	}else{
            		$('#formSigninAlert').show().html('<div class="alert alert-danger">'+data.message+'</div>');
            	}
            }
     	});
    });
	
	// validate signup form on keyup and submit
	$("#regoForm").validate({
		rules: {
			formRegoNickName: "required",
			formRegoFirstName: "required",
			formRegoLastName: "required",
			formRegoBirthDate: {
				required: true,
			},
			formRegoUsername: {
				required: true,
				minlength: 2
			},
			formRegoPassword: {
				required: true,
				minlength: 5
			},
			formRegoConfirmPassword: {
				required: true,
				minlength: 5,
				equalTo: "#formRegoPassword"
			},
			formRegoEmail: {
				required: true,
				email: true
			},
			formRegoAddress : {
				required: true,
				minlength: 5
			},
			formRegoCreditCardNo:{
				required: true,
				creditcard: true
			}
		},
		messages: {
			formRegoFirstName: "Please enter your First Name",
			formRegoLastName: "Please enter your Last Name",
			formRegoNickName: "Please enter your Nick Name",
			formRegoBirthDate: {
				required: "Please enter your Birth Date",
			},
			formRegoAddress: {
				required: "Please enter your Address",
				minlength: "Your address must consist of at least 5 characters"
			},
			formRegoUsername: {
				required: "Please enter a username",
				minlength: "Your username must consist of at least 2 characters"
			},
			formRegoPassword: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long"
			},
			formRegoConfirmPassword: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long",
				equalTo: "Please enter the same password as above"
			},
			formRegoEmail: {
				required: "Please enter your email address",
				email: "Please enter a valid email address"
			},
			formRegoCreditCardNo: {
				required: "Please enter your credit card details",
				creditcard : "Please enter a valid credit card <br/>(e.g. 446-667-651)"
			}
		},
		submitHandler: function(form) {
			var register = new Object();
			register.username = $('#formRegoUsername').val();
			register.password = $('#formRegoPassword').val();
			register.confirmPassword = $('#formRegoRepeatPassword').val();
			register.nickName = $('#formRegoNickName').val();
			register.firstName = $('#formRegoFirstName').val();
			register.lastName = $('#formRegoLastName').val();
			register.email = $('#formRegoEmail').val();
			register.birthdate = $('#formRegoBirthDate').val();
			register.address = $('#formRegoAddress').val();
			register.creditcardnum = $('#formRegoCreditCardNo').val();
			
			var registerDataString = "ajax=addUser&data=" + JSON.stringify(register);			
			$.ajax({
	     		url: "controller",
	            type: 'POST',
	            dataType: 'json',
	            data: registerDataString,
	            success: function(data) {
	            	if(data.success){
	            		$('#formRegoAlert').show().html('<div class="alert alert-danger">'+data.message+'</div>');
	            		setTimeout(function() {
	            			window.location.href = data.redirect;
	            		}, 2000);
	            	}else{
	            		$('#formRegoAlert').show().html('<div class="alert alert-danger">'+data.message+'</div>');
	            	}
	            }
	     	});
		}
	});
	
	
	
});