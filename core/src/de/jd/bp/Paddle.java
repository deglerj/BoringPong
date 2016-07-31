package de.jd.bp;

public class Paddle {

	private final float	x;

	private float		height	= 80;

	private float		y		= Constants.BOARD_HEIGHT / 2f - height / 2f;

	private float		speed	= Constants.INITIAL_PADDLE_SPEED;

	public Paddle(final float x) {
		this.x = x;
	}

	public void expand() {
		if (height + 10 < Constants.MAX_PADDLE_HEIGHT) {
			height += 10;

			stayInBounds(y);
		}
	}

	public float getHeight() {
		return height;
	}

	public float getSpeed() {
		return speed;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setHeight(final float height) {
		this.height = height;
	}

	public void setSpeed(final float speed) {
		this.speed = speed;
	}

	public void setY(final float y) {
		this.y = y;

		stayInBounds(y);
	}

	public void shrink() {
		if (height - 10 > Constants.MIN_PADDLE_HEIGHT) {
			height -= 10;

			stayInBounds(y);
		}
	}

	private void stayInBounds(final float y) {
		if (y + height > Constants.BOARD_HEIGHT) {
			this.y = Constants.BOARD_HEIGHT - height;
		}
		else if (y < 0) {
			this.y = 0;
		}
	}

}
