package jdbc;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class AuctionBidDTO {
	private int auctionId;
	private int userId;
	private double bidAmount;
	
	@NotNull(message = "Auction ID is compulsory")
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	
	@NotNull(message = "User ID is compulsory")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@NotNull(message = "Bid Amount is compulsory")
	@Digits(integer=6, fraction=2)
	public double getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}
}
