package de.deepamehta.plugins.contacts.list;

import static de.deepamehta.plugins.contacts.list.ContactURIs.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sun.jersey.api.view.Viewable;

import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.ResultSet;
import de.deepamehta.core.Topic;
import de.deepamehta.core.service.ClientState;
import de.deepamehta.plugins.webactivator.WebActivatorPlugin;

@Path("/contacts")
@Produces("text/html")
public class ContactListPlugin extends WebActivatorPlugin {

    private static Logger log = Logger.getLogger(ContactListPlugin.class.getName());

    private static int PAGE = 5;

    @Override
    public void init() {
        log.info("INIT CALL");
        setupRenderContext();
        log.info("CONTEXT setup COMPLETE: " + context);
    }

    /**
     * @return fist page
     */
    @GET
    public Viewable list(@HeaderParam("Cookie") ClientState cookie) {
        log.info("open contact list");
        return list(1, cookie);
    }

    @GET
    @Path("{page}")
    public Viewable list(@PathParam("page") int page, @HeaderParam("Cookie") ClientState cookie) {
        log.info("view contact page " + page);
        if (page < 1) {
            page = 1; // sanitize page
        }
        List<Contact> contacts = new ArrayList<Contact>();
        log.info("CONTEXT " + context);
        context.setVariable("pageNr", page);
        context.setVariable("contacts", contacts);

        // sort IDs by value
        Map<String, Long> contactIds = new TreeMap<String, Long>();
        long queryStart = System.currentTimeMillis();

        ResultSet<RelatedTopic> persons = dms.getTopics(PERSON, false, 0, cookie);
        context.setVariable("personCount", persons.getSize());
        for (RelatedTopic topic : persons) {
            contactIds.put(topic.getSimpleValue() + "_" + topic.getId(), topic.getId());
        }

        ResultSet<RelatedTopic> institutions = dms.getTopics(INSTITUTION, false, 0, cookie);
        context.setVariable("institutionCount", institutions.getSize());
        for (RelatedTopic topic : institutions) {
            contactIds.put(topic.getSimpleValue() + "_" + topic.getId(), topic.getId());
        }

        context.setVariable("queryTime", System.currentTimeMillis() - queryStart);
        context.setVariable("pageCount", contactIds.size() / PAGE + 1);

        List<Long> ids = new ArrayList<Long>(contactIds.values());
        long fetchStart = System.currentTimeMillis();
        for (int i = PAGE * page - PAGE; i < Math.min(ids.size(), PAGE * page); i++) {
            Topic topic = dms.getTopic(ids.get(i), true, cookie);
            contacts.add(new Contact(i, topic.getModel()));
        }
        context.setVariable("fetchTime", System.currentTimeMillis() - fetchStart);
        return view("contacts");
    }
}
