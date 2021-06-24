package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;
import java.util.*;

@Mapper
public interface NoteMapper {



        @Select("SELECT * FROM NOTES WHERE noteid= #{id}")
        Note getNote(int id);
        @Select("SELECT * FROM NOTES")
        ArrayList<Note> getNotes();
        @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{notetitle}, #{notedescription}, #{userid})")
        @Options(useGeneratedKeys = true, keyProperty = "noteid")
        int insert(Note note);
        @Delete("DELETE FROM NOTES WHERE noteid = #{id}")
        void delete(int id);

}
