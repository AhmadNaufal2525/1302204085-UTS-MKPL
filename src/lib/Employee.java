package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {
	public enum Grade {
		GRADE1,
		GRADE2,
		GRADE3
	}

	private int yearJoined;
	private int monthJoined;
	private int monthWorkingInYear;
	private Grade grade;
	private boolean isForeigner;

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;

	public Employee(int yearJoined, Grade grade, int monthJoined, boolean isForeigner) {
		this.yearJoined = yearJoined;
		this.grade = grade;
		this.monthJoined = monthJoined;
		this.isForeigner = isForeigner;
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}

	public void setMonthlySalary() {
		monthlySalary = calculateMonthlySalary();
	}

	private int calculateMonthlySalary() {
		int salary;
		switch (grade) {
			case GRADE1:
				salary = 3000000;
				break;
			case GRADE2:
				salary = 5000000;
				break;
			case GRADE3:
				salary = 7000000;
				break;
			default:
				salary = 0;
				break;
		}
		if (isForeigner) {
			salary = (int) (salary * 1.5);
		}
		return salary;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseIdNumber) {
		this.spouseIdNumber = spouseIdNumber;
	}

	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {

		// Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah
		// bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();

		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		} else {
			monthWorkingInYear = 12;
		}

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible,
				spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
