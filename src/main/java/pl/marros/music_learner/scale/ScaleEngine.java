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
@RequiredArgsConstructor
public class ScaleEngine {
    //Responsible for methods that create scales of different modes

    //TODO: must implement caching of looked up scales, set a fair timer etc.

    // Arrays of specific intervals for each scale mode, used for scale creation
    private final List<MusicIntervals> majorScaleRecipe = new ArrayList<>(List.of(MusicIntervals.UNISON, MusicIntervals.MAJOR_THIRD,
            MusicIntervals.MAJOR_SECOND, MusicIntervals.PERFECT_FOURTH, MusicIntervals.PERFECT_FIFTH, MusicIntervals.MAJOR_SIXTH,
            MusicIntervals.MAJOR_SEVENTH, MusicIntervals.PERFECT_OCTAVE));
    private final List<MusicIntervals> minorScaleRecipe = new ArrayList<>(List.of(MusicIntervals.UNISON, MusicIntervals.PERFECT_OCTAVE,
            MusicIntervals.MAJOR_SECOND, MusicIntervals.MINOR_THIRD, MusicIntervals.PERFECT_FOURTH, MusicIntervals.PERFECT_FIFTH,
            MusicIntervals.MINOR_SIXTH, MusicIntervals.MINOR_SEVENTH));
    private final List<MusicIntervals> dorianModeRecipe = new ArrayList<>(List.of(MusicIntervals.UNISON, MusicIntervals.PERFECT_OCTAVE,
            MusicIntervals.MAJOR_SECOND, MusicIntervals.MINOR_THIRD, MusicIntervals.PERFECT_FOURTH, MusicIntervals.PERFECT_FIFTH,
            MusicIntervals.MAJOR_SIXTH, MusicIntervals.MINOR_SEVENTH));
    private final List<MusicIntervals> phrygianModeRecipe = List.of(new MusicIntervals[]{
            MusicIntervals.UNISON, MusicIntervals.PERFECT_OCTAVE, MusicIntervals.MINOR_SECOND, MusicIntervals.MINOR_THIRD,
            MusicIntervals.PERFECT_FOURTH, MusicIntervals.PERFECT_FIFTH, MusicIntervals.MINOR_SIXTH, MusicIntervals.MINOR_SEVENTH
    });
    private final List<MusicIntervals> lydianModeRecipe = List.of(new MusicIntervals[]{
            MusicIntervals.UNISON, MusicIntervals.MAJOR_THIRD, MusicIntervals.MAJOR_SECOND, MusicIntervals.AUG_FOURTH_DIM_FIFTH,
            MusicIntervals.PERFECT_FIFTH, MusicIntervals.MAJOR_SIXTH, MusicIntervals.MAJOR_SEVENTH, MusicIntervals.PERFECT_OCTAVE
    });
    private final List<MusicIntervals> mixolydianModeRecipe = List.of(new MusicIntervals[]{
            MusicIntervals.UNISON, MusicIntervals.MAJOR_THIRD, MusicIntervals.MAJOR_SECOND, MusicIntervals.PERFECT_FOURTH,
            MusicIntervals.PERFECT_FIFTH, MusicIntervals.MAJOR_SIXTH, MusicIntervals.MINOR_SEVENTH, MusicIntervals.PERFECT_OCTAVE
    });
    private final List<MusicIntervals> locrianModeRecipe = List.of(new MusicIntervals[]{
            MusicIntervals.UNISON, MusicIntervals.PERFECT_OCTAVE, MusicIntervals.MINOR_SECOND, MusicIntervals.MINOR_THIRD,
            MusicIntervals.PERFECT_FOURTH, MusicIntervals.AUG_FOURTH_DIM_FIFTH, MusicIntervals.MINOR_SIXTH, MusicIntervals.MINOR_SEVENTH
    });
    private final MusicIntervalsEngine musicIntervalsEngine;

    public ScaleEngine(){
        this.musicIntervalsEngine = new MusicIntervalsEngine();
    }

    public Scale buildScale(MusicNotes root, Modes mode){
        try {
            List<MusicIntervals> chosenRecipe = switch (mode) {
                case MAJOR -> List.copyOf(majorScaleRecipe);
                case MINOR -> List.copyOf(minorScaleRecipe);
                case DORIAN -> List.copyOf(dorianModeRecipe);
                case PHRYGIAN -> List.copyOf(phrygianModeRecipe);
                case MIXOLYDIAN -> List.copyOf(mixolydianModeRecipe);
                case LYDIAN -> List.copyOf(lydianModeRecipe);
                case LOCRIAN -> List.copyOf(locrianModeRecipe);
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
}
