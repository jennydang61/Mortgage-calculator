package lab4;

public class MortgageController {
    // instance variables
    private MortgageModel model;
    private MortgageView view;
    // takes MortgageModel and MortgageView as arguments
    public MortgageController(MortgageModel model, MortgageView view) {
        this.model = model;
        this.view = view;
    }
    // constructor to create default values for the parameters
    public MortgageController() {
        this(new MortgageModel(0, 0, 0, 0, 0), new MortgageView());
    }
    // sets the visibility of MortgageView to true
    public void run() {
        view.setVisible(true);
    }
    // static main method to call its run method
    public static void main(String[] args) {
        MortgageController controller = new MortgageController();
        controller.run();
    }
}
