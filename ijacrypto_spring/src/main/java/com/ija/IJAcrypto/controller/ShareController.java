package com.ija.IJAcrypto.controller;

import com.ija.IJAcrypto.exception.ShareFileException;
import com.ija.IJAcrypto.request.ShareFileRequest;
import com.ija.IJAcrypto.response.EmptyResponse;
import com.ija.IJAcrypto.response.SharedFileUserResponse;
import com.ija.IJAcrypto.service.FileShareService;
import com.ija.IJAcrypto.service.GetUsersOfFileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShareController {
    private FileShareService shareFileService;

    private GetUsersOfFileService getUsersOfFileService;

    public ShareController(FileShareService shareFileService, GetUsersOfFileService getUsersOfFileService) {
        this.shareFileService = shareFileService;
        this.getUsersOfFileService = getUsersOfFileService;
    }

    @PostMapping("/files/{fileId}/share/{userId}")
    public EmptyResponse share(
            @PathVariable("fileId") Long fileId,
            @PathVariable("userId") Long userId
    ) throws ShareFileException {
        ShareFileRequest request = new ShareFileRequest(fileId, userId);
        shareFileService.share(request);
        return new EmptyResponse();
    }

    @DeleteMapping("/files/{fileId}/share/{userId}")
    public EmptyResponse revokeShare(
            @PathVariable("fileId") Long fileId,
            @PathVariable("userId") Long userId
    ) throws ShareFileException {
        ShareFileRequest shareFileRequest = new ShareFileRequest(fileId, userId);
        shareFileService.revoke(shareFileRequest);
        return new EmptyResponse();
    }

    @GetMapping("/files/{id}/users")
    @ResponseBody
    public List<SharedFileUserResponse> getUsersOfFile(@PathVariable("id") Long fileId) {
        return getUsersOfFileService.handle(fileId);
    }
}
