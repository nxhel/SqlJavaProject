/**
 * Class for loading data from a database.
 * 
 * @author Nihel Madani-Fouatih
 * @lastUpdated 2023-11-29
 */
package finaljavaproject.DataProcessing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import finaljavaproject.Members.*;
import finaljavaproject.Members.Customer.*;
import finaljavaproject.Products.*;
import oracle.jdbc.OracleTypes;

public class LoadFromDatabase implements IDatabaseImporter {
    private Connection conn;
    private String user;
    private String pwd;

    /**
     * Constructs a new LoadFromDatabase instance with the specified user and password.
     *
     * @param user The username for connecting to the database.
     * @param pwd  The password for connecting to the database.
     */
    public LoadFromDatabase(String user, String pwd){
        this.user=user;
        this.pwd=pwd;
        try{
            String url = "jdbc:oracle:thin:@198.168.52.211:1521/pdbora19c.dawsoncollege.qc.ca";
            this.conn = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads movies from the database.
     *
     * @return A list of DVDs representing movies loaded from the database.
     */
    @Override
    public List<Dvd> loadMoviesFromDatabse() {
        List<Dvd> allMoviesListed = new ArrayList<>();
    
        try {

            CallableStatement callableStatement = this.conn.prepareCall("{ call javaFinalProject.GetMovies(?)}");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet result = (ResultSet) callableStatement.getObject(1);
            while (result.next()) {
                double price = result.getDouble("price");
                String title = result.getString("title");
                Genre genre = Genre.valueOf(result.getString("genre"));
                String sn = result.getString("serialNumber");
                double duration = result.getDouble("movieDuration");
                int isAvailableValue = result.getInt("isAvailable");
                boolean isAvailable = (isAvailableValue == 1);
                double disc = result.getDouble("discount");
                int qty = result.getInt("quantity");
                String dir = result.getString("director");
                String stud = result.getString("studio");
    
                Dvd dvd = new Movie(price, title, genre, sn, duration, isAvailable, disc, qty, dir, stud);
                allMoviesListed.add(dvd);
            }
    
            if (callableStatement != null) {
                callableStatement.close();
            }
            this.conn.close();
        } catch (SQLException e) {
            System.out.println("Error occurred in GettingMovies From the DatabaseSQL");
            e.printStackTrace();
        }
        return allMoviesListed;
    }
    
         
    /**
     * Unimplemented method for loading store members from the database.
     *
     * @return An UnsupportedOperationException as this method is not implemented.
     */
    @Override
    public List <StoreMember> loadStoreMembersFromDatabse(){
       throw new UnsupportedOperationException("Unimplemented method 'loadStoreMembersFromDatabse'");
    }

    /**
     * Unimplemented method for loading customers from the database.
     *
     * @return An UnsupportedOperationException as this method is not implemented.
     */
    @Override
    public List <Customer> loadCustomersFromDatabase() {
        throw new UnsupportedOperationException("Unimplemented method 'loadCustomersFromDatabase'");
    }
}
    

