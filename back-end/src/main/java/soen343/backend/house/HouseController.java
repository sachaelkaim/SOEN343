package soen343.backend.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rooms")
    public Iterable<House> list() {
        return houseService.list();
    }


}
