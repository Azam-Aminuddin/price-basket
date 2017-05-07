package persons.azam_ami.implementations;

import java.util.Map;

import persons.azam_ami.solutions.ApplyOffer;
import persons.azam_ami.solutions.Checkout;
import persons.azam_ami.solutions.Datastore;
import persons.azam_ami.solutions.ItemPrice;
import persons.azam_ami.solutions.Offer;
import persons.azam_ami.solutions.ShoppingBasket;
import persons.azam_ami.solutions.ShoppingSummary;

public class SampleCheckout implements Checkout
{
    private SampleCheckout()
    {
        
    }
    
    @Override
    public ShoppingSummary calculatePrices(
            final Datastore datastore,
            ShoppingBasket shoppingBasket )
    {
        ShoppingSummary ans = new ShoppingSummary(); 
        
        for( Offer offer : datastore.getOffers().values() )
        {
            ApplyOffer applyOffer = offer.apply( datastore, shoppingBasket );
            if( applyOffer!=null )
            {
                ans.applyOffers.add( applyOffer );
            }
        }
        
        double subTotal = 0.;
        for( Map.Entry<String,Integer> entry : shoppingBasket.getItems().entrySet() )
        {
            final String key = entry.getKey();
            final Integer value = entry.getValue();
            
            ItemPrice price = datastore.getItemPrice( key );
            subTotal += price.getPrice() * value.doubleValue();
        }
        
        ans.subTotal = subTotal;
                
        for( ApplyOffer applyOffer : ans.applyOffers )
        {
            subTotal += applyOffer.getTotalApplyDiscount();
        }
        // System.out.println( "Total: " + getMoneyString( subTotal ) );
        ans.total = subTotal;
        return ans;
    }
    
    public static SampleCheckout Instance = new SampleCheckout();
}
