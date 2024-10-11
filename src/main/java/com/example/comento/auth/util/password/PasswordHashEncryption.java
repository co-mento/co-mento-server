package com.example.comento.auth.util.password;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class PasswordHashEncryption {

    private static final String PBKDF2_WITH_SHA1 = "PBKDF2WithHmacSHA1";

    private final int iterationCount;// 반복 횟수(암호화 강도)
    private final int keyLength;// 키 길이(암호화된 비밀번호 길이)


    public PasswordHashEncryption(@Value("${encryption.pbkdf2.iteration-count}") final int iteration,
                                  @Value("${encryption.pbkdf2.key-length}") final int keyLength){
        this.iterationCount = iteration;
        this.keyLength = keyLength;
    }

    public String encrypt(final String plainPassword, final String salt){
        try{
            KeySpec spec = new PBEKeySpec(plainPassword.toCharArray(), salt.getBytes(), iterationCount, keyLength);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBKDF2_WITH_SHA1);
            byte[] encodedPassword = keyFactory.generateSecret(spec)
                    .getEncoded();
            return Base64.getEncoder()
                    .withoutPadding()
                    .encodeToString(encodedPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Cannot encrypt password");
        }
    }

    public boolean matches(final String plainPassword, final String salt, final String hashedPassword){
        return encrypt(plainPassword, salt).equals(hashedPassword);
    }

}
