package com.jp.expenses.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HouseholdExpensesDto {
	private int id;
	@NotBlank(message = "日付を空白にすることはできません!")
	private String date;
	@NotBlank(message = "項目を空白にすることはできません")
	private String item;
	@NotNull(message = "コストは null であってはなりません")
	@Min(value = 1, message = "コストは 0 未満であってはなりません")
	private int cost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
