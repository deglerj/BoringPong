package de.jd.bp;

public class Constants {

	public static final float	WORLD_WIDTH						= 640;

	public static final float	WORLD_HEIGHT					= 480;

	public static final float	PADDLE_WIDTH					= 15;

	public static final float	DIVIDER_WIDTH					= 5;

	public static final int		DIVIDER_COUNT					= 19;

	public static final float	SCOREBOARD_HEIGHT				= 40;

	public static final float	BALL_RADIUS						= 15;

	public static final float	BOARD_HEIGHT					= WORLD_HEIGHT - SCOREBOARD_HEIGHT;

	public static final float	MAX_COLLISION_ANGLE_STRENGTH	= 44;

	public static final float	INITIAL_BALL_SPEED				= 400;

	public static final float	INITIAL_PADDLE_SPEED			= 400;

	public static final float	INITIAL_PADDLE_HEIGHT			= 80;

	public static final float	MIN_PADDLE_HEIGHT				= 50;

	public static final float	MAX_PADDLE_HEIGHT				= BOARD_HEIGHT / 2f;

	private Constants() {
	}

}
