package de.deepamehta.plugins.contacts.list;

import static de.deepamehta.plugins.contacts.list.ContactURIs.*;
import de.deepamehta.core.model.TopicModel;

public class Phone extends TopicBean {

    public Phone(TopicModel topic) {
        super(topic);
    }

    public String getLabel() {
        return getValue(PHONE_LABEL);
    }

    public String getNumber() {
        return getValue(PHONE_NUMBER);
    }

}
