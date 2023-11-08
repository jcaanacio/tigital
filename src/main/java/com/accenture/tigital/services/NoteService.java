package com.accenture.tigital.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.tigital.libraries.enums.ErrorScope;
import com.accenture.tigital.libraries.exceptions.RestException;
import com.accenture.tigital.libraries.typings.NoteInput;
import com.accenture.tigital.models.Profile;
import com.accenture.tigital.models.Note;
import com.accenture.tigital.repositories.ProfileRepository;
import com.accenture.tigital.repositories.UserRepository;

@Service
public class NoteService extends AbstractService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> list() {
        return noteRepository.findAll();
    }

    public Page<Note> paginated(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return noteRepository.findAll(pageable);
    }

    public Note create(Long userId, NoteInput noteInput) {
        User user = userRepository.findById(userId).orElse(null);

        if(user == null) {
            throw new RestException("User not found.", 404, ErrorScope.CLIENT);
        }

        Note note = new Note(user, noteInput.getSubject(), noteInput.getContent());
        return noteRepository.saveAndFlush(note);
    }


    public Note getByUserId(Long userId) {
        return noteRepository.findByUserUserId(userId).orElse(null);
    }

    public Note update(Long userId, Long noteId, NoteInput noteInput){
        Note note = noteRepository.findByUserUserId(userId).orElse(null);

        if(note == null) {
            throw new RestException("Note not found.", 404, ErrorScope.CLIENT);
        }

        if (note.getNoteId() != noteId) {
            throw new RestException("Note id mismatched.", 404, ErrorScope.CLIENT);
        }

        this.updateNonNullFields(profileInput, profile);
        return noteRepository.saveAndFlush(profile);
    }
}
