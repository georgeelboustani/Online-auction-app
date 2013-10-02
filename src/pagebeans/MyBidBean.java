package pagebeans;

import java.io.Serializable;
import java.util.List;

import jdbc.AuctionDTO;

public class MyBidBean implements Serializable {
	private static final long serialVersionUID = 4L;
	
	private List<AuctionDTO> auctions;
	private List<Long> times;
	private List<Double> bids;
	private List<Boolean> highestBidder;
	private boolean displayError;
	private String errorMessage;

	public List<Boolean> getHighestBidder() {
		return highestBidder;
	}

	public void setHighestBidder(List<Boolean> highestBidder) {
		this.highestBidder = highestBidder;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean getDisplayError() {
		return displayError;
	}

	public void setDisplayError(boolean displayError) {
		this.displayError = displayError;
	}

	public List<AuctionDTO> getAuctions() {
		return auctions;
	}

	public void setAuctions(List<AuctionDTO> auctions) {
		this.auctions = auctions;
	}

	public List<Long> getTimes() {
		return times;
	}

	public void setTimes(List<Long> auctionsTimeLeft) {
		this.times = auctionsTimeLeft;
	}

	public List<Double> getBids() {
		return bids;
	}

	public void setBids(List<Double> auctionCurrentBids) {
		this.bids = auctionCurrentBids;
	}
	
}
