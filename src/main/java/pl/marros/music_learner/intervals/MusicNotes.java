package pl.marros.music_learner.intervals;

import lombok.Getter;

import java.util.EnumSet;

// replacement for NoteCollection class !!!
@Getter
public enum MusicNotes {

    C ("C" ,"No-alt", 0),
    Db ("Db", "C#", 1),
    D ("D","No-alt", 2),
    Eb ("Eb", "D#", 3),
    E ("E", "No-alt", 4),
    F ("F", "No-alt", 5),
    Gb("Gb", "F#", 6),
    G ("G", "No-alt", 7),
    Ab ("Ab", "G#", 8),
    A ("A", "No-alt", 9),
    Bb ("Bb","A#", 10),
    B ("B","Cb", 11);

    private final String name;
    private final String altName;
    private final int order;

    MusicNotes(String name, String altName, int order) {
        this.name = name;
        this.altName = altName;
        this.order = order;
    }
    public boolean containsName(String name){
        return name != null && (name.equalsIgnoreCase(this.getName()) || name.equalsIgnoreCase(this.getAltName()));
    }
    // Cache it later for more optimized search
    public static MusicNotes findByName(String name){
         return EnumSet.allOf(MusicNotes.class)
                 .stream()
                 .filter(musicNotes -> musicNotes.containsName(name))
                 .findFirst()
                 .orElseThrow();
    }
    public static MusicNotes findByOrder(double order){
        return EnumSet.allOf(MusicNotes.class)
                .stream()
                .filter(musicNotes -> musicNotes.getOrder() == order)
                .findFirst()
                .orElseThrow();
    }
}
