package persons.azam_ami.solutions;

/**
 * Represent an offer in the store.
 * 
 * @author azam_ami@yahoo.com
 */
public abstract class Offer
{
    // Name of the offer.
    protected String name;
    
    // Which item have to bought to qualify the offer.
    protected String itemName;
    
    public Offer( 
            final String name,
            final String itemName )
    {
        this.name = name;
        this.itemName = itemName;
    }
    
    
    /**
     * Calculate and return key of this offer in datastore.
     * @return
     */
    public String getKey()
    {
        final String ans = itemName + "-" + name;
        return ans;
    }
    
    public abstract String getDiscountedItemName();
    
    public abstract ApplyOffer apply(
            final Datastore datastore,
            final ShoppingBasket shoppingBasket );
}
