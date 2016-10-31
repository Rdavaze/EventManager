package fr.eventmanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Event entity to store events data.
 */
@Entity
@Table(name = Event.tableName)
public class Event implements Serializable {
    public static final String tableName = "Event";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(name = "label", nullable = false, length = 50, unique = true)
    private String label;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_begin")
    private Date dateBegin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_end")
    private Date dateEnd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_begin")
    private Date timeBegin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_end")
    private Date timeEnd;

    @Column(name = "location")
    private String location;

    @Column(name = "visible", nullable = false)
    private boolean visible = false;

    @ManyToMany()
    @JoinTable(name = "attendees", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> attendees = new HashSet<>();

    public Event() {
    }

    public Event(User creator, String label, String description, Date dateBegin, Date dateEnd, Date timeBegin, Date timeEnd, String location, boolean visible, Set<User> attendees) {
        this.creator = creator;
        this.label = label;
        this.description = description;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.location = location;
        this.visible = visible;
        this.attendees = attendees;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Set<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<User> attendees) {
        this.attendees = attendees;
    }

    public boolean addAttendee(User attendee) {
        return this.attendees.add(attendee);
    }

    public boolean removeAttendee(User attendee) {
        return this.attendees.remove(attendee);
    }

    public Date getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Date timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (o instanceof Event) {
            return this.id.equals(((Event) o).id);
        }
        return false;
    }

    @Override
    public String toString() {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);
        final Integer nbAttendees = attendees.size();
        return String.format("%s (%s, %s) : %d attendee%c", label, dateFormat.format(dateBegin), location, nbAttendees, (nbAttendees > 1) ? 's' : ' ');
    }


    public String parseTimeBegin() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        if (this.getTimeBegin() != null) {
            return dateFormat.format(this.getTimeBegin());
        }


        return "";
    }

    public String parseTimeEnd() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        if (this.getTimeEnd() != null)
            return dateFormat.format(this.getTimeEnd());

        return "";
    }

    public String parseDateBegin() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMMM yyyy");
        if (this.getDateBegin() != null) {
            return dateFormat.format(this.getDateBegin());
        }


        return "";
    }

    public String parseDateEnd() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMMM yyyy");
        if (this.getDateEnd() != null)
            return dateFormat.format(this.getDateEnd());

        return "";
    }

}
