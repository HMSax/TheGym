import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    Customer curre = new Customer("1234564321", "Curre Magén", "2019-08-18");

    @Test
    public void updateMembershipTest() {    //Testar att metoden ändrar medlemsdatum till dagens datum.
        curre.updateMembership();

        assertTrue(curre.getDateOfMembership().equals(LocalDate.now().toString()));
        assertFalse(curre.getDateOfMembership().equals("2019-08-18"));
    }

}