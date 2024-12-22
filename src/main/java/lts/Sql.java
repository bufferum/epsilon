package lts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;

/**
 *
 * {@link ListIterator#remove() Remove} or
 * {@link ListIterator#add(Object) add} methods, the iterator will throw a
 * {@link ConcurrentModificationException}.  Thus, in the face of
 * @method set_connection
 * @method close_connection
 * @methods select
 * @methods selcet_preparated
 * @methods update

 * @pattern Singleton
 * @version 1.0
 * @author bufferum7011
 */
public class Sql {


    ////////// Constructors //////////
    private Sql() {}


    ////////// Variables //////////
    private static Connection connection;
    private static ResultSet result_set;


    ////////// Methods //////////
    /**
     * Set connection to databases.
     *
     * It doesn't close on its own, - usings pattern Singleton!
     */
    public static Connection set_connect(String ip, String port, String user, String password, String db) {

        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://" + ip + ":" + port + "/" + db,
                user,
                password
            );
        }
        catch(SQLException e) {
            connection = null;
            Print.error("[SQL_SET_CONNECT] - " + e);
        }

        return connection;
    }

    /**
     * Closes the connection.
     *
     * It doesn't close on its own, - using pattern Singleton!
     */
    public static void close_connect() {

        try {
            connection.close();
            connection = null;
            Print.result("[SQL_CLOSE_CONNECT");
        }
        catch(SQLException e) {
            Print.error("[SQL_CLOSE_CONNECT] - " + e + "\n");
        }

    }


    /** Execute SQL query without return response */
    public static void update(String sql_query) {

        try {
            connection.createStatement().executeUpdate(sql_query);
            Print.way("[SQL_UPDATE]");
        }
        catch(SQLException e) {
            Print.error("[SQL_UPDATE] - " + e + "\n");
        }

    }

    /**
     * Data in the form of a table
     *
     * @return ResultSet
     */
    public static ResultSet select(String sql_query) {

        try {
            result_set = connection.createStatement().executeQuery(sql_query);
            Print.result("[SQL_CALLBACK]");
        }
        catch(Exception e) {
            result_set = null;
            Print.error("[SQL_CALLBACK] - " + e + "\n");
        }

        return result_set;
    }

    /**
     * It works on the basis of a {@code PreparedStatement}.
     * Quantity {@code ?} to {@code sql_query}, should match with {@code parameters_prepared}!
     */
    public static ResultSet select_prepared(String sql_query, String... parameters_prepared) {

        try {
            PreparedStatement prepared_statement = connection.prepareStatement(
                sql_query,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );

            for(int i = 0; i < parameters_prepared.length; i++) {
                prepared_statement.setString(++i, parameters_prepared[--i]);
            }

            result_set = prepared_statement.executeQuery();
            Print.result("[SQL_CALLBACK_PREPARED]");
        }
        catch(Exception e) {
            result_set = null;
            Print.error("[SQL_CALLBACK_PREPARED] - " + e + "\n");
        }

        return result_set;
    }


}