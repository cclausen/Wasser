package de.horroreyes.wasser.forms;

import de.horroreyes.wasser.model.Summary;
import de.horroreyes.wasser.properties.HelferstundenProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Component
public class HelferstundenGoogleForm implements GoogleForm {
    private static final String YEAR = "_year";
    private static final String MONTH = "_month";
    private static final String DAY = "_day";
    private static final String UTF_8 = StandardCharsets.UTF_8.name();
    private final DecimalFormat germanDecimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.GERMAN));

    private final HelferstundenProperties properties;

    public HelferstundenGoogleForm(HelferstundenProperties properties) {
        this.properties = properties;
    }


    public boolean sendForm(Summary summary) {
        try {
            URL url = new URL(properties.getPostUrl());

            //PREPARE PARAMS
            StringBuilder postData = prepareParams(summary);

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

    public String openPrefilledForm(Summary summary) throws UnsupportedEncodingException {
        return properties.getPrefillUrl() + "&" + prepareParams(summary);
    }

    private StringBuilder prepareParams(Summary summary) throws UnsupportedEncodingException {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(properties.getDatum() + YEAR, summary.duty().getDate().getYear());
        params.put(properties.getDatum() + MONTH, summary.duty().getDate().getMonthValue());
        params.put(properties.getDatum() + DAY, summary.duty().getDate().getDayOfMonth());
        params.put(properties.getBezirk(), summary.place().getDistrict()); // Bremen-Stadt
        params.put(properties.getEinsatzgebiet(), summary.place().getOfficialName()); // Bultensee
        params.put(properties.getBadegaeste(), summary.duty().getAmountOfVisitors()); // 0-5
        params.put(properties.getStunden(), germanDecimalFormat.format(summary.totalHours()));
        params.put(properties.getEinsatzkraefte(), summary.persons().size());
        params.put(properties.getFirstResponder(), summary.duty().getFirstResponder());
        params.put(properties.getReanimationen(), summary.duty().getReanimations());
        params.put(properties.getAed(), summary.duty().getAed());
        params.put(properties.getHilfeleistungenPersonen(), summary.duty().getHelpPersons());
        params.put(properties.getDavonMedizinisch(), summary.duty().getWasMedical());
        params.put(properties.getDavonLebensrettung(), summary.duty().getWasLifeThreatening());
        params.put(properties.getDavonWasserrettung(), summary.duty().getWasInWater());
        params.put(properties.getDavonLebensgefahrRetter(), summary.duty().getWasLifeThreateningForHelper());
        params.put(properties.getDavonVerstorben(), summary.duty().getWasDead());
        params.put(properties.getDavonErtrunken(), summary.duty().getWasDrowned());
        params.put(properties.getHilfeSachwerte(), summary.duty().getHelpThings());
        params.put(properties.getHilfeUmwelt(), summary.duty().getHelpEnvironment());
        params.put(properties.getHilfeTiere(), summary.duty().getHelpAnimals());

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
