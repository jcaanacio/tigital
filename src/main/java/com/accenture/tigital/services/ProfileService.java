package com.accenture.tigital.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.accenture.tigital.libraries.enums.ErrorScope;
import com.accenture.tigital.libraries.exceptions.RestException;
import com.accenture.tigital.libraries.typings.ProfileInput;
import com.accenture.tigital.models.Profile;
import com.accenture.tigital.models.User;
import com.accenture.tigital.repositories.ProfileRepository;
import com.accenture.tigital.repositories.UserRepository;

@Service
public class ProfileService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> list() {
        return profileRepository.findAll();
    }

    public Page<Profile> paginated(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return profileRepository.findAll(pageable);
    }

    public Profile create(Long userId, ProfileInput profileInput) {
        User user = userRepository.findById(userId).orElse(null);

        if(user == null) {
            throw new RestException("User not found.", 404, ErrorScope.CLIENT);
        }

        Optional<Profile> existingProfile = profileRepository.findByUserUserId(userId);

        if(existingProfile.isPresent()) {
            throw new RestException("Profile is already registered using userId.", 404, ErrorScope.CLIENT);
        }

        Profile profile = new Profile(user, profileInput.getFirstName(), profileInput.getLastName(), profileInput.getEmail(), profileInput.getPhone());
        return profileRepository.saveAndFlush(profile);
    }


    public Profile get(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        profileRepository.deleteById(id);
    }
}
