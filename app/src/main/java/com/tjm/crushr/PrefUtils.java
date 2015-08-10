package com.tjm.crushr;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cymak on 10/6/14.
 */
public class PrefUtils {
    public static void addItem(Context ctx, String item, int id) {
        SharedPreferences prefs = ctx.getSharedPreferences(crushrProvider.SHARED_PREF_TAG, ctx.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> set = prefs.getStringSet(crushrProvider.SHARED_PREF_LIST+id, new HashSet<String>());
        set.add(item);
        editor.remove(crushrProvider.SHARED_PREF_LIST+id);
        editor.commit();
        editor.putStringSet(crushrProvider.SHARED_PREF_LIST+id, set);
        editor.commit();
    }

    public static void removeItem(Context ctx, String item, int id) {
        SharedPreferences prefs = ctx.getSharedPreferences(crushrProvider.SHARED_PREF_TAG, ctx.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> set = prefs.getStringSet(crushrProvider.SHARED_PREF_LIST+id, new HashSet<String>());
        set.remove(item);
        editor.remove(crushrProvider.SHARED_PREF_LIST+id);
        editor.commit();
        editor.putStringSet(crushrProvider.SHARED_PREF_LIST+id, set);
        editor.commit();
    }

    public static void setPrimaryColor(Context ctx, int color, int id) {
        SharedPreferences prefs = ctx.getSharedPreferences(crushrProvider.SHARED_PREF_TAG, ctx.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(crushrProvider.SHARED_PREF_PRIMARY_COLOR+id, color);
        editor.commit();
    }
}
