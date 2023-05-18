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


    public boolean sendTestForm(Summary summary) {
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
        params.put(properties.getDatum() + YEAR, summary.day().getStart().getYear());
        params.put(properties.getDatum() + MONTH, summary.day().getStart().getMonthValue());
        params.put(properties.getDatum() + DAY, summary.day().getStart().getDayOfMonth());
        params.put(properties.getBezirk(), summary.place().getDistrict()); // Bremen-Stadt
        params.put(properties.getEinsatzgebiet(), summary.place().getOfficialName()); // Bultensee
        params.put(properties.getBadegaeste(), summary.day().getAmountOfVisitors()); // 0-5
        params.put(properties.getStunden(), germanDecimalFormat.format(summary.totalHours()));
        params.put(properties.getEinsatzkraefte(), summary.persons().size());
        params.put(properties.getFirstResponder(), summary.day().getFirstResponder());
        params.put(properties.getReanimationen(), summary.day().getReanimations());
        params.put(properties.getAed(), summary.day().getAed());
        params.put(properties.getHilfeleistungenPersonen(), summary.day().getHelpPersons());
        params.put(properties.getDavonMedizinisch(), summary.day().getWasMedical());
        params.put(properties.getDavonLebensrettung(), summary.day().getWasLifeThreatening());
        params.put(properties.getDavonWasserrettung(), summary.day().getWasInWater());
        params.put(properties.getDavonLebensgefahrRetter(), summary.day().getWasLifeThreateningForHelper());
        params.put(properties.getDavonVerstorben(), summary.day().getWasDead());
        params.put(properties.getDavonErtrunken(), summary.day().getWasDrowned());
        params.put(properties.getHilfeSachwerte(), summary.day().getHelpThings());
        params.put(properties.getHilfeUmwelt(), summary.day().getHelpEnvironment());
        params.put(properties.getHilfeTiere(), summary.day().getHelpAnimals());

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
