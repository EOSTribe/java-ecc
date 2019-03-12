package io.plactal.ecc.main;

import io.plactal.ecc.crypto.ec.EosPrivateKey;
import io.plactal.ecc.crypto.ec.EcSignature;
import io.plactal.ecc.crypto.ec.EcDsa;
import io.plactal.ecc.crypto.digest.Sha256;

public class EosSigner {


    public static void main(String[] args) throws Exception {
        if(args.length >= 2) {
            String key = args[0];
            String text = args[1];
            EosPrivateKey privateKey = new EosPrivateKey(key);
            Sha256 digest = Sha256.from(text.getBytes());
            EcSignature signature = EcDsa.sign(digest, privateKey);
            System.out.println(signature.toString());
        } else {
            System.out.println("Must pass two params: key and text to sign.");
        }
    }

}