package za.co.knonchalant.evenme.client;


import com.google.gson.Gson;
import za.co.knonchalant.evenme.client.exception.BadCredentialsException;
import za.co.knonchalant.evenme.client.exception.RestClientException;
import za.co.knonchalant.evenme.client.exception.RestServerException;
import za.co.knonchalant.evenme.client.exception.RestUrlNotFoundException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class REST implements Serializable {
    private static final String UTF_8 = "UTF-8";
    private static final String APPLICATION_JSON = "application/json";
    private static final String AUTHORIZATION_TYPE_BEARER = "Bearer ";
    // Standard user-agent that no-one will reject - could be customized if need be.
    private String userAgent = "Mozilla/5.0";
    private String charset = UTF_8;
    private String mediaType = APPLICATION_JSON;

    private String url;
    private Map<String, String> arguments = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private Object bodyObject;
    private String username;
    private String password;
    private String keyStorePath;
    private String keyStorePassword;
    private String token;

    private Integer connectTimeoutInMs = null;

    /**
     * Private constructor
     *
     * @param url the url
     **/
    private REST(String url) {
        this.url = url;
    }

    /**
     * URL that will be the focus of our request.
     *
     * @param url the url
     * @return the result
     **/
    public static REST url(String url) {
        return new REST(url);
    }

    /**
     * Allow the user-agent to be customized.
     *
     * @param userAgent the new user agent
     * @return the REST object.
     */
    public REST userAgent(String userAgent) {
        this.userAgent = userAgent;

        return this;
    }

    /**
     * Set the Media type. The Default is application/json
     *
     * @param mediaType the media type as a string
     * @return the REST object
     **/
    public REST mediaType(String mediaType) {
        this.mediaType = mediaType;

        return this;
    }

    /**
     * Set the connection timeout for the underlying connection
     *
     * @param connectTimeoutInMs the timeout, specified in milliseconds
     * @return the REST object
     */
    public REST connectTimeOut(int connectTimeoutInMs) {
        this.connectTimeoutInMs = connectTimeoutInMs;

        return this;
    }

    /**
     * Add a single argument to the URL
     *
     * @param key   the key
     * @param value the value
     * @return the result
     **/
    public REST argument(String key, String value) {
        arguments.put(key, value);

        return this;
    }

    /**
     * Add a set of arguments to the URL.
     *
     * @param arguments the arguments
     * @return the result
     **/
    public REST arguments(Map<String, String> arguments) {
        this.arguments.putAll(arguments);

        return this;
    }

    /**
     * Add a single header to the request
     *
     * @param key   the key
     * @param value the value
     * @return the result
     **/
    public REST header(String key, String value) {
        headers.put(key, value);

        return this;
    }

    /**
     * Add a set of headers to the request.
     *
     * @param headers the headers
     * @return the result
     **/
    public REST headers(Map<String, String> headers) {
        this.headers.putAll(headers);

        return this;
    }

    /**
     * Authentication to be used with BASIC auth.
     *
     * @param username the username
     * @param password the password
     * @return the REST object.
     */
    public REST authentication(String username, String password) {
        this.username = username;
        this.password = password;

        return this;
    }

    /**
     * Authorization to be used with Bearer.
     *
     * @param token the access token
     * @return the REST object.
     */
    public REST authorization(String token) {
        this.token = token;
        return this;
    }

    /**
     * The keystore path and password for certificate-based authentication
     *
     * @param keyStorePath     the keystore path
     * @param keyStorePassword the keystore password
     * @return the REST object
     */
    public REST keyStoreAuthentication(String keyStorePath, String keyStorePassword) {
        this.keyStorePath = keyStorePath;
        this.keyStorePassword = keyStorePassword;

        return this;
    }

    /**
     * Performs a GET that does not return anything.
     *
     * @throws IOException
     */
    public String get() throws IOException {
        return doConnection(EVerb.GET);
    }

    /**
     * Performs a POST that does not return anything.
     */
    public String post() throws IOException {
        return doConnection(EVerb.POST);
    }

    /**
     * Performs a PUT that does not return anything.
     */
    public String put() throws IOException {
        return doConnection(EVerb.PUT);
    }

    /**
     * Delete
     **/
    public String delete() throws IOException {
        return doConnection(EVerb.DELETE);
    }

    /**
     * Post
     *
     * @param clazz the returned clazz
     * @return the populated object
     **/
    public <T> T put(Class<T> clazz) throws IOException {
        return verb(clazz, EVerb.PUT);
    }

    /**
     * Post
     *
     * @param clazz the returned clazz
     * @return the populated object
     **/
    public <T> T post(Class<T> clazz) throws IOException {
        return verb(clazz, EVerb.POST);
    }

    /**
     * Perform a GET
     *
     * @param clazz the expected output clazz
     * @return the result
     **/
    public <T> T get(Class<T> clazz) throws IOException {
        return verb(clazz, EVerb.GET);
    }

    /**
     * Perform a get using a type, which allows us to deserialize e.g., arrays
     *
     * @param type the type
     * @param <T>  the type of T
     * @return deserialized type of T
     */
    public <T> T get(Type type) throws IOException {
        return verb(type, EVerb.GET);
    }

    /**
     * Perform the HTTP action with the supplied verb, producing the supplied class
     *
     * @param clazz the class to deserialize to
     * @param verb  the chosen verb
     * @param <T>   type of the class
     * @return deserialized verb
     */
    public <T> T verb(Class<T> clazz, EVerb verb) throws IOException {
        final String response = doConnection(verb);
        return new Gson().fromJson(response, clazz);
    }

    public <T> T verb(Type type, EVerb verb) throws IOException {
        final String response = doConnection(verb);
        return new Gson().fromJson(response, type);
    }

    /**
     * Do connection
     *
     * @return the result
     **/
    private String doConnection(EVerb verb) throws IOException {
        String finalUrl = appendArguments(url);

        URL obj = new URL(finalUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setUseCaches(false);
        con.setRequestProperty("User-Agent", userAgent);

        if (connectTimeoutInMs != null) {
            con.setConnectTimeout(connectTimeoutInMs);
        }

        try {
            setupAuthentication(con);
        } catch (Exception e) {
            throw new IOException(e);
        }

        for (Map.Entry<String, String> header : headers.entrySet()) {
            con.setRequestProperty(header.getKey(), header.getValue());
        }

        con.setRequestMethod(verb.getVerb());

        if (verb.isBody()) {
            if (bodyObject != null) {
                setupPOSTBody(con);
            }
        }

        checkResponseCode(con);
        String response = getResponse(con);
        con.disconnect();
        return response;
    }

    /**
     * Append any additional arguments to the query string.
     */
    private String appendArguments(String finalUrl) throws UnsupportedEncodingException {
        if (arguments == null) {
            return finalUrl;
        }

        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> entry : arguments.entrySet()) {
            sj.add(URLEncoder.encode(entry.getKey(), UTF_8) + "=" + URLEncoder.encode(entry.getValue(), UTF_8));
        }
        return arguments.isEmpty() ? finalUrl : finalUrl + "?" + sj;
    }

    /**
     * If a username is set, add the Basic authentication header to the request.
     *
     * @param con
     */
    private void setupAuthentication(HttpURLConnection con) throws Exception {
        if (username != null) {
            String encoded = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));  //Java 8
            con.setRequestProperty("Authorization", "Basic " + encoded);
        } else if (token != null) {
            con.setRequestProperty("Authorization", AUTHORIZATION_TYPE_BEARER + token);
        } else if (keyStorePath != null) {
            if (con instanceof HttpsURLConnection) {
                KeyStore ks = KeyStore.getInstance("PKCS12");
                FileInputStream fis = new FileInputStream(keyStorePath);
                ks.load(fis, keyStorePassword.toCharArray());
                KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
                kmf.init(ks, keyStorePassword.toCharArray());
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(kmf.getKeyManagers(), null, new java.security.SecureRandom());
                ((HttpsURLConnection) con).setSSLSocketFactory(sc.getSocketFactory());
            }
        }
    }

    /**
     * Check the response code for standard errors and throw appropriate exceptions
     *
     * @param con the URL connection that has been opened and presumably completed
     * @throws IOException if an error occurred connecting to the server.
     */
    private void checkResponseCode(HttpURLConnection con) throws IOException {
        int responseCode = con.getResponseCode();
        if (responseCode == 404) {
            throw new RestUrlNotFoundException(url);
        } else if (responseCode == 403 || responseCode == 401) {
            throw new BadCredentialsException(responseCode);
        } else if (responseCode >= 400 && responseCode < 500) {
            throw new RestClientException(getErrorResponse(con));
        } else if (responseCode >= 500) {
            throw new RestServerException(getErrorResponse(con));
        }
    }

    /**
     * Serialize the body object to JSON, set the content type and write it to the connection.
     *
     * @param con
     * @throws IOException
     */
    private void setupPOSTBody(HttpURLConnection con) throws IOException {
        final String bodyString;
        if (bodyObject instanceof String) {
            bodyString = (String) bodyObject;
        } else {
            if (this.mediaType.equalsIgnoreCase(APPLICATION_JSON)) {
                bodyString = new Gson().toJson(bodyObject);
            } else {
                bodyString = bodyObject == null ? "" : bodyObject.toString();
            }
        }

        int contentLength = bodyString.getBytes(StandardCharsets.UTF_8).length;
        con.setRequestProperty("Content-Type", String.format("%s; charset=%s", this.mediaType, this.charset));
        con.setRequestProperty("Content-Length", Integer.toString(contentLength));
        con.setRequestProperty("Cache-Control", "no-cache");

        con.setDoOutput(true);

        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(bodyString);
            wr.flush();
        }
    }

    /**
     * Get response
     *
     * @param con the con
     * @return the result
     **/
    private String getResponse(HttpURLConnection con) throws IOException {
        return readInputStream(con.getInputStream());
    }

    /**
     * Get error response
     *
     * @param con the con
     * @return the result
     **/
    private String getErrorResponse(HttpURLConnection con) throws IOException {
        return readInputStream(con.getErrorStream());
    }

    /**
     * Read input stream
     *
     * @param inputStream the input stream
     * @return the result
     **/
    private String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return response.toString();
    }

    /**
     * Specify a body to use, presumably with a POST request. If a GET is used, the body will not be used.
     *
     * @param bodyObject JSON serializable body.
     * @return the REST object
     */
    public REST body(Object bodyObject) {
        this.bodyObject = bodyObject;

        return this;
    }
}