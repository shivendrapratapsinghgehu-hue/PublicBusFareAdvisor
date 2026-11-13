
public class TravelPass {

    private String passName;
    private double passPrice;
    private int validityDays;

    // Constructor
    public TravelPass(String passName, double passPrice, int validityDays) {

        this.passName = passName;
        this.passPrice = passPrice;
        this.validityDays = validityDays;
    }

    // Get pass name
    public String getPassName() {
        return passName;
    }

    // Get pass price
    public double getPassPrice() {
        return passPrice;
    }

    // Get validity
    public int getValidityDays() {
        return validityDays;
    }
}
