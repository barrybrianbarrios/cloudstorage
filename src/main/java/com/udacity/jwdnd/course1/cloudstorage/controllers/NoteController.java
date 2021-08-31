package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService){
        this.noteService = noteService;
        this.userService = userService;
    }


    @PostMapping()
    public String  upsertNote(@ModelAttribute("note") Note note,  Model model, Authentication authentication){
        int id = userService.getUser(authentication.getName()).getUserid();
        note.setUserid(id);
        boolean result = noteService.upsertNote(note) == 1;
        model.addAttribute("result", result ? "success" : "failure");
        return "result";
    }

    @GetMapping("/delete/{noteid}")
    public String deleteCredential(@PathVariable int noteid, Model model){

        int value = noteService.deleteNote(noteid);
        System.out.println("Number of records deleted: " + value);
        model.addAttribute("result", "success");
        return "result";
    }
}


