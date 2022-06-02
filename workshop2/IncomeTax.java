package workshop2;

public class IncomeTax {
	
	private int filingStatus;
	
	public static final int SINGLE_FILER = 0;
	public static final int MARRIED_JOINTLY_OR_QUALIFYING_WIDOW = 1;
	public static final int MARRIED_SEPARATELY = 2;
	public static final int HEAD_OF_HOUSEHOLD = 3;
	
	private int[][] intervals;
	private double[] rates;
	private double taxableIncome;
	
	public IncomeTax() {};
	
	public IncomeTax(int fillStat, int[][] intervals, double[] rates, double taxableInc) {
		setFilingStatus(fillStat);
		setIntervals(intervals);
		setRates(rates);
		setTaxableIncome(taxableInc);
	};
	
	public int getFilingStatus() {
		return filingStatus;
	}
	
	public void setFilingStatus(int status) {
		this.filingStatus = status;
	}
	
	public int[][] getIntervals() {
		return intervals;
	}
	
	public void setIntervals(int[][] interval) {
		this.intervals = interval;
	}
	
	public double[] getRatees() {
		return rates;
	};
	
	public void setRates(double[] rat) {
		this.rates = rat;
	};
	
	public double getTaxableIncome() {
		return taxableIncome;
	};
	
	public void setTaxableIncome(double taxableInc) {
		this.taxableIncome = taxableInc;
	};
	
	public double getIncomeTax() {
		double calculatedTax = 0;
		double taxed = 0;
		
		if (taxableIncome <= intervals[filingStatus][0]) {
			calculatedTax = taxableIncome * rates[0];
		}else {
			calculatedTax = intervals[filingStatus][0] * rates[0];
			
			double inc = taxableIncome;
			//for(int i = 0; i < rates.length-2; i++) { //2011 right 2021 wrong
			for(int i = rates.length - 2; i >= 0; i--) {
				
				if(inc > intervals[filingStatus][i]) {
					taxed = inc - intervals[filingStatus][i];
					calculatedTax += taxed * rates[i+1];
					inc -= taxed;
				}
			}
		}
		
		return calculatedTax;
	}; 
	

}
