package pagebeans;

import java.util.ArrayList;
import java.util.List;

import jdbc.AuctionDTO;

public class AuctionBean {
	
	private List<AuctionDTO> auctions;

	public List<AuctionDTO> getAuctions() {
		return auctions;
	}

	public void setAuctions(List<AuctionDTO> auctions) {
		this.auctions = auctions;
	}
	
}
