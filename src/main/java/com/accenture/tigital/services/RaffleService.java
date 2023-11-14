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
import com.accenture.tigital.libraries.typings.RaffleInput;
import com.accenture.tigital.models.Profile;
import com.accenture.tigital.models.Raffle;
import com.accenture.tigital.models.User;
import com.accenture.tigital.repositories.UserRepository;

@Service
public class RaffleSerice extends AbstractService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RaffleRepository raffleRepository;

    public List<Raffle> list() {
        return raffleRepository.findAll();
    }

    public Page<Raffle> paginated(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return raffleRepository.findAll(pageable);
    }

    public Raffle create(Long userId, RaffleInput raffleInput) {
        User user = userRepository.findById(userId).orElse(null);

        if(user == null) {
            throw new RestException("User not found.", 404, ErrorScope.CLIENT);
        }

        Raffle raffle = new Raffle(user, raffleInput.getCategory());
        return raffleRepository.saveAndFlush(note);
    }


    public Raffle getByUserId(Long userId) {
        return raffleRepository.findByUserUserId(userId).orElse(null);
    }

    public Raffle update(Long userId, Long raffleId, RaffleInput raffleInput){
        Raffle raffle = raffleRepository.findByUserUserId(userId).orElse(null);

        if(raffle == null) {
            throw new RestException("Note not found.", 404, ErrorScope.CLIENT);
        }

        if(raffle.getRaffleId() != raffleId) {
            throw new RestException("Raffle id mismatched.", 404, ErrorScope.CLIENT);
        }

        this.updateNonNullFields(raffleInput, note);
        return raffleRepository.saveAndFlush(note);
    }

    public void delete(Long raffleId) {
        raffleRepository.deleteById(raffleId);
    }
}
