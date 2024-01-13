package hwjdev7.storage;

import hwjdev7.prefs.Prefs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseInitService {

    public static void initDb(Database database) {

        String connectiondbUrl = new Prefs().getString(Prefs.DB_URL);

        try {
            String initDbFilename = new Prefs().getString(Prefs.INIT_DB_SQL_FILE_PATH);

            String sql = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(initDbFilename)));
            database.executeUpdate(sql);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
