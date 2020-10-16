package soen343.backend.house;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000") //to unblock request to/from react
@RequestMapping("api/")
public class HouseController {

    @JsonSerialize
    public class EmptyJsonResponse { }

    @Autowired
    private HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rooms")
    public Iterable<House> list() {
        if(true){ //if simulation is on, send house layout
            return houseService.list();
        }
        else{
            return (Iterable<House>) new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK); // if simulation is off, send empty json
        }
    }

}
