package fr.eventmanager.builder;

import fr.eventmanager.model.Event;
import fr.eventmanager.model.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by guillaume-chs on 26/10/16.
 */
public class EventBuilder implements Builder<Event> {
    // Non-nullable fields
    private final String label;
    private final User creator;

    // Nullable fields
    private final Set<User> attendees;
    private String description;
    private Date dateBegin;
    private Date dateEnd;
    private String location;
    private boolean visible;

    public EventBuilder(String label, User creator) {
        this.attendees = new HashSet<>();
        this.label = label;
        this.creator = creator;
    }

    public EventBuilder setDescription(String desc) {
        this.description = desc;
        return this;
    }

    public EventBuilder setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
        return this;
    }

    public EventBuilder setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public EventBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public EventBuilder setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public EventBuilder addAttendee(User attendee) {
        this.attendees.add(attendee);
        return this;
    }

    @Override
    public Event build() {
        // TODO : maybe remove this
        // if (!this.attendees.contains(creator)) {
        //     this.attendees.add(creator);
        // }
        return new Event(creator, label, description, dateBegin, dateEnd, location, visible, attendees);
    }
}
