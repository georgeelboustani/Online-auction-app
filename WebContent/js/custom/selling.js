$().ready( function(){
	
	$.validator.addMethod("money", function (value, element) {
		return this.optional(element) || /^(\d+)(\.\d{1,2})?$/.test(value);
		}, "currency format (e.g. 0.99)");
	
	$("#sellingForm").validate({
		rules: {
			formTitle: "required",
			formCategory: "required",
			formItemDescription: "required",
			formPostageDescription: "required",		
			formStartPrice: {
				required: true,
				number: true,
				min: 0.10,
				money: true
			},
			formReservePrice: {
				required: true,
				number: true,
				min: 0.10,
				money: true
			},
			formMinBid: {
				required: true,
				number: true,
				min: 0.10,
				money: true
			},
			formDuration: {
				required: true,
				digits: true,
				range: [3, 60]
			},
			formImage: {
				required: true,
				url: true,
			}
		},
		messages: {
			formTitle: "title required",
			formCategory: "category required",
			formItemDescription: "item desc required",
			formPostageDescription: "postage desc required",			
			formStartPrice: {
				required: "start pricerequired",
				number: "only in decimal format",
				min: "min of 0.10"
			},
			formReservePrice: {
				required: "reserve price required",
				number: "numero decimal only",
				min: "min of 0.10"
			},
			formMinBid: {
				required: "minimum bid required",
				number: "numero decimal only"
			},
			formDuration: {
				required: "duration required",
				digits: "numero decimal only",
				range: "range from 3min to 60mins"
			},
			formImage: {
				required: "image url required",
				url: "malform url detected",
			}
		},
		submitHandler: function(form) {
			var auction = new Object();
			auction.title = $('#formTitle').val();
			auction.category = $('#formCategory').val();
			auction.image = $('#formImage').val();
			auction.itemDesc = $('#formItemDescription').val();
			auction.postageDesc = $('#formPostageDescription').val();
			auction.startPrice = $('#formStartPrice').val();
			auction.reservePrice = $('#formReservePrice').val();
			auction.duration = $('#formDuration').val();
			auction.minbid = $('#formMinBid').val();
			
			var auctionDataString = "ajax=addAuction&data=" + JSON.stringify(auction);			
			$.ajax({
	     		url: "controller",
	            type: 'POST',
	            dataType: 'json',
	            data: auctionDataString,
	            success: function(data) {
	            	if(data.success){
	            		$('#formSellingAlert').show().html('<div class="alert alert-success">'+data.message+'</div>');
	            		setTimeout(function() {
	            			window.location.href = data.redirect;
	            		}, 2000);
	            	}else{
	            		$('#formSellingAlert').show().html('<div class="alert alert-danger">'+data.message+'</div>');
	            	}
	            }
	     	});
		}
	});
});
function getDetails(aid){
	event.preventDefault();
	window.location.href = "controller?action=viewAuction&auctionId="+aid;
}