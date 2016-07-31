package de.jd.bp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

	public static final Sound	bounce	= Gdx.audio.newSound(Gdx.files.internal("bounce.wav"));

	public static final Sound	fail	= Gdx.audio.newSound(Gdx.files.internal("fail.wav"));

	public static final Sound	win		= Gdx.audio.newSound(Gdx.files.internal("win.wav"));
}
