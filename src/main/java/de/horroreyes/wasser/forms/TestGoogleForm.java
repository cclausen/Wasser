package de.horroreyes.wasser.forms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class TestGoogleForm implements GoogleForm {
    public static final String ENTRY = "entry.";
    public static final String UTF_8 = StandardCharsets.UTF_8.name();
    private final String formPostUrl = "https://docs.google.com/forms/u/0/d/e/1FAIpQLScDkljY3iLLuZOZ0veEgn37OT-XNXoajo530lUogDBT9hauyg/formResponse";
    private final String prefillUrl = "https://docs.google.com/forms/d/e/1FAIpQLScDkljY3iLLuZOZ0veEgn37OT-XNXoajo530lUogDBT9hauyg/viewform?usp=pp_url";

    private final String dateY = "624781151_year";
    private final String dateM = "624781151_month";
    private final String dateD = "624781151_day";
    private final String text = "518543478";
    private final String selection = "527384042";

    @Override
    public boolean sendTestForm() {
        try {
            URL url = new URL(formPostUrl);

            //PREPARE PARAMS
            StringBuilder postData = prepareParams();

            //SEND POST
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            byte[] postDataBytes = postData.toString().getBytes(UTF_8);
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.getOutputStream().write(postDataBytes);

            //GET RESPONSE
            int response = conn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF_8));
                in.close();
            }
            conn.disconnect();

        } catch (IOException e1) {
            log.error("Error sending test form", e1);
        }
        return true;
    }

    @Override
    public String openPrefilledForm() throws UnsupportedEncodingException {
        return prefillUrl + "?usp=pp_url&" + prepareParams();
    }

    private StringBuilder prepareParams() throws UnsupportedEncodingException {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(ENTRY + text, "TEST123");
        params.put(ENTRY + selection, "Bultensee");
        params.put(ENTRY + dateY, "1989");
        params.put(ENTRY + dateM, "1");
        params.put(ENTRY + dateD, "5");
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), UTF_8));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), UTF_8));
        }
        return postData;
    }
}
