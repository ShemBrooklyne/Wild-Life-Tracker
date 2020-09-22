import org.sql2o.Sql2o;

public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "access", "Access");


//    String connectionString = "jdbc:postgresql://ec2-54-160-202-3.compute-1.amazonaws.com:5432/dss83t7hsl3hs"; //!
//    Sql2o sql2o = new Sql2o(connectionString, "ohvceqaxugrmnl", "c7c2f789e14d7cfda1ef75fab5adaa6a69fa713f1d651b18ff316873afa628d6"); //!
//

//    postgres://ohvceqaxugrmnl:c7c2f789e14d7cfda1ef75fab5adaa6a69fa713f1d651b18ff316873afa628d6@ec2-54-160-202-3.compute-1.amazonaws.com:5432/dss83t7hsl3hs

}
