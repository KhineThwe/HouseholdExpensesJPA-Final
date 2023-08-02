package com.jp.expenses.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.jp.expenses.dto.HouseholdExpensesDto;
import com.jp.expenses.entity.HouseholdExpeneseEntity;
import com.jp.expenses.entity.MonthlyExpense;
import com.jp.expenses.entity.MonthlyExpensesList;
import com.jp.expenses.repository.HouseholdExpensesRepository;

@Service
public class HouseholdExpensesService {

	@Autowired
	HouseholdExpensesRepository expensesRepository;

	public void save(HouseholdExpensesDto householdExpensesDto) {
		HouseholdExpeneseEntity entity = new HouseholdExpeneseEntity();
		String[] dateParts = householdExpensesDto.getDate().split("-");
		int year = Integer.parseInt(dateParts[0]);
		int month = Integer.parseInt(dateParts[1]);
		int day = Integer.parseInt(dateParts[2]);
		entity.setYear(year);
		entity.setMonth(month);
		entity.setDay(day);
		entity.setCost(householdExpensesDto.getCost());
		entity.setItem(householdExpensesDto.getItem());
		expensesRepository.save(entity);
	}

	public List<HouseholdExpeneseEntity> findAll() {
		return expensesRepository.findAll();
	}

	public HouseholdExpensesDto getHouseholdExpenses(int id) {
		Optional<HouseholdExpeneseEntity> p = this.expensesRepository.findById(id);
		HouseholdExpeneseEntity e = p.get();
		HouseholdExpensesDto dto = new HouseholdExpensesDto();
		int year = e.getYear();
		int month = e.getMonth();
		int day = e.getDay();
		dto.setId(e.getId());
		String date = String.valueOf(day + '-' + month + '-' + year);
		dto.setDate(date);
		dto.setCost(e.getCost());
		dto.setItem(e.getItem());
		return dto;
	}

	public void updateExpenses(HouseholdExpensesDto householdExpensesDto) {
		HouseholdExpeneseEntity entity = new HouseholdExpeneseEntity();
		String[] dateParts = householdExpensesDto.getDate().split("-");
		int year = Integer.parseInt(dateParts[0]);
		int month = Integer.parseInt(dateParts[1]);
		int day = Integer.parseInt(dateParts[2]);
		entity.setId(householdExpensesDto.getId());
		entity.setYear(year);
		entity.setMonth(month);
		entity.setDay(day);
		entity.setCost(householdExpensesDto.getCost());
		entity.setItem(householdExpensesDto.getItem());
		expensesRepository.save(entity);
	}

	public Long getTotal(int month, int year) {
		return expensesRepository.total(month, year);
	}

	public List<MonthlyExpense> getMonthlyExpenses() {
		return expensesRepository.findMonthlyExpenses();
	}

	public List<MonthlyExpensesList> getMonthlyExpensesDetails(int month, int year) {
		return expensesRepository.findTotalMonthlyCost(month, year);
	}

}
