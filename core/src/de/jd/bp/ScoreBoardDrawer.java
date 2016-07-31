package de.jd.bp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class ScoreBoardDrawer {

	private final BitmapFont	bitmapFont	= new BitmapFont(Gdx.files.internal("Roboto30.fnt"));

	private final Batch			batch;

	private final GlyphLayout	glyphLayout;

	public ScoreBoardDrawer(final Batch batch, final GlyphLayout glyphLayout) {
		this.batch = batch;
		this.glyphLayout = glyphLayout;

	}

	public void draw(final ScoreBoard scoreBoard) {
		bitmapFont.setColor(BoringColors.DARKEST_BEIGE);

		glyphLayout.setText(bitmapFont, scoreBoard.getPointsLeft());
		bitmapFont.draw(batch, scoreBoard.getPointsLeft(), (Constants.WORLD_WIDTH / 2f) - glyphLayout.width - 20,
				Constants.WORLD_HEIGHT - 10);

		glyphLayout.setText(bitmapFont, scoreBoard.getPointsRight());
		bitmapFont.draw(batch, scoreBoard.getPointsRight(), (Constants.WORLD_WIDTH / 2f) + 20, Constants.WORLD_HEIGHT - 10);
	}
}
