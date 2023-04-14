package lib;

public class TaxFunction {
	private static final double taxRate = 0.05;
	private static final int maxChildren = 3;
	private static final int taxSingle = 54000000;
	private static final int taxMarried = 4500000;
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		if (numberOfMonthWorking > 12) {
			throw new IllegalArgumentException("More than 12 month working per year");
		}
		numberOfChildren = Math.min(numberOfChildren, maxChildren);
		int status  = isMarried ? taxMarried : taxSingle; //Menentukan apakah status dari employee tersebut sudah menikah atau belum
        status += numberOfChildren * 1500000;
		int incomeTax = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking - deductible - status;
        int tax = (int) Math.round(taxRate * incomeTax);
        return Math.max(tax, 0); 
	}
}
