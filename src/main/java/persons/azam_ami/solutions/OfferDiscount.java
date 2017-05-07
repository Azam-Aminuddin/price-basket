package persons.azam_ami.solutions;

/**
 * Represent an offer from type of Discount.
 * 
 * @author azam_ami@yahoo.com
 */
public class OfferDiscount extends Offer
{
    // How much the the discount.
    protected double discount;
    
    public OfferDiscount( 
            final String name,
            final String itemName,
            final double discount )
    {
        super( name, itemName );
        
        if( discount>1. || discount<0. )
        {
            throw new AssertionError( "Discount should between 0 and 1." );
        }
        this.discount = discount;
    }
    
    @Override
    public String getDiscountedItemName()
    {
        return itemName;
    }
    
    /**
     * Apply this offer.
     */
    @Override
    public ApplyOffer apply(
            final Datastore datastore,
            final ShoppingBasket shoppingBasket )
    {
        Integer inBasket =  shoppingBasket.getItems().get( itemName );
        if( inBasket==null )
        {
            return null;
        }
        ItemPrice itemPrice = datastore.getItemPrice( itemName );
        ApplyOffer applyOffer = new ApplyOffer( this, inBasket );
        applyOffer.totalApplyDiscount = -itemPrice.price * inBasket * discount; 
        return applyOffer;
    }
}
