package de.horroreyes.wasser.forms;

import de.horroreyes.wasser.properties.HelferstundenProperties;
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
public class HelferstundenGoogleForm implements GoogleForm {
    private static final String YEAR = "_year";
    private static final String MONTH = "_month";
    private static final String DAY = "_day";
    private static final String UTF_8 = StandardCharsets.UTF_8.name();

    private final HelferstundenProperties properties;

    public HelferstundenGoogleForm(HelferstundenProperties properties) {
        this.properties = properties;
    }


    public boolean sendTestForm() {
        try {
            URL url = new URL(properties.getPostUrl());

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

    public String openPrefilledForm() throws UnsupportedEncodingException {
        return properties.getPrefillUrl() + "&" + prepareParams();
    }

    private StringBuilder prepareParams() throws UnsupportedEncodingException {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(properties.getDatum() + YEAR, "1");
        params.put(properties.getDatum() + MONTH, "1");
        params.put(properties.getDatum() + DAY, "1");
        params.put(properties.getBezirk(), "Bremen-Stadt"); // MIST
        params.put(properties.getEinsatzgebiet(), "Bultensee"); // MIST
        params.put(properties.getBadegaeste(), "1"); // MIST
        params.put(properties.getStunden(), "2");
        params.put(properties.getEinsatzkraefte(), "3");
        params.put(properties.getFirstResponder(), "4");
        params.put(properties.getReanimationen(), "5");
        params.put(properties.getAed(), "6");
        params.put(properties.getHilfeleistungenPersonen(), "7");
        params.put(properties.getDavonMedizinisch(), "8");
        params.put(properties.getDavonLebensrettung(), "9");
        params.put(properties.getDavonWasserrettung(), "10");
        params.put(properties.getDavonLebensgefahrRetter(), "11");
        params.put(properties.getDavonVerstorben(), "12");
        params.put(properties.getDavonErtrunken(), "13");
        params.put(properties.getHilfeSachwerte(), "14");
        params.put(properties.getHilfeUmwelt(), "15");
        params.put(properties.getHilfeTiere(), "16");

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
