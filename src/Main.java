
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    // Bus Route Catalogue
    static BusRoute[] routes = {

        new BusRoute("City Center to University", 15),
        new BusRoute("Railway Station to University", 20),
        new BusRoute("Bus Stand to University", 10),
        new BusRoute("Market to University", 12),
        new BusRoute("Airport to University", 35)
    };

    // Travel Pass Catalogue
    static TravelPass[] passes = {

        new TravelPass("Weekly Pass", 250, 7),
        new TravelPass("Monthly Pass", 700, 30)
    };

    // Current journey
    static Journey currentJourney = null;


    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n======================================");
            System.out.println("       PUBLIC BUS FARE ADVISOR");
            System.out.println("======================================");

            System.out.println("1. View Fare Catalogue");
            System.out.println("2. Enter Journey Details");
            System.out.println("3. Compare Travel Passes");
            System.out.println("4. View Savings Report");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");

            choice = readInt(1, 5);

            switch (choice) {

                case 1:
                    displayFareCatalogue();
                    break;

                case 2:
                    enterJourneyDetails();
                    break;

                case 3:
                    compareTravelPasses();
                    break;

                case 4:
                    showSavingsReport();
                    break;

                case 5:
                    System.out.println("\nThank you for using Public Bus Fare Advisor!");
                    break;
            }

        } while (choice != 5);

        sc.close();
    }


    // Safe integer input
    public static int readInt(int min, int max) {

        int value;

        while (true) {

            if (sc.hasNextInt()) {

                value = sc.nextInt();

                if (value >= min && value <= max) {
                    return value;
                }

            } else {

                sc.next();
            }

            System.out.print("Enter a valid number ("
                    + min + "-" + max + "): ");
        }
    }


    // Display Fare Catalogue
    public static void displayFareCatalogue() {

        System.out.println("\n========== FARE CATALOGUE ==========");

        for (int i = 0; i < routes.length; i++) {

            System.out.print((i + 1) + ". ");

            routes[i].displayRoute();
        }
    }


    // Enter Journey Details
    public static void enterJourneyDetails() {

        displayFareCatalogue();

        System.out.print("\nSelect your route: ");

        int routeChoice = readInt(1, routes.length);

        System.out.print("Enter journeys per day (1-10): ");

        int journeys = readInt(1, 10);

        System.out.print("Enter travel days (1-30): ");

        int days = readInt(1, 30);

        System.out.print("Enter number of passengers (1-20): ");

        int passengers = readInt(1, 20);


        // Passenger Category
        System.out.println("\nSelect Passenger Category:");

        System.out.println("1. General Passenger");
        System.out.println("2. Student");
        System.out.println("3. Senior Citizen");

        System.out.print("Enter category choice: ");

        int categoryChoice = readInt(1, 3);

        String category;
        double discount;


        if (categoryChoice == 1) {

            category = "General Passenger";
            discount = 0.0;

        } else if (categoryChoice == 2) {

            category = "Student";
            discount = 0.20;

        } else {

            category = "Senior Citizen";
            discount = 0.30;
        }


        // Create Journey Object
        currentJourney = new Journey(

                routes[routeChoice - 1],
                journeys,
                days,
                passengers,
                category,
                discount
        );


        System.out.println("\nJourney details saved successfully!");

        System.out.println("Selected Route: "
                + currentJourney.getRoute().getRouteName());

        System.out.println("Journeys Per Day: " + journeys);

        System.out.println("Travel Days: " + days);

        System.out.println("Total Passengers: " + passengers);

        System.out.println("Passenger Category: " + category);

        System.out.println("Discount: " + (discount * 100) + "%");

        System.out.println("Final Ticket Expense: Rs. "
                + currentJourney.calculateTicketCost());
    }


    // Calculate Travel Pass Cost
    public static double calculatePassCost(TravelPass pass) {

        int days = currentJourney.getTravelDays();

        int numberOfPasses = (int) Math.ceil(

                days / (double) pass.getValidityDays()
        );

        return numberOfPasses
                * pass.getPassPrice()
                * currentJourney.getPassengerCount();
    }


    // Find Cheapest Pass
    public static TravelPass findBestPass() {

        TravelPass bestPass = passes[0];

        for (int i = 1; i < passes.length; i++) {

            if (calculatePassCost(passes[i])
                    < calculatePassCost(bestPass)) {

                bestPass = passes[i];
            }
        }

        return bestPass;
    }


    // Compare Travel Passes
    public static void compareTravelPasses() {

        if (currentJourney == null) {

            System.out.println("\nPlease enter journey details first!");

            return;
        }

        System.out.println("\n========== PASS COMPARISON ==========");

        double ticketCost = currentJourney.calculateTicketCost();

        System.out.println("Discounted Ticket Expense: Rs. "
                + ticketCost);

        for (int i = 0; i < passes.length; i++) {

            double cost = calculatePassCost(passes[i]);

            System.out.println(passes[i].getPassName()
                    + ": Rs. " + cost);
        }

        TravelPass bestPass = findBestPass();

        double bestCost = calculatePassCost(bestPass);

        if (bestCost < ticketCost) {

            System.out.println("\nRecommended Pass: "
                    + bestPass.getPassName());

            System.out.println("Total Savings: Rs. "
                    + (ticketCost - bestCost));

        } else {

            System.out.println("\nRecommendation: Buy Normal Tickets.");

            System.out.println("Travel passes are not cheaper.");
        }
    }


    // Savings Report
    public static void showSavingsReport() {

        if (currentJourney == null) {

            System.out.println("\nPlease enter journey details first!");

            return;
        }

        double originalCost =
                currentJourney.calculateOriginalTicketCost();

        double discountAmount =
                currentJourney.calculateDiscountAmount();

        double ticketCost =
                currentJourney.calculateTicketCost();

        TravelPass bestPass = findBestPass();

        double passCost = calculatePassCost(bestPass);


        System.out.println("\n======================================");

        System.out.println("            SAVINGS REPORT");

        System.out.println("======================================");

        System.out.println("Route: "
                + currentJourney.getRoute().getRouteName());

        System.out.println("Passenger Category: "
                + currentJourney.getPassengerCategory());

        System.out.println("Total Passengers: "
                + currentJourney.getPassengerCount());

        System.out.println("Journeys Per Day: "
                + currentJourney.getJourneysPerDay());

        System.out.println("Travel Days: "
                + currentJourney.getTravelDays());

        System.out.println("--------------------------------------");

        System.out.println("Original Ticket Cost: Rs. "
                + originalCost);

        System.out.println("Discount Applied: "
                + (currentJourney.getDiscountRate() * 100) + "%");

        System.out.println("Discount Amount: Rs. "
                + discountAmount);

        System.out.println("Discounted Ticket Cost: Rs. "
                + ticketCost);

        System.out.println("--------------------------------------");

        System.out.println("Recommended Pass: "
                + bestPass.getPassName());

        System.out.println("Pass Expense: Rs. "
                + passCost);


        if (passCost < ticketCost) {

            System.out.println("Final Savings: Rs. "
                    + (ticketCost - passCost));

            System.out.println("Final Advice: Buy "
                    + bestPass.getPassName());

        } else {

            System.out.println("Final Savings: Rs. 0");

            System.out.println("Final Advice: Buy Normal Tickets.");
        }

        System.out.println("======================================");
    }
}
