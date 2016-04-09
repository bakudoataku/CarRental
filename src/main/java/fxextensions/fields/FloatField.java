package fxextensions.fields;

import javafx.scene.control.TextField;

/**
 * Created by Bartosz on 24.03.2016.
 */
public class FloatField extends TextField {
    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("\\d.?\\d?")) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (text.matches("\\d.?\\d?")) {
            super.replaceSelection(text);
        }
    }
}
