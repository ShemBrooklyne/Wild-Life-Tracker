import org.sql2o.Sql2o;

import java.net.URI;
import java.net.URISyntaxException;

public class DB {
    //        public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife", "access", "Access");
    private static URI dbUri;
    public static Sql2o sql2o;
    static {

        try {
            if (System.getenv("DATABASE_URL") == null) {
                dbUri = new URI("postgres://postgres:password@localhost:5432/wildlife");
            } else {
                dbUri = new URI(System.getenv("DATABASE_URL"));
            }
            int port = dbUri.getPort();
            String host = dbUri.getHost();
            String path = dbUri.getPath();
            String username = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[0];
            String password = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[1];
            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
        } catch (URISyntaxException e ) {
        }
    }
}