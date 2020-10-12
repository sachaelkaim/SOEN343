package soen343.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class House {

    @Id
    private String name;
    private Long windows;
    private Long lights;

    public House() {
    }

    public String getName() {
        return name;
    }

    public Long getWindows() {
        return windows;
    }

    public Long getLights() {
        return lights;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWindows(Long windows) {
        this.windows = windows;
    }

    public void setLights(Long lights) {
        this.lights = lights;
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", windows=" + windows +
                ", lights=" + lights +
                '}';
    }
}
