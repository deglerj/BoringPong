package de.jd.bp;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PaddleDrawer {

	private final ShapeRenderer shapeRenderer;

	public PaddleDrawer(final ShapeRenderer shapeRenderer) {
		this.shapeRenderer = shapeRenderer;
	}

	public void draw(final Paddle paddle) {
		shapeRenderer.setColor(BoringColors.DARKER_BEIGE);
		shapeRenderer.rect(paddle.getX(), paddle.getY(), Constants.PADDLE_WIDTH, paddle.getHeight());
	}

}
