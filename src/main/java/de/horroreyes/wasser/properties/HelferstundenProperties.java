package de.horroreyes.wasser.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "helferstunden")
public class HelferstundenProperties {
    String prefillUrl;
    String postUrl;
    String datum;
    String bezirk;
    String einsatzgebiet;
    String badegaeste;
    String stunden;
    String einsatzkraefte;
    String firstResponder;
    String reanimationen;
    String aed;
    String hilfeleistungenPersonen;
    String davonMedizinisch;
    String davonLebensrettung;
    String davonWasserrettung;
    String davonLebensgefahrRetter;
    String davonVerstorben;
    String davonErtrunken;
    String hilfeSachwerte;
    String hilfeUmwelt;
    String hilfeTiere;

}
