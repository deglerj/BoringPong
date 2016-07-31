package de.jd.bp;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BoringPongGame extends Game {

	@Override
	public void create() {
		final Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.2f);
		music.play();

		setScreen(new GameScreen());
	}
}
