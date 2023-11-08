package com.accenture.tigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.accenture.tigital.models.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findByUserUserId(Long userId);
}
