package de.jd.bp;

public class Ai {

	public void movePaddle(final Paddle paddle, final Ball ball, final float delta) {
		float targetY = paddle.getY();

		final float safetyMargin = paddle.getHeight() * 0.25f;

		// Need to move up?
		if (paddle.getY() + paddle.getHeight() < ball.getY() + safetyMargin) {
			targetY = paddle.getY() + paddle.getSpeed() * delta;
		}
		// Need to move down?
		else if (paddle.getY() > ball.getY() - safetyMargin) {
			targetY = paddle.getY() - paddle.getSpeed() * delta;
		}

		paddle.setY(targetY);

	}

}
