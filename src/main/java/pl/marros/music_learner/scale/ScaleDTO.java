package pl.marros.music_learner.scale;


import pl.marros.music_learner.intervals.MusicIntervals;
import pl.marros.music_learner.notes.MusicNotes;

import java.util.Map;

public record ScaleDTO(
        String name,
        String mode,
        Map<MusicIntervals, MusicNotes> notesMap
) {
}
