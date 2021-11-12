package src.helpers;

import java.security.MessageDigest;

public class Sha512 {
    public static String applySha512(String input){
        try {
            var digest = MessageDigest.getInstance("SHA-512");
            /* Applies sha512 to our input */
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            var hexString = new StringBuilder();
            for (var elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
