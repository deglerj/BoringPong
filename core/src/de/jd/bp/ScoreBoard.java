package de.jd.bp;

public class ScoreBoard {

	private int	pointsLeft	= 0;

	private int	pointsRight	= 0;

	public void addPointLeft() {
		pointsLeft++;
	}

	public void addPointRight() {
		pointsRight++;
	}

	public String getPointsLeft() {
		return getPoints(pointsLeft, pointsRight);
	}

	public String getPointsRight() {
		return getPoints(pointsRight, pointsLeft);
	}

	private String getPoints(final int self, final int other) {
		if (self == 0) {
			return "No points";
		}
		else if (self == 1) {
			return "One point";
		}
		else if (self > 1 && other <= 1) {
			return "Points";
		}
		else if (self > other) {
			return "More points";
		}
		else {
			return "Some points";
		}
	}
}
