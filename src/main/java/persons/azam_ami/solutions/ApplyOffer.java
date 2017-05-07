package persons.azam_ami.solutions;

/**
 * Represent an application of an offer.
 * 
 * @author azam_ami@yahoo.com
 */
public class ApplyOffer
{
    final Offer offer;
    protected String itemName;
    protected int applyCount;
    
    protected transient double totalApplyDiscount; 
    
    public ApplyOffer(
            final Offer offer,
            final int applyCount )
    {
        if( offer==null )
        {
            throw new AssertionError( "Associated offer should be valid." );
        }
        if( applyCount<=0 )
        {
            throw new AssertionError( "Apply count should be greater than or equal to 1." );
        }
        
        this.offer = offer;
        this.itemName = offer.getDiscountedItemName();
        this.applyCount = applyCount;
    }

    public double getTotalApplyDiscount()
    {
        return totalApplyDiscount;
    }
}
