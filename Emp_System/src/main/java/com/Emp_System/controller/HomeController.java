package com.Emp_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Emp_System.entity.Employee;
import com.Emp_System.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller

public class HomeController {

	@Autowired
	private EmpService empService;

	@GetMapping("/")
	public String index(Model m) {
		List<Employee> list = empService.getAllEmp();
		m.addAttribute("employee", list);
		return "index";
	}

	@GetMapping("/loadEmpsave")
	public String loadEmpsave() {
		return "emp_save";
	}

	@GetMapping("/Editemp/{id}")
	public String Editemp(@PathVariable int id, Model m) {
		System.out.println(id);
		Employee emp = empService.getEmpById(id);
		m.addAttribute("emp", emp);
		return "edit_emp";
	}

	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp, HttpSession session) {
		System.out.println(emp);
		Employee newEmp = empService.saveEmployee(emp);
		if (newEmp != null) {

			session.setAttribute("msg", "Register Successfully..");
		} else {

			session.setAttribute("msg", "something wrong server");
		}
		return "redirect:/loadEmpsave";
	}

	@PostMapping("/updateEmpDtls")
	public String updateEmpDtls(@ModelAttribute Employee emp, HttpSession session) {
		System.out.println(emp);
		Employee updateEmp = empService.saveEmployee(emp);
		if (updateEmp != null) {

			session.setAttribute("msg", "Update Successfully..");
		} else {

			session.setAttribute("msg", "something wrong server");
		}
		return "redirect:/";
	}

	@GetMapping("/deleteEmp/{id}")
	public String loadEmpsave(@PathVariable int id, HttpSession session) {
		boolean f = empService.deleteEmp(id);
		if (f) {
			session.setAttribute("msg", "Delete Successfully..");
		} else {
			session.setAttribute("msg", "something wrong server");
		}
		return "redirect:/";
	}
}
