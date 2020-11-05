package soen343.backend.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soen343.backend.room.Room;

@Service
public class ConsoleService {

    @Autowired
    private ConsoleRepository consoleRepository;

    public Iterable<Console> getAllNotifications(){
        return consoleRepository.findAll();
    }

    public void saveNotification(Console console){
        consoleRepository.save(console);
    }
}
