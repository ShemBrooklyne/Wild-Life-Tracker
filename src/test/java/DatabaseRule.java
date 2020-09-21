import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
//        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "access", "Access");

        String connectionString = "jdbc:postgresql://ec2-54-160-202-3.compute-1.amazonaws.com:5432/dss83t7hsl3hs"; //!
        Sql2o sql2o = new Sql2o(connectionString, "ohvceqaxugrmnl", "c7c2f789e14d7cfda1ef75fab5adaa6a69fa713f1d651b18ff316873afa628d6"); //!
    }

//    @Override
//    protected void after() {
//        try(Connection con = DB.sql2o.open()) {
//            String deleteAnimalsQuery = "DELETE FROM animals *;";
//            String deleteEndangeredAnimalsQuery = "DELETE FROM endangered_animals *;";
//            String deleteSightingsQuery = "DELETE FROM sightings *;";
//            con.createQuery(deleteAnimalsQuery).executeUpdate();
//            con.createQuery(deleteEndangeredAnimalsQuery).executeUpdate();
//            con.createQuery(deleteSightingsQuery).executeUpdate();
//        }
//    }


}
