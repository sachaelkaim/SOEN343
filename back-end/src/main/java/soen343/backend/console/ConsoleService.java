package soen343.backend.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soen343.backend.room.Room;

/**
 * The type Console service.
 */
@Service
public class ConsoleService {

    @Autowired
    private ConsoleRepository consoleRepository;

    /**
     * Get all notifications iterable.
     *
     * @return the iterable
     */
    public Iterable<Console> getAllNotifications(){
        return consoleRepository.findAll();
    }

    /**
     * Save notification.
     *
     * @param console the console
     */
    public void saveNotification(Console console){
        consoleRepository.save(console);
    }
}
