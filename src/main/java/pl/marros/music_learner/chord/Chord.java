package pl.marros.music_learner.chord;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="Chord")
// TBD - possible entity for chords saved by the user in a Notepad view
public class Chord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name="type")
    private String type;}
