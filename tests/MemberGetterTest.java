import LibrarySystem.MemberManager;
import LibrarySystem.member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemberGetterTest {

    @BeforeEach
    void resetMembers() {
        MemberManager.members.clear();
    }

    @Test
    void memberGettersShouldReturnStoredMemberData() {

        MemberManager.addMember("M100", "Talha");

        member m = MemberManager.findMember("M100");

        assertNotNull(m);

        assertEquals("M100", m.getmemberId());
        assertEquals("Talha", m.getmemberName());

        assertNotNull(m.getborrowedBooks());
        assertTrue(m.getborrowedBooks().isEmpty());

        assertNotNull(m.getdueDates());
        assertTrue(m.getdueDates().isEmpty());
    }
}