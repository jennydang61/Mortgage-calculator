package lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MortgageView extends JFrame {
    // instance variables
    private JTextField numPaymentsTextField;
    private JTextField principalTextField;
    private JTextField annualInterestRateTextField;
    private JComboBox<String> paymentFrequencyComboBox;
    private JComboBox<String> compoundingFrequencyComboBox;
    private JLabel blendedPaymentLabel;
    private JLabel totalInterestPaidLabel;
    private JLabel totalPaidLabel;
    private JLabel interestPrincipalRatioLabel;
    private JLabel averageInterestPerYearLabel;
    private JLabel averageInterestPerMonthLabel;
    private JLabel amortizationYearsLabel;
    private JButton calculateButton;

    public MortgageView() {
        setTitle("Mortgage Calculator");
        // creates the layout of the GUI
        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input"));
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Number of Payments (months):"));
        numPaymentsTextField = new JTextField();
        inputPanel.add(numPaymentsTextField);
        inputPanel.add(new JLabel("Principal:"));
        principalTextField = new JTextField();
        inputPanel.add(principalTextField);
        inputPanel.add(new JLabel("Annual Interest Rate (%):"));
        annualInterestRateTextField = new JTextField();
        inputPanel.add(annualInterestRateTextField);
        inputPanel.add(new JLabel("Payment Frequency:"));
        paymentFrequencyComboBox = new JComboBox<>(new String[]{"Monthly", "Bi-Weekly", "Weekly"});
        inputPanel.add(paymentFrequencyComboBox);
        inputPanel.add(new JLabel("Compounding Frequency:"));
        compoundingFrequencyComboBox = new JComboBox<>(new String[]{"Annually", "Semi-Annually", "Quarterly", "Monthly"});
        inputPanel.add(compoundingFrequencyComboBox);

        JPanel outputPanel = new JPanel();
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        outputPanel.setLayout(new GridLayout(7, 2));
        outputPanel.add(new JLabel("Blended Payment:"));
        blendedPaymentLabel = new JLabel();
        outputPanel.add(blendedPaymentLabel);
        outputPanel.add(new JLabel("Total Interest Paid:"));
        totalInterestPaidLabel = new JLabel();
        outputPanel.add(totalInterestPaidLabel);
        outputPanel.add(new JLabel("Total Paid:"));
        totalPaidLabel = new JLabel();
        outputPanel.add(totalPaidLabel);
        outputPanel.add(new JLabel("Interest/Principal Ratio:"));
        interestPrincipalRatioLabel = new JLabel();
        outputPanel.add(interestPrincipalRatioLabel);
        outputPanel.add(new JLabel("Average Interest Paid per Year:"));
        averageInterestPerYearLabel = new JLabel();
        outputPanel.add(averageInterestPerYearLabel);
        outputPanel.add(new JLabel("Average Interest Paid per Month:"));
        averageInterestPerMonthLabel = new JLabel();
        outputPanel.add(averageInterestPerMonthLabel);
        outputPanel.add(new JLabel("Amortization (years):"));
        amortizationYearsLabel = new JLabel();
        outputPanel.add(amortizationYearsLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        buttonPanel.add(calculateButton);
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(outputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    // formats each output and rounds them
    public void setBlendedPayment(double blendedPayment) {
        blendedPaymentLabel.setText(String.format("$%.2f", blendedPayment));
    }

    public void setTotalInterestPaid(double totalInterestPaid) {
        totalInterestPaidLabel.setText(String.format("$%.2f", totalInterestPaid));
    }

    public void setTotalPaid(double totalPaid) {
        totalPaidLabel.setText(String.format("$%.2f", totalPaid));
    }

    public void setInterestPrincipalRatio(double interestPrincipalRatio) {
        interestPrincipalRatioLabel.setText(String.format("%.2f%%", interestPrincipalRatio * 100));
    }

    public void setAverageInterestPerYear(double averageInterestPerYear) {
        averageInterestPerYearLabel.setText(String.format("$%.2f", averageInterestPerYear));
    }

    public void setAverageInterestPerMonth(double averageInterestPerMonth) {
        averageInterestPerMonthLabel.setText(String.format("$%.2f", averageInterestPerMonth));
    }

    public void setAmortizationYears(double amortizationYears) {
        amortizationYearsLabel.setText(String.format("%.2f", amortizationYears));
    }

    private void calculate() {
        // for each payment and compound frequency it assigns the correct value
        int numPayments = Integer.parseInt(numPaymentsTextField.getText());
        double principal = Double.parseDouble(principalTextField.getText());
        double annualInterestRate = Double.parseDouble(annualInterestRateTextField.getText()) / 100;
        int paymentFrequency;
        switch (paymentFrequencyComboBox.getSelectedIndex()) {
            case 0:
                paymentFrequency = 12;
                break;
            case 1:
                paymentFrequency = 26;
                break;
            case 2:
                paymentFrequency = 52;
                break;
            default:
                paymentFrequency = 12;
                break;
        }
        int compoundingFrequency;
        switch (compoundingFrequencyComboBox.getSelectedIndex()) {
            case 0:
                compoundingFrequency = 1;
                break;
            case 1:
                compoundingFrequency = 2;
                break;
            case 2:
                compoundingFrequency = 12;
                break;
            case 3:
                compoundingFrequency = 4;
                break;
            default:
                compoundingFrequency = 2;
                break;
        }
        // creates a new MortgageModel object for principal, annualInterestRate, numPayments, paymentFrequency and compoundingFrequency
        MortgageModel model = new MortgageModel(principal, annualInterestRate, numPayments, paymentFrequency, compoundingFrequency);
        // sets the payment and compounding frequency
        model.setPaymentFrequency(paymentFrequency);
        model.setCompoundingFrequency(compoundingFrequency);
        // sets the output variables
        setBlendedPayment(model.getBlendedMonthlyPayment());
        setTotalInterestPaid(model.getTotalInterestPaid());
        setTotalPaid(model.getTotalInterestAndPrincipal());
        setInterestPrincipalRatio(model.getInterestPrincipalRatio());
        setAverageInterestPerYear(model.getAverageInterestPerYear());
        setAverageInterestPerMonth(model.getAverageInterestPerMonth());
        setAmortizationYears(model.getAmortizationInYears());
    }
}