package com.serifgungor.myapplication;

/**
 * Created by Hafta_Sonu on 15.04.2017.
 */

public class TranslateInput {
    String phrase;
    String from;
    String to;

    public String getPhrase() {
        return phrase;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public TranslateInput(String phrase, String from, String to) {
        this.phrase = phrase;
        this.from = from;
        this.to = to;

    }
}
