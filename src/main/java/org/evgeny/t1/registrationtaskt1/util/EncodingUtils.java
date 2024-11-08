package org.evgeny.t1.registrationtaskt1.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncodingUtils {
    public static String encodeEmailAndCode(String email, String code){
        return Base64.getEncoder().encodeToString((email+":"+code).getBytes(StandardCharsets.US_ASCII));
    }

//    public static void main(String[] args) {
//        System.out.println(encodeEmailAndCode("vova.dickun@gmail.com", "9819058455f57f64524d51ef620361fd")
//                .equals("dm92YS5kaWNrdW5AZ21haWwuY29tOjk4MTkwNTg0NTVmNTdmNjQ1MjRkNTFlZjYyMDM2MWZk"));
//    }
}
