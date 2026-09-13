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
    // TODO: reinvestigate if the semitone limit is needed - it could be unlimited, with modulo operations the notes would behave like in a circle
    private static final int MAX_SEMITONE_SHIFT_RIGHT = 12;
    private static final int MAX_SEMITONE_SHIFT_LEFT = -12;

    public MusicNotes shiftSemitones(MusicNotes note, int shift){
        if (shift < MAX_SEMITONE_SHIFT_LEFT || shift > MAX_SEMITONE_SHIFT_RIGHT)
            throw new IllegalArgumentException("Unexpected shift arg: Note shift cannot be greater than 12 and lower than -12. Provided: " + shift);
        final var moduloAmount = 12;
        var musicNotes = MusicNotes.findByName(note.getName()); // note provided by user
        var shiftedNoteOrder = (moduloAmount + shift + musicNotes.getOrder()) % 12; // shifted note order, where order of C = 0, D = 1, E = 2, etc.
        return MusicNotes.findByOrder(shiftedNoteOrder);
    }
    public MusicNotes getIntervalNote(MusicNotes note, MusicIntervals interval) {
        if(interval==null){
            throw new IllegalArgumentException("Interval cannot be null for MusicTheoryEngine::getIntervalNote()");
        }
        return shiftSemitones(note, interval.getSemitones());
    }
}
