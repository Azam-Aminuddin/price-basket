package persons.azam_ami.solutions;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persons.azam_ami.implementations.SampleDatastore;

public class Test_SampleDatastore_ReadConf
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    static final String EOL = System.getProperty( "line.separator" );
    
    @Before
    public void setUpStreams() 
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() 
    {
        System.setOut(null);
        System.setErr(null);
    }
    
    @Test
    public void testSucccess()
    {
        SampleDatastore.Instance.readConfig( "conf/store.conf" );
    }

    @Test
    public void testFailed()
    {
        final String filename = "conf/store1.conf";
        
        SampleDatastore.Instance.readConfig( "conf/store1.conf" );
        assertEquals("Could not load file configuration " + filename + "." + EOL, errContent.toString());
    }
}
