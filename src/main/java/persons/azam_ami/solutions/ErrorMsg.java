package persons.azam_ami.solutions;

/**
 * Module to handle all error messages. For this code challenge, display to the console. 
 *  
 * @author azam_
 */
public class ErrorMsg
{
    public static void couldNotLoadConfig( final String filename )
    {
        System.err.println( "Could not load file configuration " + filename + "." );
    }

    public static void failedToReadConfigProperty( final String key )
    {
        System.err.println( "Failed to read property " + key + "." );
    }

    public static void configFailedToAddItem( final String name )
    {
        System.err.println( "Failed to add item " + name + ". Most cases are duplicate item issue." );
    }

    public static void checkoutFailedToAddItem( final String name )
    {
        System.err.println( "Could not find item " + name + " in datastore." );
    }
}
