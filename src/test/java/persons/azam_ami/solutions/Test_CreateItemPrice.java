package persons.azam_ami.solutions;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Test_CreateItemPrice
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @Test
    public void testSuccess()
    {
        ItemPrice ip = new ItemPrice( "AAA", 1 );
    }

    @Test
    public void testFailed()
    {
        exception.expect( AssertionError.class );
        exception.expectMessage( "Price should be greater than or equal to 0." );
        ItemPrice ip = new ItemPrice( "AAA", -1 );
    }
}
