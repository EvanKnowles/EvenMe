package za.co.knonchalant.evenme;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class REST {


    public static String sendPost(String url, String body, Map<String, String> headers) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        for (Map.Entry<String, String> stringStringEntry : headers.entrySet()) {
            con.setRequestProperty(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(body);
        wr.flush();
        wr.close();

        return convertStream(con.getInputStream());
    }

    public static String sendGet(String url, Map<String, String> urlParameters, Map<String, String> headers) throws IOException {
        String resultUrl = url;
        if (!urlParameters.isEmpty()) {
            resultUrl += "?";
        }

        for (Map.Entry<String, String> stringStringEntry : urlParameters.entrySet()) {
            String key = stringStringEntry.getKey();
            resultUrl += "&" + enc(key) + "=" + enc(stringStringEntry.getValue());
        }


        URL obj = new URL(resultUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
//        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
//        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.63 Safari/537.36");
//        con.setRequestProperty("accept-encoding","gzip, deflate, br");
//        con.setRequestProperty("Connection", "keep-alive");
//        con.setRequestProperty("Proxy-Connection", "keep-alive");
        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        for (Map.Entry<String, String> stringStringEntry : headers.entrySet()) {
            con.setRequestProperty(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        try {
            int responseCode = con.getResponseCode();
        } catch (Exception ex) {
            ex.printStackTrace();
            return convertStream(con.getErrorStream());
        }

        return convertStream(con.getInputStream());
    }

    private static String convertStream(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(inputStream));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    private static String enc(String key) throws UnsupportedEncodingException {
        return URLEncoder.encode(key, "utf-8");
    }
}