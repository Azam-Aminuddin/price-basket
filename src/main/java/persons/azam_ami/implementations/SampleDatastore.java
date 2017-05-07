package persons.azam_ami.implementations;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import persons.azam_ami.solutions.Datastore;
import persons.azam_ami.solutions.ErrorMsg;
import persons.azam_ami.solutions.ItemPrice;
import persons.azam_ami.solutions.Offer;
import persons.azam_ami.solutions.OfferDiscount;
import persons.azam_ami.solutions.OfferOtherItemDiscount;
import persons.azam_ami.solutions.SolutionConfig;

public class SampleDatastore implements Datastore
{
    protected Map<String,ItemPrice> prices = new HashMap<String,ItemPrice>();
    protected Map<String,Offer> offers = new HashMap<String,Offer>();
    
    private SampleDatastore()
    {
    }

    /**
     * Reset internal members.
     */
    public void reset()
    {
        prices = new HashMap<String,ItemPrice>();
        offers = new HashMap<String,Offer>();
    }
    
    @Override
    public ItemPrice getItemPrice( final String itemName )
    {
        return prices.get( itemName );
    }

    @Override
    public Map<String,Offer> getOffers()
    {
        return offers;
    }
    
    @Override
    public Offer getOffer( final String offerKey )
    {
        final Offer ans = offers.get( offerKey );
        return ans;
    }
    
    /**
     * Read configuration from {@code filename}.
     * 
     * @param filename the file name configuration.
     * @return {@code true} if the read operation is success, {@code false} otherwise.
     */
    public boolean readConfig( final String filename )
    {
        Properties props = new Properties( );
        
        try
        {
            props.load( new BufferedReader( new InputStreamReader( new FileInputStream(filename), "UTF8")));
        } 
        catch (FileNotFoundException e)
        {
            ErrorMsg.couldNotLoadConfig( filename );
            return false;
        } 
        catch (IOException e)
        {
            ErrorMsg.couldNotLoadConfig( filename );
            return false;
        }

        // Read Currency Configs
        if( !SolutionConfig.Instance.readConfig(props))
        {
            return false;
        }
        
        // Read Items
        String key = "itemCount";
        try
        {
            int itemCount = Integer.parseInt( props.getProperty( key ) );
            for( int i=0; i<itemCount; i++ )
            {
                key = "item." + (i+1);
                final String item = props.getProperty( key );
                if( item!=null )
                {
                    // System.out.println( "--- " + key + " -> " + item );
                    
                    final String[] parts = item.split( "," );
                    if( parts.length!=2 )
                    {
                        ErrorMsg.failedToReadConfigProperty( key );
                        return false;
                    }
                    double price = Double.parseDouble( parts[1] );
                    if( !addItemPrice( parts[0], price ))
                    {
                        ErrorMsg.configFailedToAddItem( parts[0] );
                        return false;
                    }
                }
            }
        }
        catch(NumberFormatException nfe)
        {
            ErrorMsg.failedToReadConfigProperty( key );
            return false;
        }
        
        try
        {
            key = "offerCount";
            int offerCount = Integer.parseInt( props.getProperty( key ) );
            for( int i=0; i<offerCount; i++ )
            {
                key = "offer." + (i+1);
                final String item = props.getProperty( key );
                if( item!=null )
                {
                    // System.out.println( "--- " + key + " -> " + item );
                    final String[] parts = item.split( "," );
                    if( "discount".equals( parts[0] )) 
                    {
                        if( parts.length!=4)
                        {
                            ErrorMsg.failedToReadConfigProperty( key );
                            return false;
                        }
                        
                        final String offer_name = parts[1];
                        final String offer_itemName = parts[2];
                        double discount = Double.parseDouble( parts[3] );
                        
                        Offer offer = new OfferDiscount( offer_name, offer_itemName, discount );
                        addOffer( offer );
                    }
                    else if( "otherItemDiscount".equals( parts[0] )) 
                    {
                        if( parts.length!=6)
                        {
                            ErrorMsg.failedToReadConfigProperty( key );
                            return false;
                        }
                        
                        final String offer_name = parts[1];
                        final String offer_itemName = parts[2];
                        int count = Integer.parseInt( parts[3] );
                        final String offer_discountedItemName = parts[4];
                        double discount = Double.parseDouble( parts[5] );
                        
                        Offer offer = new OfferOtherItemDiscount( offer_name, offer_itemName, count, offer_discountedItemName, discount );
                        addOffer( offer );
                    }
                    else
                    {
                        ErrorMsg.failedToReadConfigProperty( key );
                        return false;
                    }
                }
            }
        }
        catch(NumberFormatException nfe)
        {
            ErrorMsg.failedToReadConfigProperty( key );
            return false;
        }
        
        
        return true;
    }
    
    /**
     * Add an item to price list.
     * 
     * @param name the name of item.
     * @param price the price of item.
     * @return {@code true} if success, {@code false} otherwise.
     */
    public boolean addItemPrice( final String name, final double price )
    {
        if( prices.containsKey( name )) return false;
        prices.put( name, new ItemPrice( name, price ) );
        return true;
    }

    public Offer addOffer( final Offer offer )
    {
        final String offerKey = offer.getKey();
        if( offers.containsKey( offerKey ))
        {
            return null;
        }
        offers.put( offerKey, offer );
        return offer;
    }
    
    public static SampleDatastore Instance = new SampleDatastore();
}
