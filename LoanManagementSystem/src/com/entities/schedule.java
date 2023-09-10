package com.entities;

public class schedule {
	double months;
	double emiPerMonth;
	double rateOfInterest;
	double total;
	public schedule ()
	{
	}public schedule(double months, double emiPerMonth, double rateOfInterest, double total) {
		
	
		super();
		this.months = months;
		this.emiPerMonth = emiPerMonth;
		this.rateOfInterest = rateOfInterest;
		this.total = total;
	}
	@Override
	public String toString() {
		return "schedule [months=" + months + ", emiPerMonth=" + emiPerMonth + ", rateOfInterest=" + rateOfInterest
				+ ", total=" + total + "]";
	}


	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getMonths() {
		return months;
	}
	public void setMonths(double months) {
		this.months = months;
	}
	public double getEmiPerMonth() {
		return emiPerMonth;
	}
	public void setEmiPerMonth(double emiPerMonth) {
		this.emiPerMonth = emiPerMonth;
	}
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
}
