package com.ija.IJAcrypto.repository;

import com.ija.IJAcrypto.model.EncryptedFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EncryptedFileRepository extends CrudRepository<EncryptedFile, Long> {
    List<EncryptedFile> findByOwnerId(long ownerId);
}
