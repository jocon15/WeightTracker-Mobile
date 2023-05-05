package com.termproject;

import android.content.Context;
import android.content.SharedPreferences;

public class NotificationsManager {
    private static SharedPreferences mSharedPref;
    private static SharedPreferences.Editor mEditor;
    private static final String INFO = "notification_pref";
    private static NotificationsManager instance;
    private String mUsername;

    /**
     * Constructor.
     * @param context - the context.
     * @param username - the current username.
     */
    private NotificationsManager(Context context, String username){
        mUsername = username;
        mSharedPref = context.getSharedPreferences(INFO, Context.MODE_PRIVATE);
        mEditor = mSharedPref.edit();
        mEditor.apply();
    }

    /**
     * Initializes the notifications manager.
     * @param context - the context.
     * @param username - the current username.
     */
    public static void initialize(Context context, String username){
        if (instance == null){
            instance = new NotificationsManager(context, username);
        }
    }

    /**
     * Get the singleton instance.
     * Throws IllegalStateException if instance has not been initialized.
     * @return - the @NotificationsManager instance
     */
    public static NotificationsManager getInstance(){
        if (instance == null){
            throw new IllegalStateException("Notifications manager has not bee initialized");
        }
        return instance;
    }

    /**
     * Sets the username.
     * @param username - the new username value.
     */
    public void setUsername(String username){
        mUsername = username;
    }

    /**
     * Set notification preference in shared preferences.
     * @param isEnabled - the setting value to set.
     */
    public void saveNotificationPreference(boolean isEnabled){
        mEditor.putBoolean(mUsername, isEnabled);
        mEditor.apply();
    }

    /**
     * Get the notification preference setting state.
     * @return the setting state.
     */
    public boolean getNotificationPreference(){
        return mSharedPref.getBoolean(mUsername, false);
    }

    /**
     * Remove the notification preference from shared preferences.
     */
    public void removeNotificationPreference(){
        if (mSharedPref.contains(mUsername)){
            mEditor.remove(mUsername);
            mEditor.apply();
        }
    }
}
