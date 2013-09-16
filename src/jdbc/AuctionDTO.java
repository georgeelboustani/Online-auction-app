package model;

import java.util.Date;

public class AuctionDTO {
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
	private Date  auctionStartTime;
	private Date auctionCloseTime;
	private boolean auctionHalt;
	
	// private List<BidDTO> bids; // we can add this if useful. otherwise better off using DAO to get them
	
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
	public String getAuctionTitle() {
		return auctionTitle;
	}
	public void setAuctionTitle(String auctionTitle) {
		this.auctionTitle = auctionTitle;
	}
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
	public String getAuctionDescription() {
		return auctionDescription;
	}
	public void setAuctionDescription(String auctionDescription) {
		this.auctionDescription = auctionDescription;
	}
	public String getAuctionPostageDetails() {
		return auctionPostageDetails;
	}
	public void setAuctionPostageDetails(String auctionPostageDetails) {
		this.auctionPostageDetails = auctionPostageDetails;
	}
	public double getAuctionReservePrice() {
		return auctionReservePrice;
	}
	public void setAuctionReservePrice(double auctionReservePrice) {
		this.auctionReservePrice = auctionReservePrice;
	}
	public double getBiddingStartPrice() {
		return biddingStartPrice;
	}
	public void setBiddingStartPrice(double biddingStartPrice) {
		this.biddingStartPrice = biddingStartPrice;
	}
	public double getBiddingIncrement() {
		return biddingIncrement;
	}
	public void setBiddingIncrement(double biddingIncrement) {
		this.biddingIncrement = biddingIncrement;
	}
	public Date getAuctionStartTime() {
		return auctionStartTime;
	}
	public void setAuctionStartTime(Date auctionStartTime) {
		this.auctionStartTime = auctionStartTime;
	}
	public Date getAuctionCloseTime() {
		return auctionCloseTime;
	}
	public void setAuctionCloseTime(Date auctionCloseTime) {
		this.auctionCloseTime = auctionCloseTime;
	}
	public boolean getAuctionHalt() {
		return auctionHalt;
	}
	public void setAuctionHalt(boolean auctionHalt) {
		this.auctionHalt = auctionHalt;
	}
}
