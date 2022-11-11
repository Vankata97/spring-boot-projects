package codeacademy.loanrequest.service;


import static codeacademy.loanrequest.utils.ExceptionMessages.FILE_TOO_LARGE;
import static codeacademy.loanrequest.utils.ExceptionMessages.PDF_UPLOAD;

import codeacademy.loanrequest.dao.file.FileDao;
import codeacademy.loanrequest.exceptions.TeamOneException;
import codeacademy.loanrequest.model.File;
import java.io.IOException;
import java.util.Objects;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService
{

private final FileDao fileDao;

  public FileService(FileDao fileDao)
  {
    this.fileDao = fileDao;
  }


  public void save(MultipartFile file, int id) throws IOException
  {

      if(file.getSize()>10000760){
        throw new TeamOneException(FILE_TOO_LARGE);
      }
      String test = String.valueOf(file.getOriginalFilename());
      if (FilenameUtils
          .getExtension(test)
          .equals("pdf")) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));


        File File = new File(id,fileName, file.getContentType(), file.getBytes());
        fileDao.save(File);

      }
      else {
        throw new TeamOneException(PDF_UPLOAD);
      }
    }



}
