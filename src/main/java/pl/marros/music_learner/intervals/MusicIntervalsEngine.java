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

    private static final int MAX_SEMITONE_SHIFT_RIGHT = 13;
    private static final int MAX_SEMITONE_SHIFT_LEFT = -13;


    public MusicNotes shiftSemitones(MusicNotes note, int shift){
        if (shift < MAX_SEMITONE_SHIFT_LEFT || shift > MAX_SEMITONE_SHIFT_RIGHT)
            throw new IllegalArgumentException("Unexpected shift arg: Note shift cannot be greater than 11 and lower than -11. Provided: " + shift);
        final var moduloAmount = 12;
        MusicNotes musicNotes = MusicNotes.findByName(note.getName()); // note provided by user
        var shiftedNoteOrder = (moduloAmount + shift + musicNotes.getOrder()) % 12;
        return MusicNotes.findByOrder(shiftedNoteOrder);
    }

    public MusicNotes getIntervalNote(MusicNotes note, MusicIntervals interval) {
        if(interval==null){
            throw new IllegalArgumentException("Interval cannot be null for MusicTheoryEngine::getIntervalNote()");
        }
        return shiftSemitones(note, interval.getSemitones());
    }
    // Think about implementing getters for each interval: might be boilerplate as the method is constructed in such a way that each call will be descriptive

}
