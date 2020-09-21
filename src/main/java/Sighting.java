import org.sql2o.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Sighting {
    private int animal_id;
    private String location;
    private String ranger_name;
    private String animal_type;
    private int id;
    private Timestamp sighting;

    public Sighting(int animal_id, String location, String ranger_name, String animal_type) {
        this.animal_id = animal_id;
        this.location = location;
        this.ranger_name = ranger_name;
        this.animal_type = animal_type;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAnimalId() {
        return animal_id;
    }

    public String getAnimal_type(){
        return animal_type;
    }

    public Animal getAnimal() {
        return Animal.find(this.animal_id);
    }

    public Endangered getEndangeredAnimal() {
        return Endangered.find(this.animal_id);
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return ranger_name;
    }

    public Timestamp getSightingTime(){
        return sighting;
    }

    public String getSighting(){
        return DateFormat.getDateTimeInstance().format(getSightingTime());
    }

    @Override
    public boolean equals(Object otherSighting) {
        if(!(otherSighting instanceof Sighting)) {
            return false;
        } else {
            Sighting newSighting = (Sighting) otherSighting;
            return this.getAnimalId() == (newSighting.getAnimalId()) &&
                    this.getLocation().equals(newSighting.getLocation()) &&
                    this.getRangerName().equals(newSighting.getRangerName());
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animal_id, location, ranger_name, animal_type, sighting) VALUES (:animal_id, :location, :ranger_name, :animal_type, now())";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animal_id", this.animal_id)
                    .addParameter("location", this.location)
                    .addParameter("ranger_name", this.ranger_name)
                    .addParameter("animal_type", this.animal_type)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sighting> all() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings;";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE id=:id;";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

}
