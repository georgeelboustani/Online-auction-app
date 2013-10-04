package jdbc;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class AuctionDTO implements Serializable {
	private static final long serialVersionUID = 2L;
	
	private int aid;
	private int auctionOwnerId;
	private String auctionTitle;
	private String auctionCategory;
	private String auctionImage;
	private String auctionDescription;
	private String auctionPostageDetails;
	private double auctionReservePrice;
	private double biddingStartPrice;
	private double biddingIncrement;
	private Timestamp  auctionStartTime;
	private Timestamp auctionCloseTime;
	private boolean auctionHalt;
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getAuctionOwnerId() {
		return auctionOwnerId;
	}
	public void setAuctionOwnerId(int auctionOwnerId) {
		this.auctionOwnerId = auctionOwnerId;
	}
	
	@NotNull(message = "Title is compulsory")
	@NotBlank(message = "Title is compulsory")
	public String getAuctionTitle() {
		return auctionTitle;
	}
	public void setAuctionTitle(String auctionTitle) {
		this.auctionTitle = auctionTitle;
	}
	
	@NotNull(message = "Category is compulsory")
	@NotBlank(message = "Category is compulsory")
	public String getAuctionCategory() {
		return auctionCategory;
	}
	public void setAuctionCategory(String auctionCategory) {
		this.auctionCategory = auctionCategory;
	}
	
	public String getAuctionImage() {
		return auctionImage;
	}
	public void setAuctionImage(String auctionImage) {
		this.auctionImage = auctionImage;
	}
	
	@NotNull(message = "Auction Description is compulsory")
	@NotBlank(message = "Auction Description is compulsory")
	public String getAuctionDescription() {
		return auctionDescription;
	}
	public void setAuctionDescription(String auctionDescription) {
		this.auctionDescription = auctionDescription;
	}
	
	@NotNull(message = "Postage Details is compulsory")
	@NotBlank(message = "Postage Details is compulsory")
	public String getAuctionPostageDetails() {
		return auctionPostageDetails;
	}
	public void setAuctionPostageDetails(String auctionPostageDetails) {
		this.auctionPostageDetails = auctionPostageDetails;
	}
	
	@NotNull(message = "Reserve Price is compulsory")
	@DecimalMin("0.10")
	public double getAuctionReservePrice() {
		return auctionReservePrice;
	}
	public void setAuctionReservePrice(double auctionReservePrice) {
		this.auctionReservePrice = auctionReservePrice;
	}
	
	
	@NotNull(message = "Start Price is compulsory")
	@DecimalMin("0.10")
	public double getBiddingStartPrice() {
		return biddingStartPrice;
	}
	public void setBiddingStartPrice(double biddingStartPrice) {
		this.biddingStartPrice = biddingStartPrice;
	}
	
	
	@NotNull(message = "Bidding increment is compulsory")
	@DecimalMin("0.10")
	public double getBiddingIncrement() {
		return biddingIncrement;
	}
	public void setBiddingIncrement(double biddingIncrement) {
		this.biddingIncrement = biddingIncrement;
	}
	
	
	public Timestamp getAuctionStartTime() {
		return auctionStartTime;
	}
	public void setAuctionStartTime(Timestamp auctionStartTime) {
		this.auctionStartTime = auctionStartTime;
	}
	public Timestamp getAuctionCloseTime() {
		return auctionCloseTime;
	}
	public void setAuctionCloseTime(Timestamp auctionCloseTime) {
		this.auctionCloseTime = auctionCloseTime;
	}
	
	
	public boolean getAuctionHalt() {
		return auctionHalt;
	}
	public void setAuctionHalt(boolean auctionHalt) {
		this.auctionHalt = auctionHalt;
	}
	
	@Override
	public String toString() {
		return aid + " " + auctionCategory + " " + auctionDescription + " " + auctionImage + " " + auctionOwnerId + " " + 
			   auctionPostageDetails + " " + auctionReservePrice + " " + auctionTitle + " " + biddingIncrement + " " + 
			   biddingStartPrice + " " + auctionCloseTime + " " + auctionHalt + " " + auctionStartTime;
	}
}
