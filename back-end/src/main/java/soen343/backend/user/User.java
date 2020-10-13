package soen343.backend.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private String id;
    private String name;
    private String location;
    private String priority; // 0 parent, 1 child, 2 guest, 3 strangers

    public User() {
    }

    public User(String id, String name, String location, String priority) {
        super();
        this.id = id;
        this.name = name;
        this.location = location;
        this.priority = priority;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

}
