package contactcard;

public class ContactPerson {

    // contact
    private int contactID;
    private String firstName;
    private String middleName;
    private String lastName;
    private int countryCode;
    private String birthDate;
    private String comments;
    private int FK_eMail;
    private int FK_phoneNumber;

    // eMail
    private int eMail_ID;
    private String eMail;

    // phoneNumber
    private int phoneNumber_ID;
    private int phoneNumber;

    public ContactPerson() {

    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getFK_eMail() {
        return FK_eMail;
    }

    public void setFK_eMail(int FK_eMail) {
        this.FK_eMail = FK_eMail;
    }

    public int getFK_phoneNumber() {
        return FK_phoneNumber;
    }

    public void setFK_phoneNumber(int FK_phoneNumber) {
        this.FK_phoneNumber = FK_phoneNumber;
    }

    public int geteMail_ID() {
        return eMail_ID;
    }

    public void seteMail_ID(int eMail_ID) {
        this.eMail_ID = eMail_ID;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getPhoneNumber_ID() {
        return phoneNumber_ID;
    }

    public void setPhoneNumber_ID(int phoneNumber_ID) {
        this.phoneNumber_ID = phoneNumber_ID;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
