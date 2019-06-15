package io.plactal.ecc.main;

import io.plactal.ecc.crypto.ec.EosPrivateKey;
import io.plactal.ecc.crypto.ec.EosPublicKey;
import io.plactal.ecc.crypto.ec.EcSignature;
import io.plactal.ecc.crypto.ec.EcDsa;
import io.plactal.ecc.crypto.digest.Sha256;

import org.json.JSONObject;


public class EosSigner {


    public static String generateKeys() {
        EosPrivateKey privateKey = new EosPrivateKey();
        EosPublicKey publicKey = privateKey.getPublicKey();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("private", privateKey.toString());
        jsonObject.put("public", publicKey.toString());
        return jsonObject.toString();
    }

    public static String signText(String text, String key) {
        EosPrivateKey privateKey = new EosPrivateKey(key);
        Sha256 digest = Sha256.from(text.getBytes());
        EcSignature signature = EcDsa.sign(digest, privateKey);
        return signature.toString();
    }

    public static void main(String[] args) {
        System.out.println("Generate Keys: "+EosSigner.generateKeys());
        EosPrivateKey privateKey = new EosPrivateKey();
        String text = "Hello World!";
        System.out.println("Signature: "+EosSigner.signText(text, privateKey.toString()));
    }

}