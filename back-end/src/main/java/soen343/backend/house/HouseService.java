package soen343.backend.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HouseService {

    @Autowired
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
