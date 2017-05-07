package persons.azam_ami.solutions;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Test_CreateOffer
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @Test
    public void testSuccess()
    {
        Offer offer_1 = new OfferDiscount( "offer name", "item name", 0.1 );
        Offer offer_2 = new OfferOtherItemDiscount( "offer name", "item name", 2, "discounted item name", 0.1 );
        
    }

    /*
    @Test
    public void testSuccess_DiscountedFree()
    {
        Offer offer = new Offer( 
                "offer name",
                "item name",
                1,
                "discounted item name",
                1 );
    }
    
    @Test 
    public void testFailed_invalidCount()
    {
        exception.expect( AssertionError.class );
        exception.expectMessage( "Item count should be greater than or equal to 1." );
        Offer offer = new Offer( 
                "offer name",
                "item name",
                0,
                "discounted item name",
                0.1 );
    }

    @Test
    public void testFailed_invalidDiscount()
    {
        exception.expect( AssertionError.class );
        exception.expectMessage( "Discount should between 0 and 1." );
        Offer offer = new Offer( 
                "offer name",
                "item name",
                1,
                "discounted item name",
                1.01 );
        
        exception.expect( AssertionError.class );
        exception.expectMessage( "Discount should between 0 and 1." );
        offer = new Offer( 
                "offer name",
                "item name",
                1,
                "discounted item name",
                -0.01 );
    }

    @Test
    public void testFailed_invalidDiscount_LessThanZero()
    {
        exception.expect( AssertionError.class );
        exception.expectMessage( "Discount should between 0 and 1." );
        Offer offer = new Offer( 
                "offer name",
                "item name",
                1,
                "discounted item name",
                -0.01 );
    }
    */
}
