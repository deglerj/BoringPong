package de.jd.bp;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BoardDrawer {

	private final ShapeRenderer shapeRenderer;

	public BoardDrawer(final ShapeRenderer shapeRenderer) {
		this.shapeRenderer = shapeRenderer;
	}

	public void draw() {
		shapeRenderer.setColor(BoringColors.BEIGE);
		shapeRenderer.rect(0, 0, Constants.WORLD_WIDTH, Constants.BOARD_HEIGHT);

		shapeRenderer.setColor(BoringColors.DARKER_BEIGE);
		final float dividerHeight = Constants.BOARD_HEIGHT / Constants.DIVIDER_COUNT;
		final float dividerX = Constants.WORLD_WIDTH / 2f - Constants.DIVIDER_WIDTH / 2f;
		for (int i = 0; i < Constants.DIVIDER_COUNT; i += 2) {
			shapeRenderer.rect(dividerX, dividerHeight * i, Constants.DIVIDER_WIDTH, dividerHeight);
		}

	}

}
