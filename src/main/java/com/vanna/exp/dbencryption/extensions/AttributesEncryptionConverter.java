package com.vanna.exp.dbencryption.extensions;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;

public class AttributesEncryptionConverter<T, K> implements AttributeConverter <T, K>{

    private String ENCRYPTION_KEY = "SomeRandomKeyForNow";
    private String ENCRYPTION_ALGORITHM = "AES/ECB/PKCS5Padding";


    @Override
    @SuppressWarnings("unchecked")
    public K convertToDatabaseColumn(T attribute) {
        Key key = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ENCRYPTION_ALGORITHM);
        if (attribute instanceof String) {
            try {
                Cipher c = Cipher.getInstance(ENCRYPTION_ALGORITHM);
                c.init(Cipher.ENCRYPT_MODE, key);
                var encryptedBytes = c.doFinal(((String) attribute).getBytes());
                return (K) Base64.getEncoder().encodeToString(encryptedBytes);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                e.printStackTrace();
            }   
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T convertToEntityAttribute(K dbData) {
        Key key = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ENCRYPTION_ALGORITHM);
        if (dbData instanceof String) {
            try {
                Cipher c = Cipher.getInstance(ENCRYPTION_ALGORITHM);
                c.init(Cipher.DECRYPT_MODE, key);
                var decryptedBytes = c.doFinal(Base64.getDecoder().decode((String)dbData));
                return (T) new String(decryptedBytes);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                e.printStackTrace();
            }   
        }
        return null;
    }
    
}
