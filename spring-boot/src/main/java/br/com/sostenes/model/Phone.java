package br.com.sostenes.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@SequenceGenerator(name = "exemplo", sequenceName = "exemplo")
public class Phone {
	@Id
	@GeneratedValue
	@Column(name = "idphone")
	private long idPhone;

	public long getIdPhone() {
		return idPhone;
	}

	public void setIdPhone(long idPhone) {
		this.idPhone = idPhone;
	}

	String model;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	BigDecimal price;
	String brand;
	String photo;
	@Column(name = "startdate")
	Date startDate;
	@Column(name = "enddate")
	Date endDate;
	String code;
	String color;
	public Phone() {
		
	}
	public Phone(BigDecimal price, String brand, String photo, Date startDate, Date endDate, String code, String color, String model) {
		this.price = price;
		this.brand = brand;
		this.photo = photo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.code = code;
		this.color = color;
		this.model = model;
	}
}
