package persons.azam_ami.solutions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import persons.azam_ami.implementations.SampleDatastore;

public class Test_ShoppingBasket
{
    @Before
    public void doBefore()
    {
        SampleDatastore.Instance.reset();
    }
    
    @Test
    public void test_addItemExist()
    {
        SampleDatastore.Instance.addItemPrice( "ABC", 0.65 );
        Datastore datastore = SampleDatastore.Instance;
                
        ShoppingBasket basket = new ShoppingBasket();
        assertTrue( "Can add exist item", basket.addItem( datastore, "ABC"));
        Integer count = basket.items.get( "ABC" );
        assertNotNull( "Can add exist item and the item in basket", count );
        assertEquals( "Can add exist item, the item in basket and the count is 1", 1, count.intValue() );
    }

    @Test
    public void test_addItemNotExist()
    {
        SampleDatastore.Instance.addItemPrice( "ABC", 0.65 );
        Datastore datastore = SampleDatastore.Instance;
        
        ShoppingBasket basket = new ShoppingBasket();
        assertFalse( "Failed to add not exist item", basket.addItem( datastore, "ABCD"));
        Integer count = basket.items.get( "ABCD" );
        assertNull( "Failed to add not exist item and the item is not in basket", count );
    }
}
