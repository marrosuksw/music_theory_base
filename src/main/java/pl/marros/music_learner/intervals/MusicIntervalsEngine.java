package pl.marros.music_learner.intervals;

import lombok.NoArgsConstructor;
import pl.marros.music_learner.notes.MusicNotes;
/*
* Responsible for:
*   - Calculating intervals -> fetch Perfect Fifth based on a given note and interval with getIntervalNote
*   - shiftSemitones base method
*
* */
@NoArgsConstructor
public class MusicIntervalsEngine {

    public MusicNotes shiftSemitones(MusicNotes note, int shift){

        final var moduloAmount = 12;
        var musicNotes = MusicNotes.findByName(note.getName()); // note provided by the user
        var shiftedNoteOrder = Math.abs(moduloAmount + shift + musicNotes.getOrder()) % moduloAmount; // shifted note order, where order of C = 0, D = 1, E = 2, etc.
        return MusicNotes.findByOrder(shiftedNoteOrder);
    }
    public MusicNotes getIntervalNote(MusicNotes note, MusicIntervals interval) {
        if(interval==null){
            throw new IllegalArgumentException("Interval cannot be null for MusicTheoryEngine::getIntervalNote()");
        }
        return shiftSemitones(note, interval.getSemitones());
    }
}
