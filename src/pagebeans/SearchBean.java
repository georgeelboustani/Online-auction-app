package pagebeans;

import java.io.Serializable;
import java.util.List;

import jdbc.AuctionDTO;

public class SearchBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<AuctionDTO> auctions;
	private List<Long> times;
	private List<Double> bids;
	private boolean displayError;
	private String errorMessage;

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
