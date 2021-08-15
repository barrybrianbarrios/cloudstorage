package com.udacity.jwdnd.course1.cloudstorage.controllers;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.*;

@Controller
public class HomeController {
    private CredentialService credentialService;
    private UserService userService;

    //@Autowired
    //EncryptionService encryptionService;
    public HomeController(CredentialService credentialService, UserService userService){
        this.credentialService = credentialService;
        this.userService = userService;
    }
    /*
    private MessageService messageService;

    public HomeController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/chat")
    public String addMessages(@ModelAttribute("chatForm") ChatForm chatForm, Model model, Authentication authentication){
        chatForm.setUsername(authentication.getName());
        messageService.addMessage(chatForm);
        model.addAttribute("messages", messageService.getMessages());
        // clear form
        chatForm.setUsername("");
        chatForm.setMessageText("");
        chatForm.setMessageType("");
        return "chat";
    }

     */

    /*@ModelAttribute("credentialform")
    public CredentialForm getCredentialForm(){
        return new CredentialForm();
    }
    @PostMapping()
    public String editCredential(@ModelAttribute Credential credential, Model model, Authentication authentication){
        credentialService.updateCredential(credential);
        return "result";
    }*/



    @GetMapping("/home")
    public String getCredentials(@ModelAttribute("credentialform") CredentialForm credentialForm, Model model, Authentication authentication){
        User user= userService.getUser(authentication.getName());
        /*
        List<Credential> credentialList = Arrays.asList(new Credential("www.udacity.com", "bcubeb31", "123", "calvin1", 1),
                new Credential("bankofamerica.com", "bucbeb32", "123", "calvin2", 1));
        */
        model.addAttribute("credentials", credentialService.getCredentials(user.getUserid()) );

        //model.addAttribute("credentials", credentialList );
        return "home";
    }



}
