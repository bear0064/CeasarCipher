package util;

import com.algonquincollege.bear0064.caesarcipher.Constants;

/**
 *  Encrypt/Decrypt text using ROT13 encryption
 *  @author Caleb Bear bear0064@algonquinlive.com
 */
public class CaesarCipher {

    private static final int MAX_ROTATION;

    static{
        MAX_ROTATION = Constants.ROTATIONS.length();
    }

    private CaesarCipher(){

        //No-Op
    }

    static String decrypt(String encryptedMessage){

        return CaesarCipher.decrypt( encryptedMessage, Constants.ROT13 );
    }

    public static String decrypt( String encryptedMessage, int rotation ){

        return CaesarCipher.encrypt(encryptedMessage, MAX_ROTATION - rotation );
    }

    static String encrypt(String plainMessage){
        return CaesarCipher.encrypt( plainMessage, Constants.ROT13 );
    }


    public static String encrypt( String plainMessage, int rotation) {

        //char alpha;
        char c;
        int encryptedLetter;


        if (plainMessage == null ) return "";

        //StringBuffer encryptedMessage = new StringBuffer( plainMessage ); <- //TODO I know that this is a method in which you were grabbing the plain message and you were replacing each character using the for loop
        //I still had troubles trying to use the replace method, why pass plainMessage in to the string buffer as a paramater?
        // why not just make encryptedMessage a new stringBuffer and make it equal to the letters being passed through?


        //StringBuffer encryptedMessage = new StringBuffer ( );
        StringBuffer encryptedMessage = new StringBuffer ( plainMessage );

        for (int i = 0; i < plainMessage.length(); i++) {

            c = plainMessage.charAt(i);

            if (Character.isLetter(c)) {
                if (Character.isLowerCase(c)) {
                    //TODO I noticed that you used a ternary operator here, is that more efficient than using the if statement?

                    encryptedLetter =  (((int) c - 'a' + rotation) % 26) + (int) 'a';


                    c = (char) encryptedLetter;

                    encryptedMessage.append(Character.toString(c));


                } else {

                    //TODO This was where i ran into lots of issues, maybe I was having trouble wrapping my head around it but this what I understood
                    // replace uses (Start, End, StringValue)
                    //So encryptedMessage should = encryptedMessage.replace(i, **end- which I couldnt think of what to replace here** (((int) c - 'A' + rotation) % 26) + (int) 'A' );
                    //then the string which gave me some errors.
//                    encryptedMessage.replace(i,i,(((char) c - 'a' + rotation) % 26)) + (char) 'a'); <<--


                    encryptedLetter = (((int) c - 'A' + rotation) % 26) + (int) 'A';
                    c = (char) encryptedLetter;

                    encryptedMessage.append(Character.toString(c));


                }

            }


                //  return encryptedLetter;

            }
            return encryptedMessage.toString();
        }
}