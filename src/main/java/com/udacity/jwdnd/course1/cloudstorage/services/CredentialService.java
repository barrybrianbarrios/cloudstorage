package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
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

    public void addCredential(Credential credential){
        credentialMapper.insert(credential);
    }

    public int deleteCredential(int credentialid){
         return credentialMapper.delete(credentialid);
    }
}
