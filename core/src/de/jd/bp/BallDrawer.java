package de.jd.bp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BallDrawer {

	private static boolean		DRAW_HITBOX	= false;

	private final ShapeRenderer	shapeRenderer;

	public BallDrawer(final ShapeRenderer shapeRenderer) {
		this.shapeRenderer = shapeRenderer;
	}

	public void draw(final Ball ball) {
		if (DRAW_HITBOX) {
			shapeRenderer.setColor(Color.PINK);
			shapeRenderer.rect(ball.getX() - Constants.BALL_RADIUS, ball.getY() - Constants.BALL_RADIUS, Constants.BALL_RADIUS * 2,
					Constants.BALL_RADIUS * 2);
		}

		shapeRenderer.setColor(BoringColors.DARKEST_BEIGE);
		shapeRenderer.circle(ball.getX(), ball.getY(), Constants.BALL_RADIUS);
	}

}
