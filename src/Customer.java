public class Customer {
    private String name;
    private String personalNumber;
    private String dateOfMembership;

    public Customer(String name, String personalNumber, String dateOfMembership) {
        this.name = name;
        this.personalNumber = personalNumber;
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
