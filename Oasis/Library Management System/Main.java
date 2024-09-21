import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Sample Data
        Book book1 = new Book("Harry Potter", "J.K. Rowling", "12345");
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", "67890");

        Member member1 = new Member("John Doe", 1);
        Member member2 = new Member("Jane Doe", 2);

        library.addBook(book1);
        library.addBook(book2);
        library.addMember(member1);
        library.addMember(member2);

        // Menu-based interaction
        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Display Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");

            System.out.println("Enter the Choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.println("Enter member ID:");
                    int memberId = scanner.nextInt();
                    System.out.println("Enter book ISBN:");
                    String isbn = scanner.next();
                    library.borrowBook(memberId, isbn);
                    break;
                case 3:
                    System.out.println("Enter member ID:");
                    memberId = scanner.nextInt();
                    System.out.println("Enter book ISBN:");
                    isbn = scanner.next();
                    library.returnBook(memberId, isbn);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

