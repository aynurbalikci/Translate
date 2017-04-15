package com.serifgungor.myapplication;

import java.util.ArrayList;

/**
 * Created by Hafta_Sonu on 15.04.2017.
 */

public interface TranslateResultListener {
    void onTranslateResultSuccess(ArrayList<String> words);
    void onTranslateResultError();
}
