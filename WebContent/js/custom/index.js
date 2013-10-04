$().ready( function(){
	
	$('#open-dialog-button').click(function(){
		
	});
	
});

function getDetails(aid){
	event.preventDefault();
	window.location.href = "controller?action=viewAuction&auctionId="+aid;
}

