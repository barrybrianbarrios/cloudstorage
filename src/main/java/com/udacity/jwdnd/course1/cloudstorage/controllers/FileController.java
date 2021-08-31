package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/file")
public class FileController {
    private FileService fileService;
    private UserService userService;
    private String message ;

    public FileController(FileService fileService, UserService userService){
        this.fileService = fileService;
        this.userService = userService;
    }


    @PostMapping()
    public String  upsertFile(MultipartFile fileUpload, Model model, Authentication authentication){
        int id = userService.getUser(authentication.getName()).getUserid();
        boolean result = false;
        try{
            if (fileUpload.isEmpty()) {
                message="Please select a file!" ;
                throw new IOException("File Not FoundException");
            }

            if (fileUpload.getSize() > 500000000) {
                message = "File size too large";
                model.addAttribute("fileSizeError","File size larger than size limit");
                throw new IOException("Error: File size larger than size limit");
            }

            if (this.fileService.getFileByFileName(fileUpload.getOriginalFilename()) != null) {
                message = fileUpload.getOriginalFilename() + " exists in the database!!! Please upload another file!";
                throw new IOException("File name exists");
            }


            // Converting MultipartFile this projects File
            File file1 = new File();
            file1.setFilename(fileUpload.getOriginalFilename());
            file1.setContenttype(fileUpload.getContentType());
            file1.setFilesize(fileUpload.getSize());
            file1.setFiledata(fileUpload.getBytes());
            file1.setUserid(id);
            result = fileService.upsertFile(file1) == 1;
            message = "You successfully uploaded '" + file1.getFilename() + "' !";

        } catch (IOException e){


        } catch (Exception e){
            System.out.println(e);
        }

        model.addAttribute("result", result ? "success" : "failure");
        return "result";
    }



    @RequestMapping(path = "/view/{fileId}")
    public ResponseEntity serveFile(Authentication authentication, @PathVariable int fileId, Model model) throws SQLException {


        Integer userId = userService.getUser(authentication.getName()).getUserid();
        model.addAttribute("result", "success");
        File file = fileService.getFile(fileId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getContenttype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file.getFiledata());
    }
    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable int fileId, Model model){

        int value = fileService.deleteFile(fileId);
        System.out.println("Number of records deleted: " + value);
        model.addAttribute("result", "success");
        return "result";
    }

}
