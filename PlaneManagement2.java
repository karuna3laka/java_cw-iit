import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class PlaneManagement2 {
    private static final int numberOfRows = 4;
    private static final int[] seatPerRows = {14, 12, 12, 14};
    private static int[][] seatPattern;
    private static final ArrayList<Ticket> soldTicketsList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

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
                    int row_cancel = row_ca - 'A' + 1;
                    cancel_seat(row_cancel, seat_cancel);
                    break;

                case 3:
                    find_first_available();
                    break;

                case 4:
                    planeManagement.displayAvailableSeats();
                    break;

                case 5:
                    // Create a dummy ticket for demonstration purposes
                    String Row = "A";
                    int Seat = 1;
                    int Price = 100;
                    Person dummyPerson = new Person("Dummy", "Person", "dummy@example.com");

                    // Create a Ticket instance
                    Ticket dummyTicket = new Ticket(Row, Seat, Price, dummyPerson);

                    // Call the printTicketInfo method on the Ticket instance
                    dummyTicket.printTicketInfo();
                    break;


                case 6:
                    planeManagement.search_ticket();
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
    //ADDING SEAT NUMBERS TO ARRAY AND STUFF
    private void initializeSeats() {
        seatPattern = new int[numberOfRows][];
        for (int i = 0; i < numberOfRows; i++) {
            seatPattern[i] = new int[seatPerRows[i]];
        }

    }

    private void buySeat(int row, int seat) {
        if (row > 0 && row <= numberOfRows && seat > 0 && seat <= seatPerRows[row - 1]) {
            if (seatPattern[row - 1][seat - 1] == 0) { // Check if seat is available
                System.out.print("Enter person's name: ");
                String name = scanner.next();
                System.out.print("Enter person's surname: ");
                String surname = scanner.next();
                System.out.print("Enter person's email: ");
                String email = scanner.next();

                // Create a Person instance
                Person person = new Person(name, surname, email);

                System.out.print("Enter the ticket price: ");
                int price = scanner.nextInt();

                // Create a Ticket instance
                Ticket ticket = new Ticket(String.valueOf((char) ('A' + row - 1)), seat, price, person);
                seatPattern[row - 1][seat - 1] = 1; // Mark seat as booked

                // Add the ticket to the soldTicketsList
                soldTicketsList.add(ticket);

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
            if (seatPattern[row - 1][seat - 1] == 1) {
                seatPattern[row - 1][seat - 1] = 0;

                // Using Iterator to safely remove the ticket
                Iterator<Ticket> iterator = soldTicketsList.iterator();
                while (iterator.hasNext()) {
                    Ticket ticket = iterator.next();
                    if (ticket.getRow().equals(String.valueOf((char) ('A' + row - 1))) && ticket.getSeat() == seat) {
                        iterator.remove(); // Safe removal
                        System.out.println("Seat canceled successfully!");
                        return;
                    }
                }
                System.out.println("Error: Ticket not found in the soldTicketsList.");
            } else {
                System.out.println("Sorry, the seat is not booked. Please check and try another seat.");
            }
        } else {
            System.out.println("Invalid seat selection. Please check seat pattern and try again!");
        }
    }

    private void search_ticket() {
        System.out.print("Enter the row letter (A, B, C, D): ");
        char rowLabel = scanner.next().charAt(0);
        System.out.print("Enter the seat number: ");
        int seatNumber = scanner.nextInt();

        for (Ticket ticket : soldTicketsList) {
            if (ticket.getRow().equals(String.valueOf(rowLabel)) && ticket.getSeat() == seatNumber) {
                System.out.println("Ticket found:");
                ticket.printTicketInfo();
                return;
            }
        }

        System.out.println("Ticket not found.");
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
    //*********************************************************************************
    public static class Person {
        private  String name;
        private  String surname;
        private  String email;

        public Person(String name, String surname, String email) {
            this.name = name;
            this.surname = surname;
            this.email = email;
        }
        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public String getEmail() {
            return email;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void printPersonInfo() {
            System.out.println("Person Information:");
            System.out.println("Name: " + name);
            System.out.println("Surname: " + surname);
            System.out.println("Email: " + email);
        }
    }
    public static class Ticket {
        private  String row;
        private int seat;
        private int price;
        private  Person person;

        public Ticket(String row, int seat, int price, Person person) {
            this.row = row;
            this.seat = seat;
            this.price = price;
            this.person = person;
        }

        public String getRow() {
            return row;
        }


        public int getSeat() {
            return seat;
        }


        public int getPrice() {
            return price;
        }


        public Person getPerson() {
            return person;
        }

        public void setRow(String row) {
            this.row = row;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setPerson(Person person) {
            this.person = person;
        }

        public void printTicketInfo() {
            System.out.println("Ticket Information:");
            System.out.println("Row: " + row);
            System.out.println("Seat: " + seat);
            System.out.println("Price: £" + price);
            person.printPersonInfo();
        }

        public void saveToFile() {
            // Implementation to save Ticket information to a file (e.g., A2.txt)
            // You can use file I/O operations here
        }
    }

}

