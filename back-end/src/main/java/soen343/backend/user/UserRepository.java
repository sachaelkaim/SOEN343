package soen343.backend.user;

import org.springframework.data.repository.CrudRepository;

/**
 * The interface User repository.
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
