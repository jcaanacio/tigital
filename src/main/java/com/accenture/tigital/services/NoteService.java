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
import com.accenture.tigital.libraries.typings.ProfileInput;
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

    public Note create(Long userId, ProfileInput profileInput) {
        User user = userRepository.findById(userId).orElse(null);

        if(user == null) {
            throw new RestException("User not found.", 404, ErrorScope.CLIENT);
        }

        Note note = new Note(user, profileInput.getFirstName(), profileInput.getLastName(), profileInput.getEmail(), profileInput.getPhone());
        return profileRepository.saveAndFlush(profile);
    }


    public Note get(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Long userId, Long noteId) {
        Note note = noteRepository.findByUserUserId(userId).orElse(null);

        if(note == null) {
            throw new RestException("Not not found.", 404, ErrorScope.CLIENT);
        }

        if (note.getNoteId() != noteId) {
            throw new RestException("Not id mismatched.", 404, ErrorScope.CLIENT);
        }

        noteRepository.deleteById(note.getNoteId());
    }

    public Profile update(Long userId, Long profileId, ProfileInput profileInput){
        Profile profile = profileRepository.findByUserUserId(userId).orElse(null);

        if(profile == null) {
            throw new RestException("Profile not found.", 404, ErrorScope.CLIENT);
        }

        if (profile.getProfileId() != profileId) {
            throw new RestException("Profile id mismatched.", 404, ErrorScope.CLIENT);
        }

        updateNonNullFields(profileInput, profile);
        return profileRepository.saveAndFlush(profile);
    }
}
