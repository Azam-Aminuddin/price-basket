package persons.azam_ami.solutions;

/**
 * Represent an item price.
 * 
 * @author azam_ami@yahoo.com
 */
public class ItemPrice
{
    protected String name;
    protected double price;
    
    public ItemPrice( final String name, final double price )
    {
        if( price<0 )
        {
            throw new AssertionError( "Price should be greater than or equal to 0." );
        }
        this.name = name;
        this.price = price;
    }
    
    /**
     * Getter of price.
     * @return
     */
    public double getPrice()
    {
        return price;
    }
}
