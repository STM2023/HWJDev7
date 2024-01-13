package hwjdev7.storage;

import hwjdev7.prefs.Prefs;
import hwjdev7.storage.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class DatabasePopulateService {
    private static PreparedStatement insertWorker;
    private static PreparedStatement insertClient;
    private static PreparedStatement insertProject;
    private static PreparedStatement insertProjectWorker;

    public static void populDb(Database database) throws SQLException {
        String[]  sqlPopul= new String[4];
        try {
            String populateFilename = new Prefs().getString(Prefs.POPULATE_DB_SQL_FILE_PATH);

            String sql = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(populateFilename)));
            //      System.out.println(sql);

            int l=0;
            StringBuilder result = new StringBuilder();

            for (int i=0; i<sql.length();i++){
                if(sql.charAt(i)==';'){
                    sqlPopul[l]=result.toString();
                    result = new StringBuilder();
                    l++;
                } else{
                    result.append(sql.charAt(i));
                }
            }


            Connection connection = database.getConnection();
//"INSERT INTO worker(name, birthday, level, salary) VALUES (?,?,?,?);"
            insertWorker = connection.prepareStatement(sqlPopul[0] );
            //"INSERT INTO  client ( name)  VALUES ( ?)"
            insertClient = connection.prepareStatement( sqlPopul[1]  );
            //"INSERT INTO project(client_id, start_date, finish_date) VALUES ( ?, ?, ?)"
            insertProject = connection.prepareStatement( sqlPopul[2] );
            //"INSERT INTO project_worker (project_id,  worker_id) VALUES ( ?, ? )"
            insertProjectWorker = connection.prepareStatement( sqlPopul[3] );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean createNewWorker(String name, LocalDate birthday, String level, int salary) {
        try {

            insertWorker.setString(1, name);
            insertWorker.setString(2, birthday.toString());
            insertWorker.setString(3, level);
            insertWorker.setInt(4, salary);
            return insertWorker.executeUpdate() == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean createClient(String name) {
        try {
            insertClient.setString(1, name);
            return insertClient.executeUpdate() == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean createProject(int client_id, LocalDate start_date, LocalDate finish_date) {
        try {
            insertProject.setInt(1, client_id);
            insertProject.setString(2, start_date.toString());
            insertProject.setString(3, finish_date.toString());
            return insertProject.executeUpdate() == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public static boolean createProjectWorker(int project_id,int   worker_id) {
        try {
            insertProjectWorker.setInt (1, project_id);
            insertProjectWorker.setInt (2, worker_id);
            return insertProjectWorker.executeUpdate() == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
