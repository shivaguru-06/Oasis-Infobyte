import java.util.ArrayList;

public class Member {
    private String name;
    private int memberId;
    private ArrayList<Book> borrowedBooks;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (!book.isBorrowed()) {
            borrowedBooks.add(book);
            book.borrowBook();
            System.out.println(name + " has borrowed " + book.getTitle());
        } else {
            System.out.println("The book is currently unavailable.");
        }
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.returnBook();
        System.out.println(name + " has returned " + book.getTitle());
    }
}

