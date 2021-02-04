package unit;

import junit.framework.TestCase;
import model.gestioneUtente.Utente;
import model.gestioneUtente.UtenteDAO;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class Test_UtenteDAO extends TestCase {
    private UtenteDAO utenteDAO;
    private JdbcDataSource dataSource;

    @BeforeEach
    protected void setUp() {
        utenteDAO = new UtenteDAO();
    }

    @Test
    public void TC_Accesso1() {
        assertEquals(-1, utenteDAO.doRetrieveByMailPassword(new Utente(), "francy", "Francy")); // LE < 8 or LE > 255
    }

    @AfterEach
    public void tearDown() {
        utenteDAO = null;
    }
}

/*
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

package unit;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.TimeZone;

public class Test_UtenteDAO extends DBTestCase
{
    public Test_UtenteDAO(String name) {
        super(name);
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/movietipsdb?serverTimezone=" + TimeZone.getDefault().getID());
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "MovieTips" );
    }

    protected IDataSet getDataSet() throws Exception
    {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("input.xml"));
    }

    protected DatabaseOperation getSetUpOperation() throws Exception
    {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception
    {
        return DatabaseOperation.DELETE_ALL;
    }

    @Test
    public void testExample() throws Exception
    {
        // Fetch database data after executing your code
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("utente");

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("input.xml"));
        ITable expectedTable = expectedDataSet.getTable("utente");

        // Assert actual database table match expected table
        Assertion.assertEquals(expectedTable, actualTable);
    }
}
*/