package com.algonquincollege.bear0064.caesarcipher;

/**
 *  Encrypt/Decrypt text using ROT13 encryption
 *  @author Caleb Bear bear0064@algonquinlive.com
 */

public class Constants {

    public static final boolean DEBUG           = true;
    public static final String ABOUT_DIALOG_TAG = "About";
    public static final String LOG_TAG          = "CaesarCipher";
    public static final int ROT13               = 13;
    public static final int ROTATION_MAX        = 25;
    public static final int ROTATION_MIN        = 0;
    public static final String ROTATIONS        = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String THE_MESSAGE      = "com.algonquincollege.bear0064.caesarcipher.message";
    public static final String THE_ROTATION     = "com.algonquincollege.bear0064.caesarcipher.rotation";

    private Constants() {}
}
