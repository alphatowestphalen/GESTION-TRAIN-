package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {

    public Connection getConnexion()  {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/ges_train","root","");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Lachée votre Serveurs");
        }
        return connection;
    }
}
