package pl.marros.music_learner.musictheoryengine;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.marros.music_learner.notes.MusicNotes;
import pl.marros.music_learner.intervals.MusicIntervalsEngine;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MusicIntervalsEngineTest {

    private MusicIntervalsEngine musicIntervalsEngine;


    @BeforeEach
    void setUp(){
        this.musicIntervalsEngine = new MusicIntervalsEngine();
    }

    @ParameterizedTest
    //given
    @CsvSource({
        "C, 4, E",
        "Ab, 12, Ab",
        "Db, 0, Db",
        "F, -12, F",
        "B, -4, G",
        "C, 13, Db",
        "C, 481, Db",
        "A, 23, Ab",
        "B, -23, C",
        "F, -1000, B"    // |(12 + (-1000) + 5)| % 12 = 11
    })
    void shouldShiftSemitoneByGivenAmount(String preShiftNoteName, int shift, String expectedShiftedNoteName){
        //when - semitone shift
        MusicNotes shiftedNote = musicIntervalsEngine.shiftSemitones(MusicNotes.findByName(preShiftNoteName), shift);
        MusicNotes expectedNote = MusicNotes.findByName(expectedShiftedNoteName);
        //then
        assertEquals(shiftedNote, expectedNote);
    }

}
