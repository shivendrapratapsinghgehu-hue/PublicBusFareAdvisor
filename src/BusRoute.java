
public class BusRoute {

    private String routeName;
    private double oneWayFare;

    // Constructor
    public BusRoute(String routeName, double oneWayFare) {
        this.routeName = routeName;
        this.oneWayFare = oneWayFare;
    }

    // Get route name
    public String getRouteName() {
        return routeName;
    }

    // Get one-way fare
    public double getOneWayFare() {
        return oneWayFare;
    }

    // Display route details
    public void displayRoute() {
        System.out.println(routeName + " - Rs. " + oneWayFare);
    }
}
