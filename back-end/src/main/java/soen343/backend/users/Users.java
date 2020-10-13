package soen343.backend.users;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {

    @Id
    private Long id;
    private String name;
    private String location;
    private Long priority; // 0 parent, 1 child, 2 guest, 3 strangers
    private Long active;   // 0 inactive, 1 active
}
