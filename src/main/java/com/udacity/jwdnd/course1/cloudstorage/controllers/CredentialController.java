package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService){
        this.credentialService = credentialService;
        this.userService = userService;
    }
    @PostMapping()
    public String  upsertCredential(@ModelAttribute("credentialform") CredentialForm credentialform, Model model, Authentication authentication){
        int id = userService.getUser(authentication.getName()).getUserid();
        Credential credential = new Credential(credentialform.getUrl(), credentialform.getUsername(),"" ,credentialform.getPassword(), String.valueOf(id),credentialform.getCredentialid());
        boolean result = credentialService.upsertCredential(credential) == 1;
        //model.addAttribute("credentials", credentialService.getCredentials(id));
        model.addAttribute("result", result ? "success" : "failure");
        return "result";
    }

    @GetMapping("/delete/{credentialid}")
    public String deleteCredential(@PathVariable int credentialid, Model model){
        //public String deleteCredential(@ModelAttribute("credentialform") CredentialForm credentialform, Model model, Authentication authentication){
        //int id = userService.getUser(authentication.getName()).getUserid();
        System.out.println("CredentialId: " + credentialid);
        int value = credentialService.deleteCredential(credentialid);
        System.out.println("Number of records deleted: " + value);
        model.addAttribute("result", "success");
        return "result";
    }
}
