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
   /* @ConstructorArgs(value = {
            @Arg(column="url",javaType=String.class),
            @Arg(column="username",javaType=String.class),
            @Arg(column="key",javaType=String.class),
            @Arg(column="password",javaType=String.class),
            @Arg(column="userid",javaType=Integer.class)
    })*/
    ArrayList<Credential> getCredentials(int id);

    /*
    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insert(Credential credential);
    */
    /*@Delete("DELETE CREDENTIALS WHERE credentialid = #{id}")
    int delete(Credential credential);
    */

    @Delete("DELETE CREDENTIALS WHERE credentialid = #{id}")
    int delete(int id);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username=#{username}, key=#{key}, password=#{password} where credentialid = #{credentialid}")
    int update(Credential credential);

    @Insert("<script>MERGE INTO CREDENTIALS KEY (credentialid) VALUES (" +
            "   <choose> <when test='#{credentialid} != null'> #{credentialid} </when> <otherwise> default </otherwise>" +
            "</choose>, #{url}, #{username}, #{key}, #{password}, #{userid})</script>"
    )
    //@Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int upsert(Credential credential);


}
