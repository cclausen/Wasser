package de.horroreyes.wasser.forms;

import java.io.UnsupportedEncodingException;

public interface GoogleForm {
    boolean sendTestForm();

    String openPrefilledForm() throws UnsupportedEncodingException;
}
