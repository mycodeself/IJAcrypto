package com.ija.IJAcrypto.request;

public class ShareFileRequest {
    private long fileId;

    private long userId;

    public ShareFileRequest() {
    }

    public ShareFileRequest(long fileId, long userId) {
        this.fileId = fileId;
        this.userId = userId;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
