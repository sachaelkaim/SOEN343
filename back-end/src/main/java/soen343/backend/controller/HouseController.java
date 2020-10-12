package soen343.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import soen343.backend.model.House;
import soen343.backend.service.HouseService;

@RestController
public class HouseController {

    private HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rooms")
    public Iterable<House> list() {
        return houseService.list();
    }


}
