package persons.azam_ami.solutions;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represent a shopping basket. Containing {@code items} and applied offers.  
 *  
 * @author azam_ami@yahoo.com
 */
public class ShoppingBasket
{
    protected Map<String,Integer> items = new LinkedHashMap<String,Integer>();
    
    public Map<String,Integer> getItems()
    {
        return items;
    }
    
    /**
     * Add an {@code item} to {@code ShoppingBasket}.
     *  
     * @param item
     */
    public boolean addItem( final Datastore datastore, final String item )
    {
        ItemPrice itemPrice = datastore.getItemPrice( item );
        if( itemPrice==null )
        {
            return false;
        }
        
        if( items.containsKey( item ))
        {
            int value = items.get( item );
            items.put( item, value+1 );
        }
        else
        {
            items.put( item, 1 );
        }
        return true;
    }
}
