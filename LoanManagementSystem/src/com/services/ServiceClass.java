package com.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dao.DaoImp;
import com.entities.CustomerDetails;
import com.entities.LoanApplicants;
import com.entities.LoanNominee;
import com.entities.LoginClass;
import com.entities.LoginTable;
import com.entities.SessionTable;
import com.entities.schedule;

@Service
public class ServiceClass {
	DaoImp serv;

	@Autowired
	public ServiceClass(DaoImp serv) {
		this.serv = serv;

	}

	CustomerDetails cdet;
	LoanApplicants lapp;
	LoanNominee lnom;

	public void storeObj(CustomerDetails cust, LoanApplicants lap, LoanNominee nom) {
		cdet = cust;
		lapp = lap;
		lnom = nom;
		System.out.println(cdet);
	}

	public void applyLoan() {

		serv.persist(cdet, lapp, lnom);
	}

	public List<LoanApplicants> getAllApps() {
		return serv.getAllLoanApplicants();
	}

	public LoanApplicants getLoan(int loanid) {
		return serv.getLoanApplicant(loanid);

	}

	public void update(LoanApplicants lap) {
		serv.updateApp(lap);
	}

	public List<LoanApplicants> selOption(String sel) {
		return serv.selOption(sel);

	}

	public String checkCredentials(LoginClass log, SessionTable st) {
		LoginTable ret = serv.checkCredentials(log);
		System.out.println(log.getPassWord());

		if (ret == null)
			return "false";
		String has = "" + log.getPassWord().hashCode();
		System.out.println(has);
		System.out.println(ret.getUser_pwd() + "table");
		if (ret.getUser_pwd().equals(has)) {
			String key = serv.createKey(ret, st);
			return key;
		}
		return "false";
	}

	public List<schedule> schedule(LoanApplicants lapp2) {
		// TODO Auto-generated method stub
		List<schedule> ls = new ArrayList<>();		
		double from = lapp2.getLapp_emirange_from();
		double to = lapp2.getLapp_emirange_to();
		int tenure = lapp2.getLapp_months_req();
		double principal = lapp2.getLapp_amount();
		double annualAmount = lapp2.getLapp_annual_income();
		double rateOfInterest = 0;
		double EMI = 0;
		double dispose = 0;
		double total = 0;
		
		
		if (tenure <= 60) {
			rateOfInterest = 15;
		} else if (tenure >= 61 && tenure >= 120) {
			rateOfInterest = 18;
		} else if (tenure >= 121 && tenure >= 180) {
			rateOfInterest = 18;
		} else {
			rateOfInterest = 15;
		}
		System.out.println("roi:" + rateOfInterest);
		System.out.println("principal" + principal);
		System.out.println("tenure" + tenure);

		double monthlyInterestRate = (rateOfInterest / 12) / 100;
		System.out.println("months" + monthlyInterestRate);

		EMI = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenure))
				/ (Math.pow(1 + monthlyInterestRate, tenure) - 1);
		dispose = (annualAmount * 30) / 100;
		System.out.println(dispose+"dispose");
		total=tenure*EMI;
		
		
		  DecimalFormat decimalFormat = new DecimalFormat("#.##");
	        EMI = Double.parseDouble(decimalFormat.format(EMI));
	        dispose = Double.parseDouble(decimalFormat.format(dispose));
	        total = Double.parseDouble(decimalFormat.format(total));

	        System.out.println(EMI + "emi");
	        ls.add(new schedule(tenure, EMI, rateOfInterest, total));
	        System.out.println("total" + ls);
			
	        double N = (Math.log(from) - Math.log(from - principal * monthlyInterestRate))
	                / Math.log(1 + monthlyInterestRate);
	     int numberOfMonths = (int) Math.ceil(N);
	       total=to*N;
	       total = Double.parseDouble(decimalFormat.format(total));
	        ls.add(new schedule(numberOfMonths, to, rateOfInterest, total));
		
		
		
        N = (Math.log(to) - Math.log(to - principal * monthlyInterestRate))
                / Math.log(1 + monthlyInterestRate);
       numberOfMonths = (int) Math.ceil(N);
       total=to*N;
       total = Double.parseDouble(decimalFormat.format(total));
        ls.add(new schedule(numberOfMonths, to, rateOfInterest, total));
		
		
		return ls;
	}

}
