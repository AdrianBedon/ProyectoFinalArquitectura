package com.udla.Security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.Date;

public class TokenService {

    private static final String SECRET_KEY = "mySecretKeyDisenoYArquitecturaSoftware";

    public static String generateToken(String subject, long expirationTime) throws JOSEException {
        JWSSigner signer = new MACSigner(SECRET_KEY);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(subject)
                .expirationTime(new Date(System.currentTimeMillis() + expirationTime))
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public static boolean validateToken(String token) throws ParseException, JOSEException {
        JWSVerifier verifier = new MACVerifier(SECRET_KEY);
        SignedJWT signedJWT = SignedJWT.parse(token);

        return signedJWT.verify(verifier) && signedJWT.getJWTClaimsSet().getExpirationTime().after(new Date());
    }

}
