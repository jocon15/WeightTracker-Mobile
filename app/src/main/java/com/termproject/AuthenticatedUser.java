package com.termproject;

public class AuthenticatedUser {

    private String mUsername;

    /**
     * Constructor
     * @param username - the new username of the authenticated user.
     */
    public AuthenticatedUser(String username){
        mUsername = username;
    }

    /**
     * Get the username of the currently authenticated user.
     * @return - the username.
     */
    public String getUsername(){
        return mUsername;
    }
}
