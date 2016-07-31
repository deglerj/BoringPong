package de.jd.bp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PaddleInputHandler {

	private final Paddle paddle;

	public PaddleInputHandler(final Paddle paddle) {
		this.paddle = paddle;
	}

	public void handleInput(final float delta) {
		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			paddle.setY(paddle.getY() + paddle.getSpeed() * delta);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			paddle.setY(paddle.getY() - paddle.getSpeed() * delta);
		}
	}

}
