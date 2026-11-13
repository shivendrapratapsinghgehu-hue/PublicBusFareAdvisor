
public class Journey {

    private BusRoute route;

    private int journeysPerDay;
    private int travelDays;
    private int passengerCount;

    private String passengerCategory;
    private double discountRate;

    // Constructor
    public Journey(BusRoute route,
                   int journeysPerDay,
                   int travelDays,
                   int passengerCount,
                   String passengerCategory,
                   double discountRate) {

        this.route = route;
        this.journeysPerDay = journeysPerDay;
        this.travelDays = travelDays;
        this.passengerCount = passengerCount;
        this.passengerCategory = passengerCategory;
        this.discountRate = discountRate;
    }

    // Calculate original ticket cost
    public double calculateOriginalTicketCost() {

        return route.getOneWayFare()
                * journeysPerDay
                * travelDays
                * passengerCount;
    }

    // Calculate discounted ticket cost
    public double calculateTicketCost() {

        double originalCost = calculateOriginalTicketCost();

        double discount = originalCost * discountRate;

        return originalCost - discount;
    }

    // Calculate discount amount
    public double calculateDiscountAmount() {

        return calculateOriginalTicketCost() * discountRate;
    }

    // Get route
    public BusRoute getRoute() {
        return route;
    }

    // Get journeys per day
    public int getJourneysPerDay() {
        return journeysPerDay;
    }

    // Get travel days
    public int getTravelDays() {
        return travelDays;
    }

    // Get passenger count
    public int getPassengerCount() {
        return passengerCount;
    }

    // Get passenger category
    public String getPassengerCategory() {
        return passengerCategory;
    }

    // Get discount rate
    public double getDiscountRate() {
        return discountRate;
    }
}
