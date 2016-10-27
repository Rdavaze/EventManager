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
    // Event parameters
    private final Set<User> attendees;
    private final User creator;
    private String label;
    private String description;
    private Date date;
    private String location;
    private boolean visible;

    public EventBuilder(User creator) {
        this.attendees = new HashSet<>();
        this.creator = creator;
    }

    public EventBuilder setLabel(String label) {
        this.label = label;
        return this;
    }

    public EventBuilder setDescription(String desc) {
        this.description = desc;
        return this;
    }

    public EventBuilder setDate(Date date) {
        this.date = date;
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
        if (!this.attendees.contains(creator)) {
            this.attendees.add(creator);
        }
        return new Event(creator, label, description, date, location, visible, attendees);
    }
}
