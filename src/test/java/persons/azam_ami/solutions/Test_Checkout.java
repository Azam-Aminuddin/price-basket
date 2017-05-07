package persons.azam_ami.solutions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import persons.azam_ami.implementations.SampleCheckout;
import persons.azam_ami.implementations.SampleDatastore;

public class Test_Checkout
{
    @Before
    public void doBefore()
    {
        SampleDatastore.Instance.reset();
    }
    
    @Test
    public void test_noOffer()
    {
        SampleDatastore.Instance.addItemPrice( "Soup", 0.65 );
        SampleDatastore.Instance.addItemPrice( "Bread", 0.80 );
        SampleDatastore.Instance.addItemPrice( "Milk", 1.30 );
        SampleDatastore.Instance.addItemPrice( "Apples", 1.00 );
        
        // SampleDatastore.Instance.addOffer( new Offer( "10%", "Apples", 1, "Apples", 0.1 ) );
        SampleDatastore.Instance.addOffer( new OfferOtherItemDiscount( "Buy2GetHalf", "Soup", 2, "Bread", 0.5 ) );
        Datastore datastore = SampleDatastore.Instance;
        
        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem( datastore, "Apples");
        basket.addItem( datastore, "Milk");
        basket.addItem( datastore, "Bread");
        
        Checkout checkout = SampleCheckout.Instance;
        ShoppingSummary summary = checkout.calculatePrices( datastore, basket );
        // summary.displayTo( System.out );
        assertEquals( "Subtotal is match", 3.10, summary.subTotal, 0.01 );
        assertEquals( "Total is match", 3.10, summary.total, 0.01 );
    }

    @Test
    public void test_oneOfferApplied()
    {
        SampleDatastore.Instance.addItemPrice( "Soup", 0.65 );
        SampleDatastore.Instance.addItemPrice( "Bread", 0.80 );
        SampleDatastore.Instance.addItemPrice( "Milk", 1.30 );
        SampleDatastore.Instance.addItemPrice( "Apples", 1.00 );
        
        SampleDatastore.Instance.addOffer( new OfferDiscount( "10%", "Apples", 0.1 ) );
        SampleDatastore.Instance.addOffer( new OfferOtherItemDiscount( "Buy2GetHalf", "Soup", 2, "Bread", 0.5 ) );
        Datastore datastore = SampleDatastore.Instance;
        
        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem( datastore, "Apples");
        basket.addItem( datastore, "Milk");
        basket.addItem( datastore, "Bread");
        
        Checkout checkout = SampleCheckout.Instance;
        ShoppingSummary summary = checkout.calculatePrices( datastore, basket );
        // summary.displayTo( System.out );
        assertEquals( "Subtotal is match", 3.10, summary.subTotal, 0.001 );
        assertEquals( "Offer count applied is match", 1, summary.applyOffers.size() );
        assertEquals( "Calculated discount is match", -0.1, summary.applyOffers.get( 0 ).totalApplyDiscount, 0.001 );
        assertEquals( "Total is match", 3.00, summary.total, 0.001 );
    }
    
    @Test
    public void test_twoOffersApplied()
    {
        SampleDatastore.Instance.addItemPrice( "Soup", 0.65 );
        SampleDatastore.Instance.addItemPrice( "Bread", 0.80 );
        SampleDatastore.Instance.addItemPrice( "Milk", 1.30 );
        SampleDatastore.Instance.addItemPrice( "Apples", 1.00 );
        
        SampleDatastore.Instance.addOffer( new OfferDiscount( "10%", "Apples", 0.1 ) );
        SampleDatastore.Instance.addOffer( new OfferOtherItemDiscount( "Buy2GetHalf", "Soup", 2, "Bread", 0.5 ) );
        Datastore datastore = SampleDatastore.Instance;
        
        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem( datastore, "Apples");
        basket.addItem( datastore, "Milk");
        basket.addItem( datastore, "Bread");
        basket.addItem( datastore, "Soup");
        basket.addItem( datastore, "Soup");
        
        Checkout checkout = SampleCheckout.Instance;
        ShoppingSummary summary = checkout.calculatePrices( datastore, basket );
        // summary.displayTo( System.out );
        assertEquals( "Subtotal is match", 4.40, summary.subTotal, 0.001 );
        assertEquals( "Offer count applied is match", 2, summary.applyOffers.size() );
        assertEquals( "Calculated discount is match", -0.1, summary.applyOffers.get( 0 ).totalApplyDiscount, 0.01 );
        assertEquals( "Calculated discount is match", -0.4, summary.applyOffers.get( 1 ).totalApplyDiscount, 0.01 );
        assertEquals( "Total is match", 3.90, summary.total, 0.01 );
    }

    /**
     * Extend test case {@code test_twoOffersApplied } but the second offer is not qualified due to item count is not enough. 
     */
    @Test
    public void test_oneOfferApplied_oneOfferNotQualifiedDueToItemCount()
    {
        SampleDatastore.Instance.addItemPrice( "Soup", 0.65 );
        SampleDatastore.Instance.addItemPrice( "Bread", 0.80 );
        SampleDatastore.Instance.addItemPrice( "Milk", 1.30 );
        SampleDatastore.Instance.addItemPrice( "Apples", 1.00 );
        
        SampleDatastore.Instance.addOffer( new OfferDiscount( "10%", "Apples", 0.1 ) );
        SampleDatastore.Instance.addOffer( new OfferOtherItemDiscount( "Buy2GetHalf", "Soup", 2, "Bread", 0.5 ) );
        Datastore datastore = SampleDatastore.Instance;
        
        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem( datastore, "Apples");
        basket.addItem( datastore, "Milk");
        basket.addItem( datastore, "Bread");
        // Comment out seconnd "Bread"
        // basket.addItem( datastote, "Soup");
        basket.addItem( datastore, "Soup");
        
        Checkout checkout = SampleCheckout.Instance;
        ShoppingSummary summary = checkout.calculatePrices( datastore, basket );
        // summary.displayTo( System.out );
        assertEquals( "Subtotal is match", 3.75, summary.subTotal, 0.001 );
        assertEquals( "Offer count applied is match", 1, summary.applyOffers.size() );
        assertEquals( "Calculated discount is match", -0.1, summary.applyOffers.get( 0 ).totalApplyDiscount, 0.01 );
        assertEquals( "Total is match", 3.65, summary.total, 0.01 );
    }
    
    /**
     * Test Offer Buy-One-Get-One-Free.
     */
    @Test
    public void test_buyOneGetOneFree()
    {
        SampleDatastore.Instance.addItemPrice( "Soup", 0.65 );
        SampleDatastore.Instance.addItemPrice( "Bread", 0.80 );
        SampleDatastore.Instance.addItemPrice( "Milk", 1.30 );
        SampleDatastore.Instance.addItemPrice( "Apples", 1.00 );
        
        SampleDatastore.Instance.addOffer( new OfferOtherItemDiscount( "BOGOF", "Apples", 1, "Apples", 1 ) );
        Datastore datastore = SampleDatastore.Instance;
        
        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem( datastore, "Apples");
        basket.addItem( datastore, "Apples");
        basket.addItem( datastore, "Milk");
        basket.addItem( datastore, "Bread");
        
        Checkout checkout = SampleCheckout.Instance;
        ShoppingSummary summary = checkout.calculatePrices( datastore, basket );
        // summary.displayTo( System.out );
        assertEquals( "Subtotal is match", 4.10, summary.subTotal, 0.001 );
        assertEquals( "Offer count applied is match", 1, summary.applyOffers.size() );
        assertEquals( "Calculated discount is match", -1.0, summary.applyOffers.get( 0 ).totalApplyDiscount, 0.001 );
        assertEquals( "Total is match", 3.10, summary.total, 0.001 );
    }
    
    @Test
    public void test_buyOneGetOneFree_missingOneFree()
    {
        SampleDatastore.Instance.addItemPrice( "Soup", 0.65 );
        SampleDatastore.Instance.addItemPrice( "Bread", 0.80 );
        SampleDatastore.Instance.addItemPrice( "Milk", 1.30 );
        SampleDatastore.Instance.addItemPrice( "Apples", 1.00 );
        
        SampleDatastore.Instance.addOffer( new OfferOtherItemDiscount( "BOGOF", "Apples", 1, "Apples", 1 ) );
        Datastore datastore = SampleDatastore.Instance;
        
        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem( datastore, "Apples");
        // basket.addItem( datastore, "Apples");
        basket.addItem( datastore, "Milk");
        basket.addItem( datastore, "Bread");
        
        Checkout checkout = SampleCheckout.Instance;
        ShoppingSummary summary = checkout.calculatePrices( datastore, basket );
        // summary.displayTo( System.out );
        assertEquals( "Subtotal is match", 3.10, summary.subTotal, 0.001 );
        assertEquals( "Offer count applied is match", 0, summary.applyOffers.size() );
        assertEquals( "Total is match", 3.10, summary.total, 0.001 );
    }
    
    /**
     * Test Offer Buy-Two-Get-One-Free.
     */
    @Test
    public void test_buyTwoGetOneFree()
    {
        SampleDatastore.Instance.addItemPrice( "Soup", 0.65 );
        SampleDatastore.Instance.addItemPrice( "Bread", 0.80 );
        SampleDatastore.Instance.addItemPrice( "Milk", 1.30 );
        SampleDatastore.Instance.addItemPrice( "Apples", 1.00 );
        
        SampleDatastore.Instance.addOffer( new OfferOtherItemDiscount( "BTGOF", "Apples", 2, "Apples", 1 ) );
        Datastore datastore = SampleDatastore.Instance;
        
        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem( datastore, "Apples");
        basket.addItem( datastore, "Apples");
        basket.addItem( datastore, "Apples");
        basket.addItem( datastore, "Milk");
        basket.addItem( datastore, "Bread");
        
        Checkout checkout = SampleCheckout.Instance;
        ShoppingSummary summary = checkout.calculatePrices( datastore, basket );
        // summary.displayTo( System.out );
        assertEquals( "Subtotal is match", 5.10, summary.subTotal, 0.001 );
        assertEquals( "Offer count applied is match", 1, summary.applyOffers.size() );
        assertEquals( "Calculated discount is match", -1., summary.applyOffers.get( 0 ).totalApplyDiscount, 0.001 );
        assertEquals( "Total is match", 4.10, summary.total, 0.001 );
    }
}
