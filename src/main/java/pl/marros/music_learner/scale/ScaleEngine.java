package pl.marros.music_learner.scale;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.marros.music_learner.intervals.MusicIntervals;
import pl.marros.music_learner.intervals.MusicIntervalsEngine;
import pl.marros.music_learner.notes.MusicNotes;
import pl.marros.music_learner.scale.mode.Modes;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;



@Component
public class ScaleEngine {
    //Responsible for methods that create scales of different modes
    //TODO: must implement caching of looked up scales, set a fair timer etc.
    private MusicIntervalsEngine musicIntervalsEngine;

    public ScaleEngine(){
        this.musicIntervalsEngine = new MusicIntervalsEngine();
    }
    public Scale buildScale(MusicNotes root, Modes mode){
        try {
            List<MusicIntervals> chosenRecipe = switch (mode) {
                //interval recipe for major scale creation
                case MAJOR -> List.of(
                        MusicIntervals.UNISON, MusicIntervals.MAJOR_THIRD, MusicIntervals.MAJOR_SECOND, MusicIntervals.PERFECT_FOURTH,
                        MusicIntervals.PERFECT_FIFTH, MusicIntervals.MAJOR_SIXTH, MusicIntervals.MAJOR_SEVENTH, MusicIntervals.PERFECT_OCTAVE);
                //interval recipe for minor scale creation
                case MINOR -> List.of(MusicIntervals.UNISON, MusicIntervals.PERFECT_OCTAVE,
                        MusicIntervals.MAJOR_SECOND, MusicIntervals.MINOR_THIRD, MusicIntervals.PERFECT_FOURTH,
                        MusicIntervals.PERFECT_FIFTH, MusicIntervals.MINOR_SIXTH, MusicIntervals.MINOR_SEVENTH);
                //interval recipe for dorian mode scale creation
                case DORIAN -> List.of(
                        MusicIntervals.UNISON, MusicIntervals.PERFECT_OCTAVE, MusicIntervals.MAJOR_SECOND, MusicIntervals.MINOR_THIRD,
                        MusicIntervals.PERFECT_FOURTH, MusicIntervals.PERFECT_FIFTH, MusicIntervals.MAJOR_SIXTH, MusicIntervals.MINOR_SEVENTH);
                //interval recipe for phrygian mode scale creation
                case PHRYGIAN -> List.of(
                        MusicIntervals.UNISON, MusicIntervals.PERFECT_OCTAVE, MusicIntervals.MINOR_SECOND, MusicIntervals.MINOR_THIRD,
                        MusicIntervals.PERFECT_FOURTH, MusicIntervals.PERFECT_FIFTH, MusicIntervals.MINOR_SIXTH, MusicIntervals.MINOR_SEVENTH
                );
                //interval recipe for mixolydian mode scale creation
                case MIXOLYDIAN -> List.of(
                        MusicIntervals.UNISON, MusicIntervals.MAJOR_THIRD, MusicIntervals.MAJOR_SECOND, MusicIntervals.PERFECT_FOURTH,
                        MusicIntervals.PERFECT_FIFTH, MusicIntervals.MAJOR_SIXTH, MusicIntervals.MINOR_SEVENTH, MusicIntervals.PERFECT_OCTAVE
                );
                //interval recipe for lydian mode scale creation
                case LYDIAN -> List.of(
                        MusicIntervals.UNISON, MusicIntervals.MAJOR_THIRD, MusicIntervals.MAJOR_SECOND, MusicIntervals.AUG_FOURTH_DIM_FIFTH,
                        MusicIntervals.PERFECT_FIFTH, MusicIntervals.MAJOR_SIXTH, MusicIntervals.MAJOR_SEVENTH, MusicIntervals.PERFECT_OCTAVE
                );
                //interval recipe for locrian mode scale creation
                case LOCRIAN -> List.of(
                        MusicIntervals.UNISON, MusicIntervals.PERFECT_OCTAVE, MusicIntervals.MINOR_SECOND, MusicIntervals.MINOR_THIRD,
                        MusicIntervals.PERFECT_FOURTH, MusicIntervals.AUG_FOURTH_DIM_FIFTH, MusicIntervals.MINOR_SIXTH, MusicIntervals.MINOR_SEVENTH
                );
                case null -> throw new IllegalArgumentException("ScaleEngine::createScale failure: Mode is null");
            };
            var scale = new Scale(mode.toString(), root.getName());
            for (var interval : chosenRecipe) {
                scale.add(interval, musicIntervalsEngine.getIntervalNote(root, interval));
            }
            return scale;
        }
        catch (NoSuchElementException e){
            throw new IllegalArgumentException("ScaleEngine::createScale() failure: root note doesn't exist.", e);
        }
    }
    //TODO: Try to rethink what approach is best for scale queries
    // -> Fix the scale naming issue - it would look much better if (as it is IRL) there can only be one
    //   Note of a symbol per scale, i.e. there cannot be a A & Ab note in one scale, rather A & G#, etc.
    // FIX IDEA - pass both alt name and name (where alt name is present) and display both in the circle of fifths
    //
}
