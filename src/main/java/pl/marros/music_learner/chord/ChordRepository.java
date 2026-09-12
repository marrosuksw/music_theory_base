package pl.marros.music_learner.chord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//TBD - possible repository interface for user-saved chords
@Repository
public interface ChordRepository extends JpaRepository<Chord, Long> {
    @Override
    public Optional<Chord> findById(Long id);
}
