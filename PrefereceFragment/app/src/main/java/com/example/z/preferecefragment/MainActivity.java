package com.example.z.preferecefragment;

import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载Fragment
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefsFragement()).commit();
    }

    //建一个PrefFragment类继承自PreferenceFragment然后加载首选项配置的xml文件preferences.xml
    public static class PrefsFragement extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
