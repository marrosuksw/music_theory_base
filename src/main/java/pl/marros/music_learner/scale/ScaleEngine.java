package pl.marros.music_learner.scale;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.marros.music_learner.intervals.MusicIntervals;
import pl.marros.music_learner.intervals.MusicIntervalsEngine;
import pl.marros.music_learner.notes.MusicNotes;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class ScaleEngine {
    //Responsible for methods that create scales of different modes

    // Arrays of specific intervals for each scale mode, used for scale creation
    private final MusicIntervals[] majorScaleRecipe = new MusicIntervals[] {
                    MusicIntervals.UNISON, MusicIntervals.MAJOR_THIRD, MusicIntervals.MAJOR_SECOND, MusicIntervals.PERFECT_FOURTH,
                    MusicIntervals.PERFECT_FIFTH, MusicIntervals.MAJOR_SIXTH, MusicIntervals.MAJOR_SEVENTH, MusicIntervals.PERFECT_OCTAVE
                };
    private final MusicIntervals[] minorScaleRecipe = new MusicIntervals[]{
                    MusicIntervals.UNISON, MusicIntervals.PERFECT_OCTAVE, MusicIntervals.MAJOR_SECOND, MusicIntervals.MINOR_THIRD,
                    MusicIntervals.PERFECT_FOURTH, MusicIntervals.PERFECT_FIFTH, MusicIntervals.MINOR_SIXTH, MusicIntervals.MINOR_SEVENTH
                };
    private final MusicIntervalsEngine musicIntervalsEngine;

    public ScaleEngine(){
        this.musicIntervalsEngine = new MusicIntervalsEngine();
    }
    //Major scale creation method (Ionian)
    public Scale createMajor(MusicNotes root){
        try {
            var mode = "major";
            var majorScale = new Scale(mode, root.getName());
            for (var interval : majorScaleRecipe) {
                majorScale.add(interval, musicIntervalsEngine.getIntervalNote(root, interval));
            }
            return majorScale;
        }
        catch (NoSuchElementException e){
            throw new IllegalArgumentException("ScaleEngine::createMajor() failure: root note doesn't exist.", e);
        }
    }
    //(Aeolian) minor scale creation method
    public Scale createAeolian(MusicNotes root){
        try {
            var mode="minor";
            var minorScale = new Scale(mode, root.getName());
            for(MusicIntervals interval : minorScaleRecipe){
                minorScale.add(interval, musicIntervalsEngine.getIntervalNote(root, interval));
            }
            return minorScale;
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("ScaleEngine::createMinor() failure: root note doesn't exist.", e);
        }
    }
    //(Dorian mode)
    public void createDorian(MusicNotes root){
        ;
    }
    //TODO: Try to rethink what approach is best for scale queries
    // -> Fix the scale naming issue - it would look much better if (as it is IRL) there can only be one
    //   Note of a symbol per scale, i.e. there cannot be a A & Ab note in one scale, rather A & G#, etc.
}
