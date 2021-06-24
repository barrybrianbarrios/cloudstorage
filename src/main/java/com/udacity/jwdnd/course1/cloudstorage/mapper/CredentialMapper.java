package com.udacity.jwdnd.course1.cloudstorage.mapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;
import java.util.*;


@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid= #{id}")
    Credential  getCredential(int id);
    @Select("SELECT * FROM CREDENTIALS where userid=#{id}")
    ArrayList<Credential> getCredentials(int id);
    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insert(Credential credential);
    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{id}")
    void delete(int id);


}
