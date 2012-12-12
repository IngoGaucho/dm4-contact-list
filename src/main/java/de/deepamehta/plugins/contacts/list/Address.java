package de.deepamehta.plugins.contacts.list;

import de.deepamehta.core.model.TopicModel;

public class Address extends TopicBean {

    private String label;

    public Address(TopicModel topic) {
        super(topic);
        this.label = getValue("dm4.contacts.address_label");
        // rebase the topic
        this.topic = topic.getCompositeValue().getTopic("dm4.contacts.address");
    }

    public String getLabel() {
        return label;
    }

    public String getStreet() {
        return getValue("dm4.contacts.street");
    }

    public String getCode() {
        return getValue("dm4.contacts.postal_code");
    }

    public String getCity() {
        return getValue("dm4.contacts.city");
    }
}
