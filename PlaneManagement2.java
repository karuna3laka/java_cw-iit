import java.util.Scanner;

public class PlaneManagement2 {

    private static final int NumberOfRows = 4; // Able to assign any seating pattern in any airline
    private static final int[] SeatPerRows = {14, 12, 12, 14};
    private static int[][] seatPattern;
    private static Ticket[][] soldTickets; //to part 9 th one

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize seats
        initializeSeats();
        printMenu();
        int choice;
        do {
            System.out.println("User Menu:");
            System.out.println("1. Buy a seat");
            System.out.println("2. Cancel a Seat");
            System.out.println("3. Find First A vailable Seat");
            System.out.println("4. Show Seating Plan");
            System.out.println("5. Print tickets infromation and total sales");
            System.out.println("6. Search Ticket");
            System.out.println("0. Quit");
            System.out.println("");
            System.out.println("*********************************************************");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                
                System.out.print("Enter the row letter (A, B, C, D): ");
                char rowLabel = scanner.next().charAt(0);
                System.out.print("Enter the seat number: ");
                int seat = scanner.nextInt();
                int row = rowLabel - 'A' + 1; // Convert row label to row number 
                buy_seat(row, seat);
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
                find_first_available();
                break;

                case 4:
                AvailableSeats();       //display availble seats
                break;

                case 5:
                print_Ticket_Info();
                break;

                case 6:
                    
                case 0:
                    System.out.println("Exiting program. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        

    }

    
    //SUB CLASESS SECTION*
    
    private static void initializeSeats() {
        seatPattern = new int[NumberOfRows][];
        for (int i = 0; i < NumberOfRows; i++) {
            seatPattern[i] = new int[SeatPerRows[i]];
        }
    }

    private static void AvailableSeats() {
        System.out.println("Available Seats:");
        for (int i = 0; i < NumberOfRows; i++) {
            char rowLabel = (char) ('A' + i);
            System.out.print(rowLabel + " ");
            for (int j = 0; j < SeatPerRows[i]; j++) {
                System.out.print(seatPattern[i][j] == 0 ? "O " : "X ");
            }
            System.out.println();
        }
    }

    private static void buy_seat(int row, int seat) {
        if (row > 0 && row <= NumberOfRows && seat > 0 && seat <= SeatPerRows[row - 1]) {
            if (seatPattern[row - 1][seat - 1] == 0) { //check is seat avalable
                seatPattern[row - 1][seat - 1] = 1; // Mark seat as booked
                System.out.println("Seat booked successfully!");

                Person person = new Person(name, surname, email);
                 // Calculate price (you can modify this based on your pricing logic)
                int price = calculatePrice(row, seat);
                Ticket ticket = new Ticket(row, seat, price, person);
                soldTickets[row - 1][seat - 1] = ticket;


            } else {
                System.out.println("Sorry, the seat is already booked. Please try another seat.");
            }
        } else {
            System.out.println("Invalid seat selection. Please check seat pattern and Try Again !");
        }
    }

    private static void cancel_seat(int row, int seat) {
        if (row > 0 && row <= NumberOfRows && seat > 0 && seat <= SeatPerRows[row - 1]) {
            if (seatPattern[row - 1][seat - 1] == 1) { //Check if seat is booked
                seatPattern[row - 1][seat - 1] = 0; // Mark seat as available
                soldTickets[row - 1][seat - 1] = null;
                System.out.println("Seat canceled successfully!");
            } else {
                System.out.println("Sorry, the seat is not booked. Please check and try another seat.");
            }
        } else {
            System.out.println("Invalid seat selection. Please check seat pattern and try again!");
        }
    }

    private static void find_first_available() {
        for (int i = 0; i < NumberOfRows; i++) {
            char rowLabel = (char) ('A' + i);
            for (int j = 0; j < SeatPerRows[i]; j++) {
                if (seatPattern[i][j] == 0) {
                    // Found the first available seat
                    System.out.println("The first available seat is in row " + rowLabel + ", seat " + (j + 1) + ".");
                    return; 
                }
            }
        }
        System.out.println("Sorry, no available seats found.");
    }

    private static void printMenu(){
        System.out.println("*********************************************************");
        System.out.println(" ");
        System.out.println("*                   MENU OPTION                         *");
        System.out.println(" ");
        System.out.println("*********************************************************");
    }

public class Person {
    private String name;
    private String surname;
    private String email;

        // constructor making
        public Person(String name, String surname, String email) {
            this.name = name;
            this.surname = surname;
            this.email = email;

        }

        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getSurname() {
            return surname;
        }
    
        public void setSurname(String surname) {
            this.surname = surname;
        }
    
        public String getEmail() {
            return email;
        }
    
        public void setEmail(String email) {
            this.email = email;
        }
    
}
    public void printPersonInfo() {
        System.out.println("Person Information:");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
    }
}
public class Ticket {
    private String raw ;
    private String seat ;
    private Integer price ;
    private Person person;     //calling from previous Person class 

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    //**************************************************************** */
    public void print_Ticket_Info() {
        int totalSales = 0;
        System.out.println("Ticket Information:");
        System.out.println("Row: " + raw);
        System.out.println("Seat: " + seat);
        System.out.println("Price: $" + price);
        System.out.println("Person Information:");
        person.printPersonInfo();
    
        for (int i = 0; i < NumberOfRows; i++) {
            for (int j = 0; j < SeatPerRows[i]; j++) {
                if (soldTickets[i][j] != null) {
                    System.out.println("Ticket Information for Row " + (char) ('A' + i) + ", Seat " + (j + 1) + ":");
                    soldTickets[i][j].print_Ticket_Info(); // Corrected method name
                    totalSales += soldTickets[i][j].getPrice();
                }
            }
        }
        System.out.println("Total Sales: $" + totalSales);
    }


}



