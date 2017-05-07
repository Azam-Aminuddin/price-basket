package persons.azam_ami.solutions;

import persons.azam_ami.implementations.SampleCheckout;
import persons.azam_ami.implementations.SampleDatastore;

public class Main
{
    private static String getArg( final String arg, final String startsWith )
    {
        if( arg.startsWith( startsWith ))
        {
            return arg.substring( startsWith.length() );
        }
        return null;
    }
    
    public static void main(final String[] args )
    {
        if( args.length==0 )
        {
            // No argument specified.
            return;
        }
        
        // Default config file.
        String configFile = "conf/store.conf";
        boolean ignoreArgConf = false;
        
        // Check first argument, whether is a "conf=".
        String arg_value = getArg( args[0], "conf=" );
        if( arg_value!=null ) 
        {
            configFile = arg_value;
            if( args.length==1 )
            {
                // This is the only argument.
                return;
            }
            ignoreArgConf = true;
        }
        
        if( !SampleDatastore.Instance.readConfig( configFile ))
        {
            return;
        }
        
        Datastore datastore = SampleDatastore.Instance;
        ShoppingBasket basket = new ShoppingBasket();
        for( String arg : args )
        {
            if( ignoreArgConf )
            {
                // This is first argument which is "conf="
                ignoreArgConf = false;
            }
            else
            {
                if( !basket.addItem( datastore, arg ))
                {
                    ErrorMsg.checkoutFailedToAddItem( arg );
                    return;
                }
            }
        }
        
        Checkout checkout = SampleCheckout.Instance;
        ShoppingSummary summary = checkout.calculatePrices( datastore, basket );
        summary.displayTo( System.out );
    }
}
