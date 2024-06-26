package contactcard.contactcardbuttons;

import com.thoughtworks.xstream.XStream;
import contactcard.ContactCardBase;
import contactcard.ContactPerson;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveChanges extends JButton {

    XStream xstream = new XStream();
    String filePath = "";
    public String oldFilePath = "";
    ContactPerson contactPerson;

    public SaveChanges() {
        addActionListeners();
    }

    // Add actionListeners
    public void addActionListeners() {
        this.addActionListener(e -> {
            createContactPersonObject();
            contactPersonToHashCodeXML();
        });
    }

    // Create contactPerson object
    public void createContactPersonObject() {
        contactPerson = new ContactPerson();
        contactPerson.setFirstName(ContactCardBase.getFirstName());
        contactPerson.setMiddleName(ContactCardBase.getMiddleName());
        contactPerson.setLastName(ContactCardBase.getLastName());
        contactPerson.seteMail(ContactCardBase.geteMail());
        contactPerson.setCountryCode(ContactCardBase.getCountryCode());
        contactPerson.setPhoneNumber(ContactCardBase.getPhoneNumber());
        contactPerson.setBirthDate(ContactCardBase.getBirthDate());
        contactPerson.setComments(ContactCardBase.getComments());
        contactPerson.setProfilePictureFilePath(ContactCardBase.getProfilePictureFilePath());
        contactPerson.setContactID(ContactCardBase.getCurrentHashCode());
    }

    // Write contactPerson to 'hashcode'.XML
    public void contactPersonToHashCodeXML() {

        Path delFile = new File("src/main/contacts/" + oldFilePath + ".xml").toPath();
        File fileExists = new File(String.valueOf(delFile));
        System.out.println(delFile);

        if (fileExists.exists()) {
            try {
                Files.delete(delFile);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }

        if(contactPerson.getContactID() == 0) {
            contactPerson.setContactID(contactPerson.hashCode());
            System.out.println(contactPerson.getContactID());

            filePath = ("src/main/contacts/" +
                    contactPerson.getFirstName() + "_" +
                    contactPerson.getLastName() + "_" +
                    contactPerson.getContactID() + ".xml");
        } else {
            System.out.println(contactPerson.getContactID());
            filePath = ("src/main/contacts/" +
                    contactPerson.getFirstName() + "_" +
                    contactPerson.getLastName() + "_" +
                    contactPerson.getContactID() + ".xml");
        }

        try {
            String xml = xstream.toXML(contactPerson);
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(xml);
            fileWriter.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
