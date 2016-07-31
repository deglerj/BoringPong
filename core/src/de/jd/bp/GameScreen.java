package de.jd.bp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen extends ScreenAdapter {

	private Ai					ai;

	private Paddle				aiPaddle;

	private Ball				ball;

	private BallDrawer			ballDrawer;

	private SpriteBatch			batch;

	private BitmapFont			bitmapFont;

	private BoardDrawer			boardDrawer;

	private OrthographicCamera	camera;

	private GlyphLayout			glyphLayout;

	private PaddleDrawer		paddleDrawer;

	private Paddle				playerPaddle;

	private PaddleInputHandler	playerPaddleInputHandler;

	private ScoreBoard			scoreBoard;

	private ScoreBoardDrawer	scoreBoardDrawer;

	private ShapeRenderer		shapeRenderer;

	private FitViewport			viewport;

	@Override
	public void render(final float delta) {
		update(delta);

		Gdx.gl.glClearColor(BoringColors.DARK_BEIGE.r, BoringColors.DARK_BEIGE.g, BoringColors.DARK_BEIGE.b, BoringColors.DARK_BEIGE.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setProjectionMatrix(camera.projection);
		shapeRenderer.setTransformMatrix(camera.view);

		boardDrawer.draw();

		ballDrawer.draw(ball);

		paddleDrawer.draw(playerPaddle);
		paddleDrawer.draw(aiPaddle);

		shapeRenderer.end();

		batch.begin();
		batch.setProjectionMatrix(camera.projection);
		batch.setTransformMatrix(camera.view);

		scoreBoardDrawer.draw(scoreBoard);

		batch.end();
	}

	@Override
	public void resize(final int width, final int height) {
		viewport.update(width, height);
	}

	@Override
	public void show() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(Constants.WORLD_WIDTH / 2f, Constants.WORLD_HEIGHT / 2f, 0);
		camera.update();
		viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
		bitmapFont = new BitmapFont();
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		glyphLayout = new GlyphLayout();

		paddleDrawer = new PaddleDrawer(shapeRenderer);

		playerPaddle = new Paddle(0);
		aiPaddle = new Paddle(Constants.WORLD_WIDTH - Constants.PADDLE_WIDTH);
		playerPaddleInputHandler = new PaddleInputHandler(playerPaddle);

		scoreBoard = new ScoreBoard();

		boardDrawer = new BoardDrawer(shapeRenderer);
		scoreBoardDrawer = new ScoreBoardDrawer(batch, glyphLayout);

		ball = new Ball();
		ballDrawer = new BallDrawer(shapeRenderer);

		ai = new Ai();
	}

	private void handleAiScores() {
		ball.reset(playerPaddle.getX() + Constants.PADDLE_WIDTH + Constants.BALL_RADIUS,
				playerPaddle.getY() + playerPaddle.getHeight() / 2f, 90f);

		scoreBoard.addPointRight();

		playerPaddle.expand();
		aiPaddle.shrink();

		Sounds.fail.play();
	}

	private void handlePlayerScores() {
		ball.reset(aiPaddle.getX() - Constants.BALL_RADIUS, aiPaddle.getY() + aiPaddle.getHeight() / 2f, 270f);

		scoreBoard.addPointLeft();

		playerPaddle.shrink();
		aiPaddle.expand();

		Sounds.win.play();
	}

	private void update(final float delta) {
		playerPaddleInputHandler.handleInput(delta);

		updateAi(delta);

		ball.move(delta);

		updateBallCollision();
	}

	private void updateAi(final float delta) {
		// Only apply right paddle AI if ball is moving right
		if (ball.getAngle() > 0 && ball.getAngle() < 180) {
			ai.movePaddle(aiPaddle, ball, delta);
		}
		// Moving left and space down? Apply AI to player paddle
		else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			ai.movePaddle(playerPaddle, ball, delta);
		}
	}

	private void updateBallCollision() {
		// Collide with left wall
		if (ball.getX() <= 0) {
			handleAiScores();
		}
		// Collide with right wall
		else if (ball.getX() >= Constants.WORLD_WIDTH) {
			handlePlayerScores();
		}
		// Collide with left paddle
		else if (ball.getX() - Constants.BALL_RADIUS <= Constants.PADDLE_WIDTH
				&& ball.getY() - Constants.BALL_RADIUS <= playerPaddle.getY() + playerPaddle.getHeight()
				&& ball.getY() + Constants.BALL_RADIUS >= playerPaddle.getY()) {
			// Get y offset from paddle center
			final float yOffset = (playerPaddle.getY() + playerPaddle.getHeight() / 2f) - (ball.getY());
			final float angleModifier = yOffset / (playerPaddle.getHeight() / 2f);
			ball.collide(true, angleModifier);
			ball.setX(Constants.PADDLE_WIDTH + Constants.BALL_RADIUS);
			ball.speedUp();
			Sounds.bounce.play();
		}
		// Collide with right paddle
		else if (ball.getX() + Constants.BALL_RADIUS >= Constants.WORLD_WIDTH - Constants.PADDLE_WIDTH
				&& ball.getY() - Constants.BALL_RADIUS <= aiPaddle.getY() + aiPaddle.getHeight()
				&& ball.getY() + Constants.BALL_RADIUS >= aiPaddle.getY()) {
			// Get y offset from paddle center
			final float yOffset = (aiPaddle.getY() + aiPaddle.getHeight() / 2f) - (ball.getY());
			final float angleModifier = yOffset / (aiPaddle.getHeight() / 2f) * -1;
			ball.collide(true, angleModifier);
			ball.setX(Constants.WORLD_WIDTH - Constants.PADDLE_WIDTH - Constants.BALL_RADIUS);
			ball.speedUp();
			Sounds.bounce.play();
		}
		// Collide with floor
		else if (ball.getY() - Constants.BALL_RADIUS <= 0) {
			ball.collide(false, 0f);
			ball.setY(Constants.BALL_RADIUS);
			ball.speedUp();
			Sounds.bounce.play();
		}
		// Collide with ceiling
		else if (ball.getY() + Constants.BALL_RADIUS >= Constants.BOARD_HEIGHT) {
			ball.collide(false, 0f);
			ball.setY(Constants.BOARD_HEIGHT - Constants.BALL_RADIUS);
			ball.speedUp();
			Sounds.bounce.play();
		}

	}

}
