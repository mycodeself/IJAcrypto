package com.ija.IJAcrypto.response;

import com.ija.IJAcrypto.model.EncryptedFile;
import com.ija.IJAcrypto.model.SharedFile;

import java.util.List;
import java.util.stream.Collectors;

public class FilesResponse {

    private List<FileResponse> filesOwn;

    private List<FileResponse> filesShared;


    public FilesResponse(List<EncryptedFile> filesOwn, List<SharedFile> filesShared) {
        this.filesOwn = filesOwn.stream()
                .map(FileResponse::new)
                .collect(Collectors.toList());

        this.filesShared = filesShared.stream()
                .map(fileShared -> new FileResponse(fileShared.getFile()))
                .collect(Collectors.toList());
    }

    public List<FileResponse> getFilesOwn() {
        return filesOwn;
    }

    public List<FileResponse> getFilesShared() {
        return filesShared;
    }
}
