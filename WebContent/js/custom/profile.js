$().ready( function(){

	$('form').submit(false);
	
	$('#formProfileBirthDate').datepicker({
		dateFormat: "yyyy-mm-dd",
		beforeShow: function(input, inst){
	    	inst.dpDiv.css({marginTop: -input.offsetHeight + 'px', marginLeft: input.offsetWidth + 'px'}); 
	    }
	});
	
	$("#profileForm").validate({
		rules: {
			formProfileNickName: "required",
			formProfileFirstName: "required",
			formProfileLastName: "required",
			formProfileBirthDate: {
				required: true,
			},
			formProfileAddress : {
				required: true,
				minlength: 5
			},
			formProfileCreditCardNo:{
				required: true,
				creditcard: true
			},
			formProfilePassword: {
				required: true,
				minlength: 5
			},
			formProfileConfirmPassword: {
				required: true,
				minlength: 5,
				equalTo: "#formProfilePassword"
			},
			formProfileEmail: {
				required: true,
				email: true
			},
			
		},
		messages: {
			formProfileFirstName: "Please enter your First Name",
			formProfileLastName: "Please enter your Last Name",
			formProfileNickName: "Please enter your Nick Name",
			formProfileBirthDate: {
				required: "Please enter your Birth Date"
			},
			formProfileAddress: {
				required: "Please enter your Address",
				minlength: "Your address must consist of at least 5 characters"
			},
			formProfileCreditCardNo: {
				required: "Please enter your credit card details",
				creditcard : "Please enter a valid credit card <br/>(e.g. 446-667-651)"
			},
			formProfilePassword: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long"
			},
			formProfileConfirmPassword: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long",
				equalTo: "Please enter the same password as above"
			},
			formProfileEmail: {
				required: "Please enter your email address",
				email: "Please enter a valid email address"
			},
		},
		submitHandler: function(form) {
			var profile = new Object();
			profile.nickName = $('#formProfileNickName').val();
			profile.firstName = $('#formProfileFirstName').val();
			profile.lastName = $('#formProfileLastName').val();
			profile.birthdate = $('#formProfileBirthDate').val();
			profile.address = $('#formProfileAddress').val();
			profile.creditcardnum = $('#formProfileCreditCardNo').val();
			profile.password = $('#formProfilePassword').val();
			profile.confirmPassword = $('#formProfileConfirmPassword').val();
			profile.email = $('#formProfileEmail').val();
			
			var profileDataString = "ajax=updateUser&data=" + JSON.stringify(profile);			
			$.ajax({
	     		url: "controller",
	            type: 'POST',
	            dataType: 'json',
	            data: profileDataString,
	            success: function(data) {
	            	if(data.success){
	            		$('#formProfileAlert').show().html('<div class="alert alert-success">'+data.message+'</div>');
	            		setTimeout(function() {
	            			window.location.href = data.redirect;
	            		}, 2000);
	            	}else{
	            		$('#formProfileAlert').show().html('<div class="alert alert-danger">'+data.message+'</div>');
	            	}
	            }
	     	});
		}
	});
	
});