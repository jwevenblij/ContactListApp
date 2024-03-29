package contactcard.contactcardbuttons;

import com.thoughtworks.xstream.XStream;
import contactcard.ContactCardBase;
import contactcard.ContactPerson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveChanges extends JButton {

    XStream xstream = new XStream();
    ContactPerson contactPerson;

    public SaveChanges() {
        addActionListeners();
    }

    // Add actionListeners
    public void addActionListeners() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contactPerson = new ContactPerson();
                contactPerson.setContactID("2039422034");
                contactPerson.setFirstName(ContactCardBase.getFirstName());
                contactPerson.setMiddleName(ContactCardBase.getMiddleName());
                contactPerson.setLastName(ContactCardBase.getLastName());
                contactPerson.seteMail(ContactCardBase.geteMail());
                contactPerson.setCountryCode(ContactCardBase.getCountryCode());
                contactPerson.setPhoneNumber(ContactCardBase.getPhoneNumber());
                contactPerson.setBirthDate(ContactCardBase.getBirthDate());
                contactPerson.setComments(ContactCardBase.getComments());
                contactPerson.setProfilePictureFilePath(ContactCardBase.getProfilePictureFilePath());

                String xml = xstream.toXML(contactPerson);

                System.out.println(xml);
            }
        });
    }
}
