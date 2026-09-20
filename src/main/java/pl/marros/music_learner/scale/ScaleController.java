package pl.marros.music_learner.scale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marros.music_learner.notes.MusicNotes;
import pl.marros.music_learner.scale.mode.Modes;

@RestController
@RequestMapping("/scale")
public class ScaleController {

    private final ScaleEngine scaleEngine;

    public ScaleController(ScaleEngine scaleEngine) {
        this.scaleEngine = scaleEngine;
    }
    @GetMapping("/{mode}/{name}")
    public ResponseEntity<ScaleDTO> getScale(@PathVariable String mode, @PathVariable String name){
        var scale = scaleEngine.buildScale(MusicNotes.findByName(name), Modes.valueOf(mode.toUpperCase()));
        return ResponseEntity.ok(new ScaleDTO(scale.getName(), scale.getMode(), scale.getScaleView()));
    }
}
