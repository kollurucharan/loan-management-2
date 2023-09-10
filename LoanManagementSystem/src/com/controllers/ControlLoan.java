package com.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.CustomerDetails;
import com.entities.LoanApplicants;
import com.entities.LoanNominee;
import com.entities.LoginClass;
import com.entities.SessionTable;
import com.execl.ExcelGenerator;
import com.services.ServiceClass;
import com.entities.schedule;

@Controller
public class ControlLoan {
	ServiceClass ctrl;

	@Autowired
	public ControlLoan(ServiceClass ctrl) {
		this.ctrl = ctrl;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	public String adminLogin(Model model) {
		return "adminLogin";
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public String userLogin(Model model) {

		return "userLogin";
	}

	@RequestMapping(value = "/startpage", method = RequestMethod.GET)
	public String userStartPage(LoginClass log, SessionTable st, HttpServletRequest hsq, Model model) {
		String b = ctrl.checkCredentials(log, st);
		HttpSession session = hsq.getSession();
		session.setAttribute("key", b);
		if (b.equals("false")) {
			model.addAttribute("false", "incorrect crediatials");
			return "userLogin";
		} else {
			return "userstartpage";
		}
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String customerApplication(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		String x = (String) session.getAttribute("key");
		System.out.println(x);
		return "customerApplication";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String applicationPreview(CustomerDetails customerdetails, LoanNominee nominee,
			LoanApplicants loanApplicants, Model model) {
		ctrl.storeObj(customerdetails, loanApplicants, nominee);
		model.addAttribute("nom", nominee);
		model.addAttribute("cust", customerdetails);
		model.addAttribute("app", loanApplicants);

		return "applicationPreview";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.GET)
	public String ttt(Model model) {
		System.out.println("submit");
		ctrl.applyLoan();
		return "userstartpage";
	}

	@RequestMapping(value = "/backtouserpage", method = RequestMethod.GET)
	public String backToUserStartPage(Model model) {
		return "userstartpage";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminApplicationPage(Model model) {
		List<LoanApplicants> lapp = ctrl.getAllApps();
		model.addAttribute("lapp", lapp);
		return "adminpage";
	}

	@RequestMapping(value = "/editdetails", method = RequestMethod.GET)
	public String attttt(LoanApplicants lap, Model model) {
		LoanApplicants lapp = ctrl.getLoan(lap.getLapp_id());
		model.addAttribute("editdetails", lapp);
		return "editdetails";
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String yttt(LoanApplicants lap, Model model) {
		System.out.println(lap.getLapp_conclusion_remarks());
		ctrl.update(lap);
		List<LoanApplicants> lapp = ctrl.getAllApps();
		model.addAttribute("lapp", lapp);
		return "adminpage";
	}

	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public void downloadExcel(@RequestParam String sel, HttpServletResponse response) throws IOException {
		List<LoanApplicants> lapp = ctrl.selOption(sel);
		Workbook workbook = ExcelGenerator.generateExcel(lapp);

		// Set the response headers for Excel download
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=loan_applicants.xlsx");

		// Write the workbook to the response output stream
		workbook.write(response.getOutputStream());
		workbook.close();
	}

	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public String filter(@RequestParam String sel, Model model) {
		System.out.println("input" + sel);
		model.addAttribute("sel", sel);
		List<LoanApplicants> al = ctrl.selOption(sel);
		System.out.println("table" + al.get(0).getLapp_amount());
		model.addAttribute("lapp", al);
		return "adminpage";
	}

	@RequestMapping(value = "/checkloan", method = RequestMethod.GET)
	public String check(Model model) {
		return "checkloan";
	}
	//-------------------------------------------------------------------------------------
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public String status(Model model) {
		List<LoanApplicants> lapp = ctrl.getAllApps();
		model.addAttribute("lapp", lapp);
		return "status";
	}
	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public String schedule(LoanApplicants lap, Model model) {
		LoanApplicants lapp = ctrl.getLoan(lap.getLapp_id());
		List <schedule> ls =ctrl.schedule(lapp);
		System.out.println("controller"+ls);
		model.addAttribute("schedule", ls);
		return "schedule";
	}
}
