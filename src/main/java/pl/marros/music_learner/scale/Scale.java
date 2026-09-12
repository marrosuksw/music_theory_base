package pl.marros.music_learner.scale;


import lombok.Getter;
import pl.marros.music_learner.intervals.MusicIntervals;
import pl.marros.music_learner.notes.MusicNotes;

import java.util.*;

//think of a better way - using a Getter on a map here feels against the reason I've made this class
// TODO: possibly obsolete
public class Scale {

    //Map of max 7 elems - MusicNote objects
    private final Map<MusicIntervals, MusicNotes> map;
    @Getter
    private final String mode;
    @Getter
    private final String name;

    public Scale(String mode, String name){
        this.mode = mode;
        this.name = name;
        this.map = new EnumMap<>(MusicIntervals.class);
    }
    public void add(MusicIntervals interval, MusicNotes note) {
        if (map.size() == 8) throw new IllegalStateException("Scale::add(): Scale contains the maximum amount of elements.");
        else map.put(interval, note);
    }
    //returns an unmodifiable enum map
    public Map<MusicIntervals, MusicNotes> getScaleView(){
        return Collections.unmodifiableMap(this.map);
    }
    //TODO: FOR TESTING PURPOSES ONLY
    public void printOut(){
        for(var interval : map.keySet()){
            System.out.println(map.get(interval) + ",");
        }
    }
    public int size(){
        return map.size();
    }
}
