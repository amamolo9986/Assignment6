package com.coderscampus.main;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class SalesReport {

	private YearMonth date; 
	private Integer sales;
	
	public SalesReport(String date, String sales) {
		super();
		this.date = YearMonth.parse(date, DateTimeFormatter.ofPattern("MMM-yy"));
		this.sales = Integer.parseInt(sales);
	}
	
	public YearMonth getDate() {
		return date;
	}
	public void setDate(YearMonth date) {
		this.date = date;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "SalesReport [date=" + date + ", sales=" + sales + "]";
	}

}