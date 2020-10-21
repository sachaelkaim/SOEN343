package soen343.backend.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String location;
    private String privilege; // 0 parent, 1 child, 2 guest, 3 strangers

    /**
     * Default constructor for User Class
     */
    public User() {
    }

    /**
     * User Constructor with parameters
     * @param name
     * @param location
     * @param privilege
     */
    public User( String name, String location, String privilege) {
        super();

        this.name = name;
        this.location = location;
        this.privilege = privilege;
    }

    /**
     * Function to get the Id of a User
     * @return Id
     */
    public Long getId() {
        return id;
    }

    /**
     * Function to set a value for Id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Function to retrieve the User's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Function to set the name of the User
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Function to retrieve the User's location
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Function to set the location of the User
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Function to retrieve the User's privilege
     * @return
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * Function to set the User's privilege
     * @param privilege
     */
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

}
