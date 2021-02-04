package unit;

import org.dbunit.Assertion;
import org.dbunit.DatabaseTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class Test_UtenteDAO extends DatabaseTestCase {
    private FlatXmlDataSet loadedDataSet;

    protected IDatabaseConnection getConnection() {
        try {
            Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movietipsdb?serverTimezone=" + TimeZone.getDefault().getID(), "root", "MovieTips");
            return new DatabaseConnection(jdbcConnection);
        } catch (SQLException | DatabaseUnitException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected IDataSet getDataSet() throws IOException, DataSetException {
        loadedDataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("input.xml"));
        return loadedDataSet;
    }

    @Test
    public void testCompareQuery() throws Exception {
        QueryDataSet queryDataSet = new QueryDataSet(getConnection());
        queryDataSet.addTable("utente", "SELECT * FROM " + "utente");
        Assertion.assertEquals(loadedDataSet, queryDataSet);
    }
}

/*
package unit;

import junit.framework.TestCase;
import model.gestioneUtente.UtenteDAO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class Test_Utente extends TestCase {
    private UtenteDAO utenteDAO;

    @BeforeEach
    protected void setUp() {
        utenteDAO = new UtenteDAO();
        utenteDAO.doDeleteByUsername("frank");
        utenteDAO.doDeleteByUsername("ghost");
        utenteDAO.doSave("frank", "francesco@unisa.it", "Francesco1!", "Francesco", "Ceriello", "Uomo", "1985-12-10", "100000");
    }

    @Test
    public void testRetrieveUtenteEsistente() {
        assertEquals("frank", utenteDAO.doRetrieveByUsername("frank").getUsername());
    }

    @Test
    public void testRetrieveUtenteNonEsistente() {
        assertEquals(null, utenteDAO.doRetrieveByUsername("ghosts"));
    }

    @Test
    public void testRetrieveByEmailUtenteEsistente() {
        assertEquals("frank", utenteDAO.doRetrieveByMail("francesco@unisa.it").getUsername());
    }

    @Test
    public void testRetrieveByEmailUtenteNonEsistente() {
        assertEquals(null, utenteDAO.doRetrieveByMail("ghosts@unisa.it"));
    }

    @Test
    public void testRetrieveByEmailPasswordUtenteEsistente() {
        assertEquals("frank", utenteDAO.doRetrieveByMailPassword("francesco@unisa.it", "Francesco1!").getUsername());
    }

    @Test
    public void testRetrieveByEmailPasswordUtenteNonEsistente() {
        assertEquals(null, utenteDAO.doRetrieveByMailPassword("ghosts@unisa.it", "Ghosts1!"));
    }

    @Test
    public void testUpdateRuoloUtenteEsistente() {
        assertEquals(true, utenteDAO.doUpdateUtente("frank", "001001"));
    }

    @Test
    public void testUpdateRuoloUtenteNonEsistente() {
        assertEquals(false, utenteDAO.doUpdateUtente("ghost", "001001"));
    }

    @AfterEach
    protected void tearDown() {
        utenteDAO.doDeleteByUsername("frank");
    }
}*/
