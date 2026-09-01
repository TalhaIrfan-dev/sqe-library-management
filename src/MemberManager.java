import java.util.ArrayList;
import java.util.List;

public class MemberManager
{
    static List<member> members = new ArrayList<>();

    static void addMember(String memberId, String memberName)
    {
        if (memberId == null || memberId.trim().isEmpty())
        {
            throw new IllegalArgumentException("Member ID cannot be empty");
        }

        if (memberName == null || memberName.trim().isEmpty())
        {
            throw new IllegalArgumentException("Member name cannot be empty");
        }

        for (member m : members)
        {
            if (m.memberId.equalsIgnoreCase(memberId.trim()))
            {
                throw new IllegalArgumentException("Member ID already exists");
            }
        }

        member m = new member(memberId.trim(), memberName.trim());
        members.add(m);
    }

    static member findMember(String memberId)
    {
        if (memberId == null || memberId.trim().isEmpty())
        {
            return null;
        }

        String id = memberId.trim();

        for (member m : members)
        {
            if (m.memberId.equalsIgnoreCase(id))
            {
                return m;
            }
        }

        return null;
    }

    static void borrowBook(String memberId, String ISBN)
    {
         if (ISBN == null || ISBN.trim().isEmpty())
        {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }


        member m = findMember(memberId);

        if (m == null)
        {
            throw new IllegalArgumentException("Member not found");
        }

        book selectedBook = BookManager.findBookByISBN(ISBN);

        if (selectedBook == null)
        {
            throw new IllegalArgumentException("Book not found");
        }

        for (book b : m.borrowedBooks)
        {
            if (b.ISBN.equalsIgnoreCase(ISBN.trim()))
            {
                throw new IllegalArgumentException("Member already borrowed this book");
            }
        }

        if (selectedBook.availableCopies <= 0)
        {
            throw new IllegalArgumentException("No copies available");
        }

        if (m.borrowedBooks.size() >= 3)
        {
            throw new IllegalArgumentException("Borrowing limit reached");
        }

        m.borrowedBooks.add(selectedBook);
        selectedBook.availableCopies--;
    }

    static void returnBook(String memberId, String ISBN)
    {
        if (ISBN == null || ISBN.trim().isEmpty())
        {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }

        member m = findMember(memberId);

        if (m == null)
        {
            throw new IllegalArgumentException("Member not found");
        }

        book selectedBook = null;

        for (book b : m.borrowedBooks)
        {
            if (b.ISBN.equalsIgnoreCase(ISBN.trim()))
            {
                selectedBook = b;
                break;
            }
        }

        if (selectedBook == null)
        {
            throw new IllegalArgumentException("Book was not borrowed by this member");
        }

        m.borrowedBooks.remove(selectedBook);
        selectedBook.availableCopies++;
    }
}