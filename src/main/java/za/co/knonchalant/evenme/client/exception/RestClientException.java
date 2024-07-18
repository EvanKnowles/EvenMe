package za.co.knonchalant.evenme.client.exception;

/**
 * <p>Title: RestClientException</p>
 * <p/>
 * <p>Description: Thrown when the server returns a 400 code, with the response as the message.</p>
 * <p/>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p/>
 * <p>Company: Discovery</p>
 *
 * @author Evan Knowles
 * @version 1.0
 */
public class RestClientException extends RestException
{
  /**
   * Constructor
   * @param message the message
   **/
  public RestClientException(String message)
  {
    super(message);
  }
}