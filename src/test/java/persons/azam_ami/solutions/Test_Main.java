package persons.azam_ami.solutions;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persons.azam_ami.implementations.SampleDatastore;

public class Test_Main
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    
	
    @Before
    public void setUpStreams() 
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        
        SolutionConfig.Instance.setDefault();
        SampleDatastore.Instance.reset();
    }

    @After
    public void cleanUpStreams() 
    {
        System.setOut(null);
        System.setErr(null);
    }
    
    @Test
    public void test_oneOfferApplied()
    {
        final String[] args = new String[] {
            "Apples",
            "Milk",
            "Bread",
            };
        
        Main.main( args );
        assertEquals("Subtotal: £3.10\r\n" +
"Apples 10% off: 10p\r\n" +
"Total: £3.00\r\n", outContent.toString());
    }

}
