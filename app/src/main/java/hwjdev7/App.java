
package hwjdev7;

import hwjdev7.servis.Population;
import hwjdev7.storage.Database;
import hwjdev7.storage.DatabaseInitService;
import hwjdev7.storage.DatabasePopulateService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class App {


    public static void main(String[] args) throws SQLException {

        Database database = Database.getInstance();


        new DatabaseInitService().initDb(database);
        new DatabasePopulateService().populDb(database);
        new Population().populationInit();
    }
}
