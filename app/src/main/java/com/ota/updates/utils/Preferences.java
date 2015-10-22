package com.ota.updates.utils;
/*
 * Copyright (C) 2015 Matt Booth.
 *
 * Licensed under the Attribution-NonCommercial-ShareAlike 4.0 International 
 * (the "License") you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://creativecommons.org/licenses/by-nc-sa/4.0/legalcode
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences implements Constants {

    public static final String TAG = "Preferences";

    public static String PREF_NAME = "OTAUpdateSettings";

    private static String mDefaultString = "";

    private Preferences() {
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getUpdateLastChecked(Context context) {
        return getPrefs(context).getString(LAST_CHECKED_FOR_UPDATE, mDefaultString);
    }

    public static void setUpdateLastChecked(Context context, String time) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(LAST_CHECKED_FOR_UPDATE, time);
        editor.commit();
    }
}
