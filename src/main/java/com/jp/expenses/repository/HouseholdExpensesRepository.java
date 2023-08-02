package com.jp.expenses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jp.expenses.entity.HouseholdExpeneseEntity;
import com.jp.expenses.entity.MonthlyExpense;
import com.jp.expenses.entity.MonthlyExpensesList;

@Repository
public interface HouseholdExpensesRepository extends JpaRepository<HouseholdExpeneseEntity, Integer> {
	@Query("SELECT SUM(h.cost) FROM HouseholdExpeneseEntity h WHERE h.year=?1 AND h.month=?2")
	Long total(int year, int month);

	@Query("SELECT new com.jp.expenses.entity.MonthlyExpense(h.year, h.month, SUM(h.cost) AS total) FROM HouseholdExpeneseEntity h GROUP BY h.year, h.month ORDER BY h.year DESC, h.month DESC")
	List<MonthlyExpense> findMonthlyExpenses();

	@Query("SELECT new com.jp.expenses.entity.MonthlyExpensesList(h.id,h.year, h.month, h.day, h.item, SUM(h.cost) AS total) "
			+ "FROM HouseholdExpeneseEntity h " + "WHERE  h.month = ?1 AND h.year = ?2 "
			+ "GROUP BY h.id, h.year, h.month, h.day, h.item " + "ORDER BY h.day DESC")
	List<MonthlyExpensesList> findTotalMonthlyCost(int month, int year);
	
	@Query("SELECT h FROM HouseholdExpeneseEntity h WHERE h.day=?1 AND h.month=?2 AND h.year=?3 AND h.item=?4")
	HouseholdExpeneseEntity findForValidate(int day,int month,int year,String item);

}
