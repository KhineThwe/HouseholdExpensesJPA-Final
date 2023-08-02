package com.jp.expenses.entity;

public class MonthlyExpense {
	private int year;
	private int month;
	private double cost;

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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public MonthlyExpense(int year, int month, double cost) {
		super();
		this.year = year;
		this.month = month;
		this.cost = cost;
	}

}
