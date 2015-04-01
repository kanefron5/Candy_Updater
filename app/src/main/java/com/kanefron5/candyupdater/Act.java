package com.kanefron5.candyupdater;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Act extends PreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.act);
    }
}