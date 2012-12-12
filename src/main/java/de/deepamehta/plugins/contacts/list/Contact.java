package de.deepamehta.plugins.contacts.list;

import java.util.ArrayList;
import java.util.List;

import de.deepamehta.core.model.TopicModel;

// Bean that wraps person and institution topics
public class Contact extends TopicBean {

    private int nr;

    public Contact(int nr, TopicModel topic) {
        super(topic);
        this.nr = nr;
    }

    public int getNr() {
        return nr;
    }

    public long getId() {
        return topic.getId();
    }

    public String getName() {
        return topic.getSimpleValue().toString();
    }

    public List<Address> getAddresses() {
        List<Address> addresses = new ArrayList<Address>();
        if (topic.getCompositeValue().has("dm4.contacts.address_entry")) {
            for (TopicModel address : topic.getCompositeValue().getTopics(
                    "dm4.contacts.address_entry")) {
                addresses.add(new Address(address));
            }
        }
        return addresses;
    }

    public List<String> getMails() {
        return getCompositeValueList("dm4.contacts.email_address");
    }

    public List<Phone> getPhones() {
        List<Phone> phones = new ArrayList<Phone>();
        if (topic.getCompositeValue().has("dm4.contacts.phone_entry")) {
            for (TopicModel phone : topic.getCompositeValue().getTopics("dm4.contacts.phone_entry")) {
                phones.add(new Phone(phone));
            }
        }
        return phones;
    }

    private List<String> getCompositeValueList(String uri) {
        List<String> mails = new ArrayList<String>();
        if (topic.getCompositeValue().has(uri)) {
            for (TopicModel topicModel : topic.getCompositeValue().getTopics(uri)) {
                mails.add(topicModel.getSimpleValue().toString());
            }
        }
        return mails;
    }
}
