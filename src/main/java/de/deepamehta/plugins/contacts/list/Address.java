package de.deepamehta.plugins.contacts.list;

import static de.deepamehta.plugins.contacts.list.ContactURIs.*;
import de.deepamehta.core.model.TopicModel;

public class Address extends TopicBean {

    private String label;

    public Address(TopicModel topic) {
        super(topic);
        this.label = getValue(ADDRESS_LABEL);
        // rebase the topic
        this.topic = topic.getCompositeValue().getTopic(ADDRESS);
    }

    public String getLabel() {
        return label;
    }

    public String getStreet() {
        return getValue(STREET);
    }

    public String getCode() {
        return getValue(POSTAL_CODE);
    }

    public String getCity() {
        return getValue(CITY);
    }
}
