package pl.marros.music_learner;

import org.springframework.boot.SpringApplication;

public class TestMusicLearnerApplication {

	 static void main(String[] args) {
		SpringApplication.from(MusicLearnerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
