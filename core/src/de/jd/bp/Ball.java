package de.jd.bp;

import com.badlogic.gdx.math.MathUtils;

public class Ball {

	private float	angle;

	private float	speed;

	private float	x;

	private float	y;

	public Ball() {
		reset(Constants.WORLD_WIDTH / 2f, Constants.BOARD_HEIGHT / 2f, 90f);
	}

	public void collide(final boolean xCollide, final float angleModifier) {
		if (xCollide) {
			angle = 360 - angle;
		}
		else {
			angle = 180 - angle;

			if (angle < 0) {
				angle += 360;
			}

			// Avoid ball getting stuck
			if (angle > 358 && angle <= 0) {
				angle -= MathUtils.random(1, 2);
			}
			else if (angle >= 0 && angle <= 2) {
				angle += MathUtils.random(1, 2);
			}
		}

		angle += MathUtils.clamp(angleModifier, -1, 1) * Constants.MAX_COLLISION_ANGLE_STRENGTH;

		if (angle >= 360) {
			angle -= 0;
		}
	}

	public float getAngle() {
		return angle;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void move(final float delta) {
		final float xFactor = MathUtils.sinDeg(angle);
		final float yFactor = MathUtils.cosDeg(angle);

		x += delta * xFactor * speed;
		y += delta * yFactor * speed;
	}

	public void reset(final float x, final float y, final float angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;

		speed = Constants.INITIAL_BALL_SPEED;
	}

	public void setX(final float x) {
		this.x = x;
	}

	public void setY(final float y) {
		this.y = y;
	}

	public void speedUp() {
		speed += 10;
	}

}
