package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService( NoteMapper noteMapper){
        this.noteMapper = noteMapper;
    }

    public Note getNote(int userId){
        return noteMapper.getNote(userId);
    }

    public ArrayList<Note> getNotes(int noteid){
        return noteMapper.getNotes(noteid);
    }

    public int deleteNote(int noteid){
        return noteMapper.delete(noteid);
    }

    public int upsertNote(Note note){

        return noteMapper.upsert(note);
    }

}
