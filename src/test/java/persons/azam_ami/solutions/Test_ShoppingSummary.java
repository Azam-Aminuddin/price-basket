package persons.azam_ami.solutions;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persons.azam_ami.implementations.SampleDatastore;

public class Test_ShoppingSummary
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
    public void test_noOffer()
    {
        ShoppingSummary summary = new ShoppingSummary();
        summary.subTotal = 3.1;
        summary.total = 3.1;
        summary.displayTo( System.out );
        
        assertEquals("Subtotal: £3.10 (No offers available)\r\n" +
"Total: £3.10\r\n", outContent.toString());
    }

    @Test
    public void test_oneOfferApplied()
    {
        SampleDatastore.Instance.addItemPrice( "Soup", 0.65 );
        SampleDatastore.Instance.addItemPrice( "Bread", 0.80 );
        SampleDatastore.Instance.addItemPrice( "Milk", 1.30 );
        SampleDatastore.Instance.addItemPrice( "Apples", 1.00 );
        
        Offer offer = SampleDatastore.Instance.addOffer( new OfferDiscount( "10%", "Apples", 0.1 ) );
        SampleDatastore.Instance.addOffer( new OfferOtherItemDiscount( "Buy2GetHalf", "Soup", 2, "Bread", 0.5 ) );
        
        ShoppingSummary summary = new ShoppingSummary();
        summary.subTotal = 3.1;
        ApplyOffer applyOffer = new ApplyOffer( offer, 1 );
        applyOffer.totalApplyDiscount = -0.1; 
        summary.applyOffers.add( applyOffer);
        summary.total = 3.0;
        
        summary.displayTo( System.out );
        
        assertEquals("Subtotal: £3.10\r\n" +
"Apples 10% off: 10p\r\n" +
"Total: £3.00\r\n", outContent.toString());
    }
    
    @Test
    public void test_twoOffersApplied()
    {
        SampleDatastore.Instance.addItemPrice( "Soup", 0.65 );
        SampleDatastore.Instance.addItemPrice( "Bread", 0.80 );
        SampleDatastore.Instance.addItemPrice( "Milk", 1.30 );
        SampleDatastore.Instance.addItemPrice( "Apples", 1.00 );
        
        Offer offer1 = SampleDatastore.Instance.addOffer( new OfferDiscount( "10%", "Apples", 0.1 ) );
        Offer offer2 = SampleDatastore.Instance.addOffer( new OfferOtherItemDiscount( "Buy2GetHalf", "Soup", 2, "Bread", 0.5 ) );
        
        ShoppingSummary summary = new ShoppingSummary();
        summary.subTotal = 4.4;
        
        // First offer
        ApplyOffer applyOffer = new ApplyOffer( offer1, 1 );
        applyOffer.totalApplyDiscount = -0.1; 
        summary.applyOffers.add( applyOffer);
        
        // Second offer
        applyOffer = new ApplyOffer( offer2, 1 );
        applyOffer.totalApplyDiscount = -0.4; 
        summary.applyOffers.add( applyOffer);
        
        summary.total = 3.9;
        
        summary.displayTo( System.out );
        
        assertEquals("Subtotal: £4.40\r\n" +
"Apples 10% off: 10p\r\n" +
"Bread Buy2GetHalf off: 40p\r\n" +
"Total: £3.90\r\n", outContent.toString());
    }
}
