package persons.azam_ami.solutions;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test_StoreConfig
{
    @Test
    public void testMoneyString()
    {
        SolutionConfig.Instance.currencyPrefix = "Xy";
        SolutionConfig.Instance.centsSuffix = "z";
        
        assertEquals( "Xy1.00", SolutionConfig.Instance.getMoneyString( 1 ));
        assertEquals( "Xy1.01", SolutionConfig.Instance.getMoneyString( 1.01 ));
        assertEquals( "99z", SolutionConfig.Instance.getMoneyString( 0.99 ));
        assertEquals( "1z", SolutionConfig.Instance.getMoneyString( 0.01 ));
    }

}
