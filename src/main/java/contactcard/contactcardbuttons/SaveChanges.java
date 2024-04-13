package contactcard.contactcardbuttons;

import com.thoughtworks.xstream.XStream;
import contactcard.ContactCardBase;
import contactcard.ContactCardFull;
import contactcard.ContactPerson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class SaveChanges extends JButton {

    XStream xstream = new XStream();
    String filePath = "";
    ContactPerson contactPerson;

    String firstFreeID = "";

    public SaveChanges() {
        addActionListeners();
    }

    // Add actionListeners
    public void addActionListeners() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createContactPersonObject();
                contactPersonToHashCodeXML();
            }
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
