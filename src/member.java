import java.util.ArrayList;
import java.util.List;

public class member
{
    String memberId;
    String memberName;
    List<book> borrowedBooks = new ArrayList<>();

    member(String memberId, String memberName)
    {
        this.memberId = memberId;
        this.memberName = memberName;
    }
}