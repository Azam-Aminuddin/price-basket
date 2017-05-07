package persons.azam_ami.solutions;

/**
 * Represent an offer from type of Buy Some Item and Get Other Discounted Item.
 * 
 * @author azam_ami@yahoo.com
 */
public class OfferOtherItemDiscount extends Offer
{
    // Minimum item to qualify offer.
    protected int itemCount;
    
    // To which item the discount apply.
    protected String discountedItemName;

    // How much the discount.
    protected double discountedDiscount;
    
    public OfferOtherItemDiscount( 
            final String name,
            final String itemName,
            final int itemCount,
            final String discountedItemName,
            final double discountedDiscount )
    {
        super( name, itemName );
        
        if( itemCount<=0 )
        {
            throw new AssertionError( "Item count should be greater than or equal to 1." );
        }
        if( discountedDiscount>1. || discountedDiscount<0. )
        {
            throw new AssertionError( "Discount should between 0 and 1." );
        }
        
        this.itemCount = itemCount;
        this.discountedItemName = discountedItemName;
        this.discountedDiscount = discountedDiscount; 
    }
    
    @Override
    public String getDiscountedItemName()
    {
        return discountedItemName;
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
        
        if( inBasket<itemCount )
        {
            return null;
        }
        Integer discountedInBasket =  shoppingBasket.getItems().get( discountedItemName );
        if( discountedInBasket==null )
        {
            return null;
        }
        
        ItemPrice itemPrice = datastore.getItemPrice( discountedItemName );
        if( itemName.equals( discountedItemName ))
        {
            int applyCount = Integer.min( inBasket / (itemCount+1), discountedInBasket );
            if( applyCount>0 )
            {
                ApplyOffer applyOffer = new ApplyOffer( this, applyCount );
                applyOffer.itemName = discountedItemName;
                applyOffer.totalApplyDiscount = - itemPrice.price * applyCount * discountedDiscount; 
                return applyOffer;
            }
            else
            {
                return null;
            }
        }
        else
        {
            int applyCount = Integer.min( inBasket / itemCount, discountedInBasket );
            ApplyOffer applyOffer = new ApplyOffer( this, applyCount );
            applyOffer.totalApplyDiscount = - itemPrice.price * applyCount * discountedDiscount; 
            return applyOffer;
        }
    }
    
}
