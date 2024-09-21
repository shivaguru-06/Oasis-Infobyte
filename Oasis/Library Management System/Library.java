import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.getTitle() + " added to the library.");
    }

    public void removeBook(Book book) {
        books.remove(book);
        System.out.println(book.getTitle() + " removed from the library.");
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("New member added: " + member.getName());
    }

    public void borrowBook(int memberId, String isbn) {
        Member member = findMemberById(memberId);
        Book book = findBookByISBN(isbn);

        if (member != null && book != null) {
            member.borrowBook(book);
        } else {
            System.out.println("Invalid member ID or book ISBN.");
        }
    }

    public void returnBook(int memberId, String isbn) {
        Member member = findMemberById(memberId);
        Book book = findBookByISBN(isbn);

        if (member != null && book != null) {
            member.returnBook(book);
        } else {
            System.out.println("Invalid member ID or book ISBN.");
        }
    }

    private Book findBookByISBN(String isbn) {
        for (Book book : books) {
            if (book.getISBN().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    private Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getISBN() + ")");
        }
    }
}

