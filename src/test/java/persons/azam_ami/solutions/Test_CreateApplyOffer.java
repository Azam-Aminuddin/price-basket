package persons.azam_ami.solutions;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Test_CreateApplyOffer
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /*
    @Test
    public void testSuccess()
    {
        Offer offer = new Offer( 
                "offer name",
                "item name",
                1,
                "discounted item name",
                0.1 );
        ApplyOffer applyOffer = new ApplyOffer( offer, 1 ); 
    }

    @Test
    public void testFailed_invalidOffer()
    {
        Offer offer = null; 
        
        exception.expect( AssertionError.class );
        exception.expectMessage( "Associated offer should be valid." );
        ApplyOffer applyOffer = new ApplyOffer( offer, 0 ); 
    }

    @Test
    public void testFailed_invalidOfferCount()
    {
        Offer offer = new Offer( 
                "offer name",
                "item name",
                1,
                "discounted item name",
                0.1 );
        
        exception.expect( AssertionError.class );
        exception.expectMessage( "Apply count should be greater than or equal to 1." );
        ApplyOffer applyOffer = new ApplyOffer( offer, 0 ); 
    }
    */
}
