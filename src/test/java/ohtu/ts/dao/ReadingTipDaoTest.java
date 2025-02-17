package ohtu.ts.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static ohtu.ts.dao.Dao.closeAll;
import ohtu.ts.db.Database;
import ohtu.ts.utils.Configuration;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author ida
 */
public class ReadingTipDaoTest {

    private Configuration configuration;
    private ReadingTipDao readingTipDao;
    private Database db;

    public ReadingTipDaoTest() {
    }

    @Before
    public void setUp() {

        configuration = new Configuration();
        db = new Database();

        try {
            readingTipDao = new ReadingTipDao(db);
        } catch (SQLException e) {
            System.out.println("error");
            System.out.println(e);
        }

        try {
            Connection conn = db.connect();
            String sql = new StringBuilder()
                    .append("INSERT INTO ReadingTip")
                    .append("(type_id, title, author, isbn)")
                    .append("VALUES (?,?,?,?)")
                    .toString();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 1); //Type 1 = Kirja
            stmt.setString(2, "kirjan nimi");
            stmt.setString(3, "kirjailija");
            stmt.setString(4, "isbn-1234");

            stmt.executeUpdate();
            closeAll(stmt, conn);

        } catch (Exception e) {
            throw new AbstractMethodError("Error in inserting book into db: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        //delete database file
        File dbFile = new File(configuration.getDbFile());
        dbFile.delete();
    }

}
