package pl.marros.music_learner.scale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marros.music_learner.notes.MusicNotes;

@RestController
@RequestMapping("/scale")
public class ScaleController {

    private final ScaleEngine scaleEngine;

    public ScaleController(ScaleEngine scaleEngine) {
        this.scaleEngine = scaleEngine;
    }

    @GetMapping("/major/{name}")
    public ResponseEntity<ScaleDTO> getMajorScale(@PathVariable String name){
        var scale = scaleEngine.createMajor(MusicNotes.findByName(name));
        var dto = new ScaleDTO(scale.getName(), scale.getMode(), scale.getScaleView());
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/minor/{name}")
    public ResponseEntity<ScaleDTO> getMinorScale(@PathVariable String name) {
        var scale = scaleEngine.createAeolian(MusicNotes.findByName(name));
        var dto = new ScaleDTO(scale.getName(), scale.getMode(), scale.getScaleView());
        return ResponseEntity.ok(dto);
    }
}
