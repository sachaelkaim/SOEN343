package soen343.backend.service;

import org.springframework.stereotype.Service;
import soen343.backend.model.House;
import soen343.backend.repository.HouseRepository;

import java.util.List;


@Service
public class HouseService {

    private HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public Iterable<House> list(){
        return houseRepository.findAll();
    }

    public void save(List<House> rooms){
        houseRepository.saveAll(rooms);
    }



}
