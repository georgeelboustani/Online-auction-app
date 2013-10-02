package pagebeans;

import java.io.Serializable;

import jdbc.AuctionDTO;

public class AuctionBean implements Serializable{
	private static final long serialVersionUID = 3L;
	
	private AuctionDTO auction;
	private long timeLeft;
	private boolean displayError;
	private String errorMessage;
	
	public long getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(long timeLeft) {
		this.timeLeft = timeLeft;
	}
	public AuctionDTO getAuction() {
		return auction;
	}
	public void setAuction(AuctionDTO auction) {
		this.auction = auction;
	}
	public boolean getDisplayError() {
		return displayError;
	}
	public void setDisplayError(boolean displayError) {
		this.displayError = displayError;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
