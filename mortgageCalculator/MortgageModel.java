package lab4;

public class MortgageModel {
    /**
     * instance variables
     */
    private double principal;
    private double annualInterestRate;
    private int numPayments;
    private int paymentFrequency;
    private int compoundingFrequency;
    /**
     *
     * @param principal the total amount loaned
     * @param annualInterestRate the interest rate
     * @param numPayments number of monthly payments (amortization)
     * @param paymentFrequency frequency of payments
     * @param compoundingFrequency compounding frequency per year
     */
    public MortgageModel(double principal, double annualInterestRate, int numPayments, int paymentFrequency, int compoundingFrequency) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.numPayments = numPayments;
        this.paymentFrequency = paymentFrequency;
        this.compoundingFrequency = compoundingFrequency;
    }
    /**
     *
     * @param paymentFrequency set the payment frequency
     */
    public void setPaymentFrequency(int paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }
    /**
     *
     * @param compoundingFrequency set the compounding frequnecy
     */
    public void setCompoundingFrequency(int compoundingFrequency) {
        this.compoundingFrequency = compoundingFrequency;
    }
    /**
     *
     * @return the equation to get the blended payment
     */
    public double getBlendedMonthlyPayment() {
        double interestFactor = calculateInterestFactor();
        return principal * interestFactor / (1 - Math.pow(1 + interestFactor, -numPayments));
    }
    /**
     *
     * @return calculate the total interest paid
     */
    public double getTotalInterestPaid() {
        return getBlendedMonthlyPayment() * numPayments - principal;
    }
    /**
     *
     * @return calculate the total interest and principal
     */
    public double getTotalInterestAndPrincipal() {
        return getBlendedMonthlyPayment() * numPayments;
    }
    /**
     *
     * @return calculate the interest
     */
    public double getInterestPrincipalRatio() {
        return getTotalInterestPaid() / principal;
    }
    /**
     *
     * @return calculate the average interest paid per year
     */
    public double getAverageInterestPerYear() {
        return getTotalInterestPaid() / (numPayments / 12.0);
    }
    /**
     *
     * @return calculate the average interest paid per month
     */
    public double getAverageInterestPerMonth() {
        return getTotalInterestPaid() / numPayments;
    }
    /**
     *
     * @return the amortization expressed in years
     */
    public double getAmortizationInYears() {
        return ((double)numPayments) / 12.0;
    }
    /**
     *
     * @return calculate the interest factor
     */
    private double calculateInterestFactor() {
        double r = annualInterestRate;
        double c = compoundingFrequency;
        double f = paymentFrequency;
        double cf = c / f;
        double rc = r/c;
        double i = Math.pow(rc + 1, cf);
        return i - 1;
    }
}