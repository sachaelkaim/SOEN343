package soen343.backend.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The type User.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String location;
    private String privilege; // 0 parent, 1 child, 2 guest, 3 strangers

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param name      the name
     * @param location  the location
     * @param privilege the privilege
     */
    public User( String name, String location, String privilege) {
        super();
        this.name = name;
        this.location = location;
        this.privilege = privilege;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets privilege.
     *
     * @return the privilege
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * Sets privilege.
     *
     * @param privilege the privilege
     */
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", privilege='" + privilege + '\'' +
                '}';
    }
}
