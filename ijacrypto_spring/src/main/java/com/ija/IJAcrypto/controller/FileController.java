package com.ija.IJAcrypto.controller;

import com.ija.IJAcrypto.exception.DecryptException;
import com.ija.IJAcrypto.exception.EncryptException;
import com.ija.IJAcrypto.response.FileResponse;
import com.ija.IJAcrypto.response.FilesResponse;
import com.ija.IJAcrypto.service.DecryptService;
import com.ija.IJAcrypto.service.EncryptService;
import com.ija.IJAcrypto.service.GetFilesService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    private EncryptService encryptService;

    private DecryptService decryptService;

    private GetFilesService getFilesService;

    public FileController(EncryptService encryptService, DecryptService decryptService, GetFilesService getFilesService) {
        this.encryptService = encryptService;
        this.decryptService = decryptService;
        this.getFilesService = getFilesService;
    }

    @GetMapping("/files")
    @ResponseBody
    public FilesResponse getFiles() {
        return getFilesService.getFiles();
    }

    @PostMapping("/files")
    @ResponseBody
    public FileResponse encryptAndUpload(
            @RequestParam("file") MultipartFile file
    ) throws EncryptException {
        return new FileResponse(this.encryptService.encrypt(file));
    }

    @GetMapping("/files/{id}/decrypt")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> decryptAndDownload(
            @PathVariable("id") long id
    ) throws DecryptException {
        byte[] fileByte = this.decryptService.decrypt(id);

        ByteArrayResource resource = new ByteArrayResource(fileByte);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
