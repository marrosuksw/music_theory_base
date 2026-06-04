package pl.marros.music_learner.chord;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="Chord")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//
public class Chord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name="type")
    private String type;}
