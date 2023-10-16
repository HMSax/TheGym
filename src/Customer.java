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

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(String dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }
}
