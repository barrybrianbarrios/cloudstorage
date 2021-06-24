package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;
import java.util.*;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE fileId= #{id}")
    File getFile(int id);
    @Select("SELECT * FROM FILES where userid=#{id}")
    ArrayList<File> getFiles(int id);
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);
    @Delete("DELETE FROM FILES WHERE fileId = #{id}")
    void delete(int id);
}