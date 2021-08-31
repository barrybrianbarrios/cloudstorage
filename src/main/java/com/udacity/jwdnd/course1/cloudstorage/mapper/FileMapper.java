package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;
import java.util.*;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE fileId= #{id}")
    File getFile(int id);

    @Select("SELECT * FROM FILES where userid=#{id}")
    ArrayList<File> getFiles(int id);

    /*@Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);*/

    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    File getFileByFileName(String filename);

    @Delete("DELETE FROM FILES WHERE fileId = #{id}")
    int delete(int id);

    @Insert("<script>MERGE INTO FILES KEY (fileId) VALUES (" +
            "   <choose> <when test='#{fileId} != null'> #{fileId} </when> <otherwise> default </otherwise>" +
            "</choose>, #{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})</script>"
    )
        //@Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int upsert(File file);
}