package codeacademy.loanrequest.controller;

import codeacademy.loanrequest.service.FileService;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/svc")
public class FileController
{

  private final FileService fileService;

  public FileController(FileService fileService)
  {
    this.fileService = fileService;
  }


  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  @PostMapping("/upload")
  @ResponseStatus(HttpStatus.OK)
  public void uploadFile(@Valid @RequestParam("file") MultipartFile file, @RequestParam("id") int id) throws IOException
  {
    fileService.save(file, id);
  }

}
