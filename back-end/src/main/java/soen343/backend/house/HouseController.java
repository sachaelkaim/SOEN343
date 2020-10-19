package soen343.backend.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000") //to unblock request to/from react
@RequestMapping("api/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    //@Bean
   // @ConditionalOnProperty()
    @RequestMapping(method = RequestMethod.GET, value = "/house")
    public Iterable<House> list() {
        return houseService.list();
    }

}
