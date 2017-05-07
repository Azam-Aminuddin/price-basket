package persons.azam_ami.solutions;

/**
 * Definition of checkout. We may implement some checkouts with difference logic. 
 *  
 * @author azam_ami@yahoo.com
 */
public interface Checkout
{
    public ShoppingSummary calculatePrices(
            final Datastore datastore,
            ShoppingBasket shoppingBasket );
}
