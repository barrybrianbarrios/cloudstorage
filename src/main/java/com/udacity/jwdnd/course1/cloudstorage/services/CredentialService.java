package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public Credential getCredential(int credentialid){
        return credentialMapper.getCredential(credentialid);
    }

    public ArrayList<Credential> getCredentials(int userId){
        ArrayList<Credential> b3 = credentialMapper.getCredentials(userId);

        System.out.println(b3.size());
        if(b3.size() > 1){
            System.out.println(b3.get(1).getPassword());
        }
        for (Credential credential:b3) {
            System.out.println(credential);
        }
        //return credentialMapper.getCredentials(userId);
        return b3;
    }

    /*
    public void addCredential(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setKey(encodedKey);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        credentialMapper.insert(credential);
    }*/

    public int deleteCredential(int credentialid){
         return credentialMapper.delete(credentialid);
    }

    public int updateCredential(Credential credential){
        return credentialMapper.update(credential);
    }

    public int upsertCredential(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setKey(encodedKey);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        System.out.println("credentialid: " + credential.getCredentialid());
        return credentialMapper.upsert(credential);
    }
    private Credential encryptPassword(Credential c) {
        String encryptedPassword = encryptionService.encryptValue(c.getPassword(), c.getKey());
        c.setPassword(encryptedPassword);
        return c;
    }

    public String decryptPassword(Credential c) {
        return encryptionService.decryptValue(c.getPassword(), c.getKey());
    }

}
