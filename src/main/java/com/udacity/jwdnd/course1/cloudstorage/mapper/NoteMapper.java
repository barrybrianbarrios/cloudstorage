package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;
import java.util.*;

@Mapper
public interface NoteMapper {



        @Select("SELECT * FROM NOTES WHERE noteid= #{id}")
        Note getNote(int id);

        @Select("SELECT * FROM NOTES where userid=#{id}")
        ArrayList<Note> getNotes(int id);

        /*
        @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{notetitle}, #{notedescription}, #{userid})")
        @Options(useGeneratedKeys = true, keyProperty = "noteid")
        int insert(Note note);
        */

        @Delete("DELETE FROM NOTES WHERE noteid = #{id}")
        int delete(int id);

        @Insert("<script>MERGE INTO NOTES KEY (noteid) VALUES (" +
                "   <choose> <when test='#{noteid} != null'> #{noteid} </when> <otherwise> default </otherwise>" +
                "</choose>, #{notetitle}, #{notedescription}, #{userid})</script>"
        )
                //@Options(useGeneratedKeys = true, keyProperty = "credentialid")
        int upsert(Note note);

}
