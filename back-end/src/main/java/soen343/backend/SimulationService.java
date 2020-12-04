package soen343.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import soen343.backend.console.Console;
import soen343.backend.console.ConsoleService;
import soen343.backend.room.Room;
import soen343.backend.room.RoomRepository;
import soen343.backend.room.RoomService;
import soen343.backend.state.StateService;
import soen343.backend.user.User;
import soen343.backend.user.UserRepository;
import soen343.backend.user.UserService;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The type Simulation service.
 */
@EnableScheduling
@Service
public class SimulationService {

    private static CoreModuleModel coreModel;
    private static SecurityModuleModel securityModel;
    private List<HeatingModuleModel> zones = new ArrayList<>();
    private static int zoneCounter = 0;
    private static boolean regulateZone = false;
    private static boolean initializeArr = false;
    private ArrayList<Integer> periods = new ArrayList<>();
    private ArrayList<Double> temperatures = new ArrayList<>();

    /**
     * The Intruder present.
     */
    boolean intruderPresent = false;
    /**
     * The Authorities called.
     */
    boolean authoritiesCalled = false;
    private LocalDateTime timeToNotifyAuthorities = null;

    @Autowired
    private  UserService users;

    @Autowired
    private  ConsoleService notifications;

    @Autowired
    private RoomService roomService;

    @Autowired
    private StateService state;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    /* DATE AND TIME */

    /**
     * Start date and time.
     */
    @Bean
    public void startDateAndTime(){
        //simulationDateTime = LocalDateTime.of(2014, 6, 30, 12, 00);
        CoreModuleModel.simulationDateTime = LocalDateTime.now();
        CoreModuleModel.setSimulationDateTime(CoreModuleModel.simulationDateTime);
    }

    /**
     * Date and time.
     */
    @Scheduled(fixedRate=1000)
    public void dateAndTime() {
        if(state.getCurrentState()) {
            CoreModuleModel.simulationDateTime = CoreModuleModel.simulationDateTime.plusSeconds(CoreModuleModel.timeSpeed);
            CoreModuleModel.setSimulationDateTime(CoreModuleModel.simulationDateTime);
        }
    }

    /* SHC FEATURES*/

    /**
     * Set light.
     *
     * @param userLocation the user location
     * @param privilege    the privilege
     * @param location     the location
     * @param lightOn      the light on
     */
    public void setLight(String userLocation, String privilege, String location, boolean lightOn){
        if(state.getCurrentState()){
            if(privilege.equals("0")){
                roomService.onOffLight(location,lightOn);
                if(lightOn == true)
                    notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + " light is on."));
                else
                    notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + " light is off."));
            }
            if(privilege.equals("1") || privilege.equals("2")){
                if(userLocation.equals("Outside")){
                    notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + ". Guest/Child does not have permission."));
                }
                else if(userLocation.equals(location)){
                    roomService.onOffLight(location,lightOn);
                    if(lightOn == true){
                        notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + " light is on."));
                    }
                    else
                        notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + " light is off."));
                }
                else
                    notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + ". Guest/Child does not have permission."));
            }
            if(privilege.equals("3")){
                notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + ". Stranger does not have permission."));
            }
        }
    }

    /**
     * Set door.
     *
     * @param privilege the privilege
     * @param location  the location
     * @param doorLock  the door lock
     */
    public void setDoor(String privilege, String location, String doorLock){
        if(state.getCurrentState()){
            if(privilege.equals("0")){
                roomService.unlockLockDoor(location,doorLock);
                if(doorLock.equals("UNLOCKED"))
                    notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + " door is unlocked."));
                else
                    notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + " door is locked."));
            }
            else
                notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + ". Stranger/Guest/Child does not have permission."));
        }
    }

    /**
     * Set window.
     *
     * @param userLocation the user location
     * @param privilege    the privilege
     * @param location     the location
     * @param windowOpen   the window open
     */
    public void setWindow(String userLocation, String privilege, String location, String windowOpen){
        if(state.getCurrentState()){
            if(privilege.equals("0")){
                boolean notBlocked = roomService.openCloseWindow(location,windowOpen);
                if(notBlocked){
                    if(windowOpen.equals("OPEN"))
                        notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + " light is on."));
                    else
                        notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + " light is off."));
                }
            }
            if(privilege.equals("1") || privilege.equals("2")){
                if(userLocation.equals("Outside")){
                    notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + ". Guest/Child does not have permission."));
                }
                else if(userLocation.equals(location)){
                    boolean notBlocked = roomService.openCloseWindow(location,windowOpen);
                    if(notBlocked){
                        if(windowOpen.equals("OPEN"))
                            notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + " light is on."));
                        else
                            notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + " light is off."));
                    }
                }
                else
                    notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + ". Guest/Child does not have permission."));
            }
            if(privilege.equals("3")){
                notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",location + ". Stranger does not have permission."));
            }
        }
    }

    /**
     * Set auto mode.
     *
     * @param autoMode the auto mode
     */
    public void setAutoMode(boolean autoMode){
        CoreModuleModel.setAutoMode(autoMode);
        if(state.getCurrentState()){
            if(autoMode){
                notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",  "Auto Mode is on."));
                autoMode();
            }
            else
                notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",  "Auto Mode is off."));
        }
    }

    /**
     * Auto mode.
     */
    public void autoMode(){
        if(CoreModuleModel.isAutoMode()) {
            Iterable<User> users = userRepository.findAll();
            Iterator<User> iter = users.iterator();
            while (iter.hasNext()) {
                User user = iter.next();
                Iterable<Room> rooms = roomRepository.findAll();
                Iterator<Room> iter1 = rooms.iterator();
                while(iter1.hasNext()){
                    Room room = iter1.next();
                    if(user.getLocation().equals(room.getName())){
                        room.setLightOn(true);
                        roomRepository.save(room);
                    }
                }
            }
            Iterable<Room> rooms = roomRepository.findAll();
            Iterator<Room> iter2 = rooms.iterator();
            while(iter2.hasNext()){
                int i = 0;
                Room room = iter2.next();
                Iterator<User> iter3 = users.iterator();
                while (iter3.hasNext()) {
                    User user = iter3.next();
                    if (user.getLocation().equals(room.getName())){
                        i++;
                    }
                }
                if(i == 0){
                    room.setLightOn(false);
                    roomRepository.save(room);
                    notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHC",  room.getName() + " light is off."));
                }
            }
        }
    }

    /* SHP FEATURES*/

    /**
     * Set away mode.
     *
     * @param awayMode      the away mode
     * @param userPrivilege the user privilege
     */
    public void setAwayMode(boolean awayMode, String userPrivilege){
        authoritiesCalled = false;
        intruderPresent = false;
        if(state.getCurrentState())
            if(awayMode){
                if(users.allUsersOutside()){
                    if(userPrivilege.equals("2") || userPrivilege.equals("3")){
                        notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHP","Away mode cannot be set by Stranger/Guest"));
                    }
                    else{
                        roomService.closeDoorsWindows();
                        notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHP","Away mode is on."));
                        SecurityModuleModel.setAwayMode(true);
                    }
                }
                else{
                    notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHP","Away mode cannot be set. All users are not outside."));
                }
            }
            else{
                SecurityModuleModel.setAwayMode(false);
                notifications.saveNotification(new Console(CoreModuleModel.dateTime,"SHP","Away mode is off."));
            }
    }

    /**
     * Check intruders.
     */
    @Scheduled(fixedRate=1000)
    public void checkIntruders() {
        if (state.getCurrentState()) {
            if (SecurityModuleModel.isAwayMode() && !intruderPresent) {
                if (!users.allUsersOutside()) {
                    notifications.saveNotification(new Console(CoreModuleModel.dateTime, "SHC", "There are intruders..."));
                    intruderPresent = true;
                    timeToNotifyAuthorities = CoreModuleModel.simulationDateTime.plusMinutes(SecurityModuleModel.timeCallAuthorities);
                    notifications.saveNotification(new Console(CoreModuleModel.dateTime, "SHC", "Authorities will be called in " + SecurityModuleModel.timeCallAuthorities + " minutes."));
                }
            }
        }
        if(intruderPresent){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String dateTime1 = timeToNotifyAuthorities.format(formatter);
            String dateTime2 = CoreModuleModel.simulationDateTime.format(formatter);
            if(dateTime1.equals(dateTime2) && authoritiesCalled == false){
                notifications.saveNotification(new Console(CoreModuleModel.dateTime, "SHP", "Calling the authorities!"));
                authoritiesCalled = true;
            }
        }
    }


    /* SHH FEATURES*/

    public ArrayList<String> availableLocations (){
        ArrayList<String> empty = new ArrayList<>();
        if(state.getCurrentState()){
            ArrayList<String> availableRooms = new ArrayList<>();
            Iterable<Room> rooms = roomRepository.findAll();
            Iterator<Room> iter1 = rooms.iterator();
            while(iter1.hasNext()) {
                Room room = iter1.next();
                if(!"Outside".equals(room.getName()) && !"Backyard".equals(room.getName())){
                    availableRooms.add(room.getName());
                }
            }
            zones.forEach(d -> {
                ArrayList<String> arr = new ArrayList<>(d.getLocations());
                arr.forEach(i -> {
                    availableRooms.remove(i);
                });
            });
            return availableRooms;
        }
        else return (ArrayList<String>) empty;
    }

    public void addZone (ArrayList<String> locations, String privilege){
        if(privilege.equals("0")){
            HeatingModuleModel zone = new HeatingModuleModel();
            zone.setLocations(locations);
            zoneCounter++;
            zone.setZone("Zone" + zoneCounter);
            zones.add(zone);
            notifications.saveNotification(new Console(CoreModuleModel.dateTime, "SHH", "New " + zone.getZone() + " : " + zone.getLocations() + "."));
        }
      else notifications.saveNotification(new Console(CoreModuleModel.dateTime, "SHH", "No Permission!"));
    }

    public List<HeatingModuleModel> displayZones() {
        return zones;
    }

    public void setZoneTemperature(String privilege, String zone, int period, double temperature){
        if(privilege.equals("0")){
            zones.forEach(i -> {
                if( i.getZone().equals(zone)){
                    if(initializeArr == false){
                        periods.add(-1);
                        periods.add(-1);
                        periods.add(-1);
                        temperatures.add(-1.0);
                        temperatures.add(-1.0);
                        temperatures.add(-1.0);
                        initializeArr = true;
                    }
                    periods.set(period, period);
                    temperatures.set(period, temperature);
                    i.setPeriods(periods);
                    i.setTemperatures(temperatures);
                    String displayPeriod = null;
                   if(period == 0){
                       displayPeriod = " between 00:00 and 08:00.";
                   }
                    if(period == 1){
                        displayPeriod = " between 08:00 and 16:00.";
                    }
                    if(period == 2){
                        displayPeriod = " between 16:00 and 00:00.";
                    }
                    notifications.saveNotification(new Console(CoreModuleModel.dateTime, "SHH", zone + " temperature set at " + temperatures.get(period) + "C" + displayPeriod));
                }
                regulateZone = true;
            });
        }
        else notifications.saveNotification(new Console(CoreModuleModel.dateTime, "SHH", "No Permission!"));
    }

    @Scheduled(fixedRate=1000)
    public void regulateZoneTemperatures(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        int time = Integer.parseInt(CoreModuleModel.simulationDateTime.format(formatter));
        Iterable<Room> rooms = roomRepository.findAll();
        Iterator<Room> iter1 = rooms.iterator();
        if(state.getCurrentState() && regulateZone){
            zones.forEach(i -> {
                ArrayList<Integer> period = new ArrayList<>(i.getPeriods());
                ArrayList<Double> temperature = new ArrayList<>(i.getTemperatures());
                System.out.println(period);
                System.out.println(temperature);
                period.forEach(j -> {
                    ArrayList<String> zoneRooms = new ArrayList<>(i.getLocations());
                    if(j == 0) { // 00:00 and 08:00 increase/decrease temp until desired temp
                        if (time >= 0 && time < 8) {
                            zoneRooms.forEach(d -> {
                                while (iter1.hasNext()) {
                                    Room room = iter1.next();
                                    if (d.equals(room.getName())) {
                                        if (temperature.get(j) != room.getTemperature()) {
                                            i.setHVAC(true);
                                            double temp2 = room.getTemperature();
                                            if (room.getTemperature() > temperature.get(j)) {
                                                temp2 -= 0.1;
                                            }
                                            if (room.getTemperature() < temperature.get(j)) {
                                                temp2 += 0.1;
                                            }
                                            double temp3 = temp2;
                                            DecimalFormat df = new DecimalFormat("#.##");
                                            temp2 = Double.valueOf(df.format(temp3));
                                            room.setTemperature(temp2);
                                            roomRepository.save(room);
                                        }
                                    }
                                }
                            });
                        }
                    }
                    else if(j == 1) { // 08:00 and 16:00 increase/decrease temp until desired temp
                        if (time >= 8 && time < 16) {
                            zoneRooms.forEach(d -> {
                                while (iter1.hasNext()) {
                                    Room room = iter1.next();
                                    if (d.equals(room.getName())) {
                                        if (temperature.get(j) != room.getTemperature()) {
                                            i.setHVAC(true);
                                            double temp2 = room.getTemperature();
                                            if (room.getTemperature() > temperature.get(j)) {
                                                temp2 -= 0.1;
                                            }
                                            if (room.getTemperature() < temperature.get(j)) {
                                                temp2 += 0.1;
                                            }
                                            double temp3 = temp2;
                                            DecimalFormat df = new DecimalFormat("#.##");
                                            temp2 = Double.valueOf(df.format(temp3));
                                            room.setTemperature(temp2);
                                            roomRepository.save(room);
                                        }
                                    }
                                }
                            });
                        }
                    }
                    else if(j == 2) { // 16:00 and 00:00 increase/decrease temp until desired temp
                        if (time >= 16 && time != 0) {
                            zoneRooms.forEach(d -> {
                                while (iter1.hasNext()) {
                                    Room room = iter1.next();
                                    if (d.equals(room.getName())) {
                                        if (temperature.get(j) != room.getTemperature()) {
                                            i.setHVAC(true);
                                            double temp2 = room.getTemperature();
                                            if (room.getTemperature() > temperature.get(j)) {
                                                temp2 -= 0.1;
                                            }
                                            if (room.getTemperature() < temperature.get(j)) {
                                                temp2 += 0.1;
                                            }
                                            double temp3 = temp2;
                                            DecimalFormat df = new DecimalFormat("#.##");
                                            temp2 = Double.valueOf(df.format(temp3));
                                            room.setTemperature(temp2);
                                            roomRepository.save(room);
                                        }
                                    }
                                }
                            });
                        }
                    }
                });

            });
        }
    }

    public Iterable<Room> getCurrentTemperatures(){
        Iterable<Room> rooms = roomRepository.findAll();
        return rooms;
    }


}
