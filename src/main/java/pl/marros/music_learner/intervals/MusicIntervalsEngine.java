package pl.marros.music_learner.intervals;


import lombok.NoArgsConstructor;


/*
* Responsible for:
*   - Calculating intervals -> fetch Perfect Fifth based on a given note and interval with getIntervalNote
*   - shiftSemitones base method
*
* */
@NoArgsConstructor
public class MusicIntervalsEngine {

    private static final int MAX_SEMITONE_SHIFT_RIGHT = 11;
    private static final int MAX_SEMITONE_SHIFT_LEFT = -11;

    // Think about refactoring this method to accept a MusicNotes argument instead of a String
    public MusicNotes shiftSemitones(String name, int shift){
        if (shift < MAX_SEMITONE_SHIFT_LEFT || shift > MAX_SEMITONE_SHIFT_RIGHT)
            throw new IllegalArgumentException("Note shift cannot be greater than 11 and lower than -11. Provided: " + shift);
        final var moduloAmount = 12;
        MusicNotes musicNotes = MusicNotes.findByName(name); // note provided by user
        var shiftedNoteOrder = (moduloAmount + shift + musicNotes.getOrder()) % 12;
        return MusicNotes.findByOrder(shiftedNoteOrder);
    }

    public MusicNotes getIntervalNote(String name, MusicIntervals interval) {
        if(interval==null){
            throw new IllegalArgumentException("Interval cannot be null for MusicTheoryEngine::getIntervalNote()");
        }
        return shiftSemitones(name, interval.getSemitones());
    }

}
