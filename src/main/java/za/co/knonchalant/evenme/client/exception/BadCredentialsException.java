package za.co.knonchalant.evenme.client.exception;

/**
 * <p>Title: BadCredentialsException</p>
 * <p/>
 * <p>Description: Thrown when the username / password combination were either invalid (could not authenticate), or did not have the correct authorization.</p>
 * <p/>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p/>
 * <p>Company: Discovery</p>
 *
 * @author Evan Knowles
 * @version 1.0
 */
public class BadCredentialsException extends RuntimeException
{
  /**
   * Constructor
   * @param responseCode the response code
   **/
  public BadCredentialsException(int responseCode)
  {
    super("Bad credentials: " + responseCode);
  }
}
