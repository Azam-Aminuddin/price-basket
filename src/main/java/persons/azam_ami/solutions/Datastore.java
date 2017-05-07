package persons.azam_ami.solutions;

import java.util.Map;

/**
 * Definition of datastore. We may implement a datastore with database or in-memory ones. 
 *  
 * @author azam_ami@yahoo.com
 */
public interface Datastore
{
    public ItemPrice getItemPrice( final String itemName );
    public Map<String,Offer> getOffers();
    public Offer getOffer( final String offerName );
}
