import java.time.LocalDate;

public class Customer {
    private String personalNumber;
    private String name;
    private String dateOfMembership;

    public Customer(String personalNumber, String name, String dateOfMembership) {
        this.personalNumber = personalNumber;
        this.name = name;
        this.dateOfMembership = dateOfMembership;
    }

    public String getName() {
        return name;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getDateOfMembership() {
        return dateOfMembership;
    }

    public void updateMembership() {                            //F.d. medlem betalar ny medlemsavgift idag.
        this.dateOfMembership = LocalDate.now().toString();     //Används ej i huvudprogrammets nuvarande form.
    }

}
