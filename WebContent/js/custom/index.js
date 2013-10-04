$().ready( function(){
	
});


function doBid(aid, current_bid, min_bid_amt){
	var value = $("#bid_input_"+aid).val();
	var isValid = value.search(/^\$?\d+(,\d{3})*(\.\d*)?$/) >= 0;
	var bidDifference = current_bid - value;
	
	if(isValid == false){
		$("<div>Error: Invalid format of money <br/>or empty</div>").dialog({
			title: "Bid Warning",	
			modal: true
		});
	}else if(value <= current_bid ){
		$("<div>Error: Bid a higher value!</div>").dialog({
			title: "Bid Warning",
			modal: true
		});
	}else if(current_bid > 0 && bidDifference > 0){ 
		alert("hello");
		if(bidDifference < min_bid_amt){
			alert("diff: " + bidDifference);
			$("<div>Error: Bid does not meet the minimum increase of $"+min_bid_amt+"</div>").dialog({
				title: "Bid Warning",
				modal: true
			});
		}
	}else{
		var bid = new Object();
		bid.aid = aid;
		bid.value = value;
		var bidDataString = "ajax=placeBid&data=" + JSON.stringify(bid);
		
		$.ajax({
     		url: "controller",
            type: 'POST',
            dataType: 'json',
            data: bidDataString,
            success: function(data) {
            	if(data.success){
            		$('#bidAlert_'+aid).show().html('<div class="alert alert-success">'+data.message+'</div>');
            		setTimeout(function() {
            			window.location.href = data.redirect;
            		}, 2000);
            	}else{
            		$('#bidAlert_'+aid).show().html('<div class="alert alert-danger">'+data.message+'</div>');
            		setTimeout(function() {
            			window.location.href = data.redirect;
            		}, 2000);
            	}
            }
     	});
	}
	return false;
}

function getDetails(aid){
	event.preventDefault();
	window.location.href = "controller?action=viewAuction&auctionId="+aid;
}

