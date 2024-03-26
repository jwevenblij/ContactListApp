package contactcard;

public class ContactPerson {
    // contact
    int contactID = 0;
    String firstName = "";
    String middleName = "";
    String lastName = "";
    int countryCode = 0;
    int birthDateDay = 0;
    int birthDateMonth = 0;
    int birthDateYear = 0;
    String birthDate = "";
    String comments = "";
    int FK_eMail = 0;
    int FK_phoneNumber = 0;

    // eMail
    int eMail_ID = 0;
    String eMail = "";

    // phoneNumber
    int phoneNumber_ID = 0;
    int phoneNumber = 0;

    public ContactPerson() {

    }

    public void setContactPerson(
        int contactID,
        String firstName,
        String middleName,
        String lastName,
        int countryCode,
        int birthDateDay,
        int birthDateMonth,
        int birthDateYear,
        String birthDate,
        String comments,
        int FK_eMail,
        int FK_phoneNumber,
        int eMail_ID,
        String eMail,
        int phoneNumber_ID,
        int phoneNumber) {

        this.contactID = contactID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.countryCode = countryCode;
        this.birthDateDay = birthDateDay;
        this.birthDateMonth = birthDateMonth;
        this.birthDateYear = birthDateYear;
        this.birthDate = birthDate;
        this.comments = comments;
        this.FK_eMail = FK_eMail;
        this.FK_phoneNumber = FK_phoneNumber;
        this.eMail_ID = eMail_ID;
        this.eMail = eMail;
        this.phoneNumber_ID = phoneNumber_ID;
        this.phoneNumber = phoneNumber;
    }

    private void newContactSQL() {

    }

    private void editContactSQL() {

    }

    private void deleteContactSQL() {

    }
}
