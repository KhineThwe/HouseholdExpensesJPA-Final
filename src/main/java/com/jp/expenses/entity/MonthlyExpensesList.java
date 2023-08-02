package com.jp.expenses.entity;

public class MonthlyExpensesList {
	private int id;
	private int year;
	private int month;
	private int day;
	private String item;
	private double cost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public MonthlyExpensesList(int id, int year, int month, int day, String item, double cost) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.day = day;
		this.item = item;
		this.cost = cost;
	}

	public MonthlyExpensesList(int year, int month, int day, String item, double cost) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.item = item;
		this.cost = cost;
	}

	public MonthlyExpensesList(int day, String item, double cost) {
		super();
		this.day = day;
		this.item = item;
		this.cost = cost;
	}

}
