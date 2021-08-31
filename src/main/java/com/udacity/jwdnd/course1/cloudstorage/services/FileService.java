package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public File getFile(int userId){return fileMapper.getFile(userId);}

    public ArrayList<File> getFiles(int fileId){

        return fileMapper.getFiles(fileId);

    }

    public File getFileByFileName(String fileName){
        return fileMapper.getFileByFileName(fileName);
    }

    public int deleteFile(int fileid){
        return fileMapper.delete(fileid);
    }

    public int upsertFile(File file){

        return fileMapper.upsert(file);
    }


}
