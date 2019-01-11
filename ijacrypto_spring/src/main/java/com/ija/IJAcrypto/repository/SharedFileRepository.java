package com.ija.IJAcrypto.repository;

import com.ija.IJAcrypto.model.SharedFile;
import com.ija.IJAcrypto.model.SharedFileId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SharedFileRepository extends CrudRepository<SharedFile, SharedFileId> {
    public List<SharedFile> findByIdUserId(long userId);

    public List<SharedFile> findByIdEncryptedFileId(long fileId);

    public void deleteByIdEncryptedFileIdAndIdUserId(long fileId, long userId);
}
