package org.books.services;

import com.google.common.io.BaseEncoding;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.books.entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class SecurityService {

    @Autowired
    private Environment environment;

    private static final String KEY_PROPERTY = "secret.key";

    private static final Logger LOGGER = Logger.getLogger(SecurityService.class.getName());

    public String gerarToken(Owner owner) {
        byte[] apykeybytes = environment.getProperty(KEY_PROPERTY).getBytes();
        Key signingKey = new SecretKeySpec(apykeybytes, SignatureAlgorithm.HS256.getJcaName());

        return Jwts
            .builder()
            .setIssuer("My Library App")
            .setSubject("Save your library, find your stuff!")
            .setIssuedAt(new Date())
            .claim("name", owner.getName())
            .signWith(SignatureAlgorithm.HS256, signingKey)
            .compact();
    }

    public boolean verificar(String name, String token) {
        Claims body = Jwts
            .parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(environment.getProperty(KEY_PROPERTY)))
            .parseClaimsJws(token)
            .getBody();

        String nameToken = body.get("name").toString();

        return checkValues(name, nameToken);
    }

    private String decode(String encodedString) {
        byte[] decoded = BaseEncoding.base64().decode(encodedString);
        return new String(decoded);
    }

    private boolean checkValues(String value1, String value2) {
        return value1.equals(value2);
    }


}
