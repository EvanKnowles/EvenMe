package za.co.knonchalant.evenme.client.exception;

/**
 * <p>Title: RestException</p>
 * <p/>
 * <p>Description: Base REST exception.</p>
 * <p/>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p/>
 * <p>Company: Discovery</p>
 *
 * @author Evan Knowles
 * @version 1.0
 */
public class RestException extends RuntimeException
{
  /**
   * Constructor
   * @param message the message
   **/
  public RestException(String message)
  {
    super(message);
  }

  /**
   * Constructor
   * @param ex the exception
   **/
  public RestException(Exception ex)
  {
    super(ex);
  }
}
