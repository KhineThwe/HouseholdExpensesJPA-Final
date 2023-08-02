package com.jp.expenses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jp.expenses.dto.HouseholdExpensesDto;
import com.jp.expenses.entity.HouseholdExpeneseEntity;
import com.jp.expenses.entity.MonthlyExpensesList;
import com.jp.expenses.service.HouseholdExpensesService;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import jakarta.validation.Valid;

@Controller
public class HouseholdController {
	@Autowired
	HouseholdExpensesService householdExpensesService;

	@GetMapping("/")
	public String index(Model model) {
		List<HouseholdExpeneseEntity> household = householdExpensesService.findAll();
		if(household.isEmpty()) {
			model.addAttribute("empty","No Expenses Data");
		}
		model.addAttribute("householdExpensesList", householdExpensesService.getMonthlyExpenses());
		return "index";
	}

	@GetMapping("/addExpenses")
	public String addExpenses(Model model) {
		model.addAttribute("expenses", new HouseholdExpensesDto());
		return "addExpenses";
	}

	@PostMapping("/addExpenses")
	public String addExpensesPost(@ModelAttribute("expenses") @Valid HouseholdExpensesDto household,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("expenses", household);
			return "addExpenses";
		}
		String[] dateParts = household.getDate().split("-");
		HouseholdExpeneseEntity h = householdExpensesService.findForValidate(dateParts[0], dateParts[1], dateParts[2], household.getItem());
		if(h!=null) {
			model.addAttribute("same","Same Record Again");
			model.addAttribute("expenses", household);
			return "addExpenses";
		}else {
			householdExpensesService.save(household);
		}
		
		String route = "monthlyDetails?month=" + dateParts[1] + "&year=" + dateParts[2];
		return "redirect:/" + route;
	}

	@GetMapping("/monthlyDetails")
	public String showMonthlyExpenses(Model model, @RequestParam("month") int month, @RequestParam("year") int year) {
		model.addAttribute("total", householdExpensesService.getTotal(year, month));
		model.addAttribute("monthlyExpenses", householdExpensesService.getMonthlyExpensesDetails(month, year));
		return "monthlyDetails";
	}

	@GetMapping("/update/{id}")
	public String update(Model model, @PathVariable("id") int id) {
		model.addAttribute("monthlyExp", householdExpensesService.getHouseholdExpenses(id));
		return "updateExpenses";
	}

	@PostMapping("/update")
	public String updateConfirm(Model model, @ModelAttribute("monthlyExp") HouseholdExpensesDto h) {
		householdExpensesService.updateExpenses(h);
		String[] dateParts = h.getDate().split("-");
		String route = "monthlyDetails?month=" + dateParts[1] + "&year=" + dateParts[2];
		return "redirect:/" + route;
	}
}
