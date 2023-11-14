package com.accenture.tigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.accenture.tigital.models.Raffle;
import java.util.Optional;

public interface RaffleRepository extends JpaRepository<Raffle, Long> {
    Optional<Raffle> findByUserUserId(Long userId);
}
