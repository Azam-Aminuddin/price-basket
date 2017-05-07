package persons.azam_ami.solutions;

import java.text.DecimalFormat;
import java.util.Properties;

/**
 * Configurable parts of thie solution.
 * 
 * @author azam_ami@yahoo.com
 */
public class SolutionConfig
{
    protected String currencyPrefix;
    protected String centsPrefix;
    protected String centsSuffix;
    protected DecimalFormat moneyFormat; 
    protected DecimalFormat centsFormat;
    
    // Text string for localization.
    protected String text_Subtotal;
    protected String text_Total;
    protected String text_NoOffersAvailable;
    protected String text_off;
    
    private SolutionConfig()
    {
        setDefault();
    }
    
    /**
     * Set configuration values to default.
     */
    public void setDefault()
    {
        currencyPrefix = "£";
        centsPrefix = "";
        centsSuffix = "p";
        moneyFormat = new DecimalFormat("#.00"); 
        centsFormat = new DecimalFormat("#"); 
        text_Subtotal = "Subtotal";
        text_Total = "Total";
        text_NoOffersAvailable = "(No offers available)";
        text_off = "off";
    }
    
    /**
     * Read configuration from properties.
     * 
     * @param props
     * @return {@code true} if success, otherwise {@code false}
     */
    public boolean readConfig( final Properties props )
    {
        currencyPrefix = props.getProperty( "currencyPrefix", currencyPrefix );
        centsPrefix = props.getProperty( "centsPrefix", centsPrefix );
        centsSuffix = props.getProperty( "centsSuffix", centsSuffix );
        text_Subtotal = props.getProperty( "text.Subtotal", text_Subtotal );
        text_Total = props.getProperty( "text.Total", text_Total );
        text_NoOffersAvailable = props.getProperty( "text.NoOffersAvailable", text_NoOffersAvailable );
        text_off = props.getProperty( "text.off", text_off );

        // Read Currency Formats
        String key = "moneyFormat";
        String value;
        try
        {
            value = props.getProperty( key );
            if( value!=null )
            {
                moneyFormat = new DecimalFormat( value );
            }
            
            key = "centsFormat";
            value = props.getProperty( key );
            if( value!=null )
            {
                centsFormat = new DecimalFormat( value );
            }
        }
        catch( Exception e )
        {
            ErrorMsg.failedToReadConfigProperty( key );
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    /**
     * Get display string of money as {@code amount}. Return cents part only if the {@code amount} less than 1.   
     * 
     * @param amount the amount.
     * @return the display string.
     */
    protected String getMoneyString( double amount )
    {
        if( amount>=1 )
        {
            String s = moneyFormat.format( amount );
            return currencyPrefix + s;
        }
        else
        {
            String s = centsFormat.format( amount*100 );
            return s + centsSuffix;
        }
    }
    
    public static SolutionConfig Instance = new SolutionConfig();
}
