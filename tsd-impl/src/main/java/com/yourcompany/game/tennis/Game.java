package com.yourcompany.game.tennis;

import java.util.List;
import java.util.ArrayList;

public class Game {
	private TennisPlayer player1;
	private TennisPlayer player2;
	private TennisPoint scorePlayer1;
	private TennisPoint scorePlayer2;
	private TennisPlayer winner;
	private List<String> scoreHistory;
	private boolean isTieBreak;

	public Game(TennisPlayer player1, TennisPlayer player2, boolean isTieBreak) {
		this.player1 = player1;
		this.player2 = player2;
		this.isTieBreak = isTieBreak;
		this.scorePlayer1 = new TennisPoint(isTieBreak);
		this.scorePlayer2 = new TennisPoint(isTieBreak);
		this.winner = null;
		this.scoreHistory = new ArrayList<>();
		updateScoreHistory();
	}

	public Game(TennisPlayer player1, TennisPlayer player2) {
		this(player1, player2, false);
	}


	public TennisPoint getScorePlayer1() {
		return scorePlayer1;
	}

	public TennisPoint getScorePlayer2() {
		return scorePlayer2;
	}

	public TennisPlayer getWinner() {
		return winner;
	}

	public void pointWonBy(TennisPlayer scoringPlayer) {
		if (winner != null) {
			return;
		}

		if (scoringPlayer.equals(player1)) {
			scorePoint(scorePlayer1, scorePlayer2);
		} else if (scoringPlayer.equals(player2)) {
			scorePoint(scorePlayer2, scorePlayer1);
		} else {
			return;
		}

		checkGameEnd();
		updateScoreHistory();
	}

	private void scorePoint(TennisPoint scoringPoint, TennisPoint opponentPoint) {
		if (isTieBreak) {
			scoringPoint.increment();
		} else {
			if (scoringPoint.getValue() == 40) {
				if (opponentPoint.getValue() == 40 && !opponentPoint.isAdvantage()) {
					scoringPoint.setAdvantage();
				} else if (opponentPoint.isAdvantage()) {
					opponentPoint.reset();
					opponentPoint.increment();
					opponentPoint.increment();
					opponentPoint.increment();
				}
			} else {
				scoringPoint.increment();
			}
		}
	}


	private void checkGameEnd() {
		if (isTieBreak) {
			if ((scorePlayer1.getValue() >= 7 || scorePlayer2.getValue() >= 7) && Math.abs(scorePlayer1.getValue() - scorePlayer2.getValue()) >= 2) {
				if (scorePlayer1.getValue() > scorePlayer2.getValue()) {
					winner = player1;
				} else {
					winner = player2;
				}
			}
		} else {
			if (scorePlayer1.getValue() == 40 && scorePlayer1.isAdvantage()) {
				winner = player1;
			} else if (scorePlayer2.getValue() == 40 && scorePlayer2.isAdvantage()) {
				winner = player2;
			} else if (scorePlayer1.getValue() >= 40 && scorePlayer1.getValue() >= scorePlayer2.getValue() + 2) {
				winner = player1;
			} else if (scorePlayer2.getValue() >= 40 && scorePlayer2.getValue() >= scorePlayer1.getValue() + 2) {
				winner = player2;
			}
		}
	}


	public boolean isFinished() {
		return winner != null;
	}

	public String getScoreString() {
		if (isTieBreak && isFinished()) {
			return scorePlayer1.toString() + "-" + scorePlayer2.toString();
		} else if (isTieBreak) {
			return scorePlayer1.toString() + "-" + scorePlayer2.toString();
		} else {
			if (scorePlayer1.getValue() == 40 && scorePlayer2.getValue() == 40 && !scorePlayer1.isAdvantage() && !scorePlayer2.isAdvantage()) {
				return "40-40 (Deuce)";
			}
			return scorePlayer1.toString() + "-" + scorePlayer2.toString();
		}
	}


	private void updateScoreHistory() {
		scoreHistory.add(getScoreString());
	}

	public List<String> getScoreHistory() {
		return scoreHistory;
	}

	@Override
	public String toString() {
		if (isFinished()) {
			return "Game won by " + winner.getName() + " with score " + getScoreString();
		}
		return "Current score: " + getScoreString();
	}

	public boolean getIsTieBreak() {
		return isTieBreak;
	}
}
