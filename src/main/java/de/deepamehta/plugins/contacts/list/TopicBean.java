package de.deepamehta.plugins.contacts.list;

import de.deepamehta.core.model.TopicModel;

public abstract class TopicBean {

    protected TopicModel topic;

    public TopicBean(TopicModel topic) {
        this.topic = topic;
    }

    public String getValue(String uri) {
        if (topic.getCompositeValue().has(uri)) {
            return topic.getCompositeValue().getTopic(uri).getSimpleValue().toString();
        } else {
            return null;
        }
    }

}
