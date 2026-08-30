package pl.marros.music_learner.intervals;

import lombok.Getter;

@Getter
public enum MusicIntervals {
    //Listed intervals with their most common names and the distance from the root note they represent in semitones
    UNISON(0),
    MINOR_SECOND(1),
    MAJOR_SECOND(2),
    MINOR_THIRD(3),
    MAJOR_THIRD(4),
    PERFECT_FOURTH(5),
    AUG_FOURTH_DIM_FIFTH(6),
    PERFECT_FIFTH(7),
    MINOR_SIXTH(8),
    MAJOR_SIXTH(9),
    MINOR_SEVENTH(10),
    MAJOR_SEVENTH(11),
    PERFECT_OCTAVE(12);


    private final int semitones;

    MusicIntervals(int semitones){
        this.semitones = semitones;
    }

}
