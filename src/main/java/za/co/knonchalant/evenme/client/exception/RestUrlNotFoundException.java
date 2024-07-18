package za.co.knonchalant.evenme.client.exception;

/**
 * <p>Title: RestUrlNotFoundException</p>
 * <p/>
 * <p>Description: Thrown when the server returns a 404</p>
 * <p/>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p/>
 * <p>Company: Discovery</p>
 *
 * @author Evan Knowles
 * @version 1.0
 */
public class RestUrlNotFoundException extends RestException
{
  public RestUrlNotFoundException(String url)
  {
    super(url + " was not found");
  }
}
