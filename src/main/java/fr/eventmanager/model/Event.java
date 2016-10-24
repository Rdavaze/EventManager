package fr.eventmanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Event entity to store events data.
 */
@Entity
@Table(name = Event.tableName)
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "Event";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "label", nullable = false, length = 50, unique = true)
    private String label;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @ManyToMany()
    @JoinTable(name = "attendees", joinColumns = @JoinColumn(name = "id_event"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    private Set<User> attendees = new HashSet<>();

    public Event() {
    }

    public Event(String label) {
        this.label = label;
    }

    public Event(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<User> attendees) {
        this.attendees = attendees;
    }

    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (o instanceof Event) {
            return this.id == ((Event) o).id;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%d]:%s", id, label);
    }
}
