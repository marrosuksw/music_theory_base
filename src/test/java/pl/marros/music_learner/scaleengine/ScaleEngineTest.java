package pl.marros.music_learner.scaleengine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.marros.music_learner.notes.MusicNotes;
import pl.marros.music_learner.scale.Scale;
import pl.marros.music_learner.scale.ScaleEngine;
import pl.marros.music_learner.scale.mode.Modes;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class ScaleEngineTest {

    private ScaleEngine scaleEngine;

    @BeforeEach
    void setUp(){
        this.scaleEngine = new ScaleEngine();
    }
    @MethodSource
    static Stream<Arguments> generateModeTestData(){
        return Stream.of(
                Arguments.of(Modes.MAJOR, MusicNotes.C, List.of(MusicNotes.C, MusicNotes.D, MusicNotes.E,
                        MusicNotes.F, MusicNotes.G, MusicNotes.A, MusicNotes.B, MusicNotes.C)),
                Arguments.of(Modes.MINOR, MusicNotes.A, List.of(MusicNotes.A, MusicNotes.B, MusicNotes.C, MusicNotes.D,
                        MusicNotes.E, MusicNotes.F, MusicNotes.G, MusicNotes.A)),
                Arguments.of(Modes.DORIAN, MusicNotes.E, List.of(MusicNotes.E, MusicNotes.Gb, MusicNotes.G, MusicNotes.A,
                        MusicNotes.B, MusicNotes.Db, MusicNotes.D, MusicNotes.E)),
                Arguments.of(Modes.LYDIAN, MusicNotes.F, List.of(MusicNotes.F, MusicNotes.G, MusicNotes.A, MusicNotes.B,
                        MusicNotes.C, MusicNotes.D, MusicNotes.E, MusicNotes.F)),
                Arguments.of(Modes.LOCRIAN, MusicNotes.Ab, List.of(MusicNotes.Ab, MusicNotes.A, MusicNotes.B, MusicNotes.Db,
                        MusicNotes.D, MusicNotes.E, MusicNotes.Gb, MusicNotes.Ab)),
                Arguments.of(Modes.PHRYGIAN, MusicNotes.Bb, List.of(MusicNotes.Bb, MusicNotes.B, MusicNotes.Db, MusicNotes.Eb,
                        MusicNotes.F, MusicNotes.Gb, MusicNotes.Ab, MusicNotes.Bb)),
                Arguments.of(Modes.MIXOLYDIAN, MusicNotes.Eb, List.of(MusicNotes.Eb, MusicNotes.F, MusicNotes.G, MusicNotes.Ab,
                        MusicNotes.Bb, MusicNotes.C, MusicNotes.Db, MusicNotes.Eb))
        );
    }
    @ParameterizedTest
    //given
    @MethodSource("generateModeTestData")
    void shouldBuildScaleOfGivenMode(Modes mode, MusicNotes note, List<String> expectedScale){
        //when
        var actualScale = scaleEngine.buildScale(note, mode);
        //then
        assertIterableEquals(expectedScale, actualScale.getScaleView().values());
    }
}
