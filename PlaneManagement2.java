import java.util.Scanner;

public class PlaneManagement2 {
    private static final int numberOfRows = 4;
    private static final int[] seatPerRows = {14, 12, 12, 14};
    private static int[][] seatPattern;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*********************************************************");
        System.out.println(" ");
        System.out.println("*                   MENU OPTION                         *");
        System.out.println(" ");
        System.out.println("*********************************************************");
        System.out.println("Hello and welcome!");
        PlaneManagement2 planeManagement = new PlaneManagement2();
        planeManagement.initializeSeats();          //KEEP THIS OUT SIDE LOOP BCZ IT KEEP RESETTING ARRAY.
        int choice;
        do {

            planeManagement.printMenu();//PRINT PATTERN
            planeManagement.printMenu();

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the row letter (A, B, C, D): ");
                    char rowLabel = scanner.next().charAt(0);
                    System.out.print("Enter the seat number: ");
                    int seat = scanner.nextInt();
                    int row = rowLabel - 'A' + 1;
                    planeManagement.buySeat(row, seat);
                    break;

                case 2:
                    System.out.print("Enter the row letter (A, B, C, D): ");
                    char row_ca = scanner.next().charAt(0);
                    System.out.print("Enter the seat number: ");
                    int seat_cancel = scanner.nextInt();
                    int row_cancel = row_ca - 'A' + 1; // Fix: Use 'row_ca' instead of 'rowLabel'
                    cancel_seat(row_cancel, seat_cancel);
                    break;

                case 3:
                    find_first_available();// Implement find first available seat logic
                    break;

                case 4:
                    planeManagement.displayAvailableSeats();
                    break;

                case 5:
                    // Implement printing tickets information and total sales
                    break;

                case 6:
                    // Implement searching for a ticket
                    break;

                case 0:
                    System.out.println("Exiting program. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
//**************************************************************************************//
    private void initializeSeats() {
        seatPattern = new int[numberOfRows][];
        for (int i = 0; i < numberOfRows; i++) {
            seatPattern[i] = new int[seatPerRows[i]];
        }
        // Additional initialization logic if needed
    }

    private void buySeat(int row, int seat) {
        if (row > 0 && row <= numberOfRows && seat > 0 && seat <= seatPerRows[row - 1]) {
            if (seatPattern[row - 1][seat - 1] == 0) { //check is seat avalable
                seatPattern[row - 1][seat - 1] = 1; // Mark seat as booked
                System.out.println("Seat booked successfully!");
            } else {
                System.out.println("Sorry, the seat is already booked. Please try another seat.");
            }
        } else {
            System.out.println("Invalid seat selection. Please check seat pattern and try again!");
        }
    }

    private  void displayAvailableSeats() {
        System.out.println("Available Seats:");
        for (int i = 0; i < numberOfRows; i++) {
            char rowLabel = (char) ('A' + i);
            System.out.print(rowLabel + " ");
            for (int j = 0; j < seatPerRows[i]; j++) {
                System.out.print(seatPattern[i][j] == 0 ? "O " : "X ");
            }
            System.out.println();
        }
    }


    private static void find_first_available() {
        for (int i = 0; i < numberOfRows; i++) {
            char rowLabel = (char) ('A' + i);
            for (int j = 0; j < seatPerRows[i]; j++) {
                if (seatPattern[i][j] == 0) {
                    // Found the first available seat
                    System.out.println("The first available seat is in row " + rowLabel + ", seat " + (j + 1) + ".");
                    return;
                }
            }
        }
        System.out.println("Sorry, no available seats found.");
    }
    private static void cancel_seat(int row, int seat) {
        if (row > 0 && row <= numberOfRows && seat > 0 && seat <= seatPerRows[row - 1]) {
            if (seatPattern[row - 1][seat - 1] == 1) { //Check if seat is booked
                seatPattern[row - 1][seat - 1] = 0; // Mark seat as available
                //soldTickets[row - 1][seat - 1] = null;
                System.out.println("Seat canceled successfully!");
            } else {
                System.out.println("Sorry, the seat is not booked. Please check and try another seat.");
            }
        } else {
            System.out.println("Invalid seat selection. Please check seat pattern and try again!");
        }
    }
    private void printMenu() {
        System.out.println("User Menu:");
        System.out.println("1. Buy a seat");
        System.out.println("2. Cancel a Seat");
        System.out.println("3. Find First Available Seat");
        System.out.println("4. Show Seating Plan");
        System.out.println("5. Print tickets information and total sales");
        System.out.println("6. Search Ticket");
        System.out.println("0. Quit");
        System.out.println("*********************************************************");
    }


}

