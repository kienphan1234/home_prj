package home.model;

import java.util.Date;

public class HomePrice {
	private int id;
	private String roomPrice;
	private String electricPrice;
	private String waterPrice;
	private String internetPrice;
	private String garbagePrice;
	private String livingPrice;
	private String total;
	private String note;
	private int status;
	private String createdAt;
	private String depositedAt;

	public HomePrice(int id, String roomPrice, String electricPrice, String waterPrice, String internetPrice, String garbagePrice, String livingPrice, String total, String note, int status, String createdAt, String depositedAt) {
		this.id = id;
		this.roomPrice = roomPrice;
		this.electricPrice = electricPrice;
		this.waterPrice = waterPrice;
		this.internetPrice = internetPrice;
		this.garbagePrice = garbagePrice;
		this.livingPrice = livingPrice;
		this.total = total;
		this.note = note;
		this.status = status;
		this.createdAt = createdAt;
		this.depositedAt = depositedAt;
	}

	public HomePrice() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(String roomPrice) {
		this.roomPrice = roomPrice;
	}

	public String getElectricPrice() {
		return electricPrice;
	}

	public void setElectricPrice(String electricPrice) {
		this.electricPrice = electricPrice;
	}

	public String getWaterPrice() {
		return waterPrice;
	}

	public void setWaterPrice(String waterPrice) {
		this.waterPrice = waterPrice;
	}

	public String getInternetPrice() {
		return internetPrice;
	}

	public void setInternetPrice(String internetPrice) {
		this.internetPrice = internetPrice;
	}

	public String getGarbagePrice() {
		return garbagePrice;
	}

	public void setGarbagePrice(String garbagePrice) {
		this.garbagePrice = garbagePrice;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDepositedAt() {
		return depositedAt;
	}

	public void setDepositedAt(String depositedAt) {
		this.depositedAt = depositedAt;
	}

	public String getLivingPrice() {
		return livingPrice;
	}

	public void setLivingPrice(String livingPrice) {
		this.livingPrice = livingPrice;
	}
}
