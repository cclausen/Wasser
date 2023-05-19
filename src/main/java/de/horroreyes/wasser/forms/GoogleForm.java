package de.horroreyes.wasser.forms;

import de.horroreyes.wasser.model.Summary;

import java.io.UnsupportedEncodingException;

public interface GoogleForm {
    boolean sendForm(Summary summary);

    String openPrefilledForm(Summary summary) throws UnsupportedEncodingException;
}
