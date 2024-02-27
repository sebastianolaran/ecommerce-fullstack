package org.sebastian;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;


@SpringBootApplication
public class HolaMundoApplication {

    public static void main(String[] args) {

        SpringApplication.run(HolaMundoApplication.class, args);
    }

}
