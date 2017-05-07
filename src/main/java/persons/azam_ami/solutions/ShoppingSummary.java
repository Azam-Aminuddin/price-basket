package persons.azam_ami.solutions;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a Shopping Summary.
 * 
 * @author azam_ami@yahoo.com
 */
public class ShoppingSummary
{
    public double subTotal;
    public List<ApplyOffer> applyOffers = new ArrayList<ApplyOffer>();
    public double total;
    
    public ShoppingSummary()
    {
    }
    
    /**
     * Get display string of money as {@code amount}. Return cents part only if the {@code amount} less than 1.   
     * 
     * @param amount the amount.
     * @return the display string.
     */
    private String getMoneyString( double amount )
    {
        return SolutionConfig.Instance.getMoneyString( amount );
    }
    
    /**
     * Display contents to a PrintSetram.
     * 
     * @param out the printStream.
     */
    public void displayTo( PrintStream out )
    {
        if( applyOffers.size()==0 )
        {
            out.println( SolutionConfig.Instance.text_Subtotal + ": " + getMoneyString( subTotal ) 
                + " " + SolutionConfig.Instance.text_NoOffersAvailable 
                );
        }
        else
        {
            out.println( SolutionConfig.Instance.text_Subtotal + ": " + getMoneyString( subTotal ) );
            for( ApplyOffer applyOffer : applyOffers )
            {
                out.println( applyOffer.itemName + " " + applyOffer.offer.name 
                        + " " + SolutionConfig.Instance.text_off + ": " + getMoneyString(-applyOffer.totalApplyDiscount ) );
            }
        }
        
        out.println( SolutionConfig.Instance.text_Total + ": " + getMoneyString( total ) );
    }
}
