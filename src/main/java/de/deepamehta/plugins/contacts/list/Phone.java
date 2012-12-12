package de.deepamehta.plugins.contacts.list;

import de.deepamehta.core.model.TopicModel;

public class Phone extends TopicBean {

    public Phone(TopicModel topic) {
        super(topic);
    }

    public String getLabel() {
        return getValue("dm4.contacts.phone_label");
    }

    public String getNumber() {
        return getValue("dm4.contacts.phone_number");
    }

}
