package za.co.knonchalant.evenme.client.exception;

/**
 * <p>Title: RestServerException</p>
 * <p/>
 * <p>Description: Thrown when the server returns a 500 code, with the response as the message.</p>
 * <p/>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p/>
 * <p>Company: Discovery</p>
 *
 * @author Evan Knowles
 * @version 1.0
 */
public class RestServerException extends RestException
{
  /**
   * Constructor
   * @param message the message
   **/
  public RestServerException(String message)
  {
    super(message);
  }
}
