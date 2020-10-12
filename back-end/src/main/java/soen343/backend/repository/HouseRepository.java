package soen343.backend.repository;

import org.springframework.data.repository.CrudRepository;
import soen343.backend.model.House;

public interface HouseRepository extends CrudRepository<House, String> {

}
