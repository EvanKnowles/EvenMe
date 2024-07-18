package za.co.knonchalant.evenme.client;

/**
 * <p>Title: EVerb</p>
 * <p/>
 * <p>Description: Enumerator containing the verbs supported by the REST client.</p>
 * <p/>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p/>
 * <p>Company: Discovery</p>
 *
 * @author Evan Knowles
 * @version 1.0
 */
public enum EVerb
{
  GET("GET", false), HEAD("HEAD", false),TRACE("TRACE", false), OPTIONS("OPTIONS", true),
  POST("POST", true), PUT("PUT", true), DELETE("DELETE", false);

  private String verb;
  private boolean body;

  EVerb(String verb, boolean body)
  {
    this.verb = verb;
    this.body = body;
  }

  public String getVerb()
  {
    return verb;
  }

  public boolean isBody()
  {
    return body;
  }
}
