package controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

/**
 * Customer Class
 * Comparable sorts on dueDateTime
 */
public class Customer implements Comparable<Customer> {

    private String id;
    private String name;
    private String duetime;
    private String jointime;

    private transient DateTime dueDateTime;

    @Override
    public int compareTo(Customer customerIn) {
        return customerIn.getDueDateTime().compareTo(this.getDueDateTime());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuetime() {
        return duetime;
    }

    public void setDuetime(String duetime) {
        this.duetime = duetime;
    }

    public String getJointime() {
        return jointime;
    }

    public void setJointime(String jointime) {
        this.jointime = jointime;
    }

    @JsonIgnore
    public DateTime getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(DateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }
}
