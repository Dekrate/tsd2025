package com.yourcompany.game.tennis;

import java.util.List;
import java.util.ArrayList;

public class TennisSet {
	private TennisPlayer player1;
	private TennisPlayer player2;
	private List<Game> games;
	private int gamesWonPlayer1;
	private int gamesWonPlayer2;
	private TennisPlayer winner;

	public TennisSet(TennisPlayer player1, TennisPlayer player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.games = new ArrayList<>();
		this.gamesWonPlayer1 = 0;
		this.gamesWonPlayer2 = 0;
		this.winner = null;
		startNewGame();
	}
	public List<Game> getGames() {
		return games;
	}

	public int getGamesWonPlayer1() {
		return gamesWonPlayer1;
	}

	public int getGamesWonPlayer2() {
		return gamesWonPlayer2;
	}

	public TennisPlayer getWinner() {
		return winner;
	}

	public void startNewGame() {
		if (isFinished()) {
			return;
		}

		Game newGame;
		if (gamesWonPlayer1 == 6 && gamesWonPlayer2 == 6) {
			newGame = new Game(player1, player2, true);
		} else {
			newGame = new Game(player1, player2, false);
		}
		games.add(newGame);
	}

	public void pointWonBy(TennisPlayer scoringPlayer) {
		if (isFinished()) {
			return;
		}

		Game currentGame = getCurrentGame();
		if (currentGame == null) {
			return;
		}

		currentGame.pointWonBy(scoringPlayer);

		if (currentGame.isFinished()) {
			if (currentGame.getWinner().equals(player1)) {
				gamesWonPlayer1++;
			} else {
				gamesWonPlayer2++;
			}
			checkSetEnd();
			if (!isFinished()) {
				startNewGame();
			}
		}
	}

	public Game getCurrentGame() {
		if (isFinished() || games.isEmpty()) {
			return null;
		}
		return games.getLast();
	}

	private void checkSetEnd() {
		if ((gamesWonPlayer1 >= 6 || gamesWonPlayer2 >= 6) && Math.abs(gamesWonPlayer1 - gamesWonPlayer2) >= 2) {
			if (gamesWonPlayer1 > gamesWonPlayer2) {
				winner = player1;
			} else {
				winner = player2;
			}
		}
		if ((gamesWonPlayer1 == 7 && gamesWonPlayer2 == 6) || (gamesWonPlayer2 == 7 && gamesWonPlayer1 == 6)) {
			if (gamesWonPlayer1 > gamesWonPlayer2) {
				winner = player1;
			} else {
				winner = player2;
			}
		}
		if (gamesWonPlayer1 > 7 && gamesWonPlayer1 > gamesWonPlayer2 + 1) {
			winner = player1;
		} else if (gamesWonPlayer2 > 7 && gamesWonPlayer2 > gamesWonPlayer1 + 1) {
			winner = player2;
		}
	}

	public boolean isFinished() {
		return winner != null;
	}

	public String getScoreString() {
		String score = gamesWonPlayer1 + "-" + gamesWonPlayer2;
		if (!isFinished()) {
			Game currentGame = getCurrentGame();
			if (currentGame != null) {
				score += " (" + currentGame.getScoreString() + ")";
			}
		} else {
			Game lastGame = games.getLast();
			if (lastGame.getIsTieBreak()) {
				int tieBreakLoserScore = (lastGame.getWinner().equals(player1)) ? lastGame.getScorePlayer2().getValue() : lastGame.getScorePlayer1().getValue();
				score = gamesWonPlayer1 + "-" + gamesWonPlayer2 + "(" + tieBreakLoserScore + ")";
			}
		}
		return score;
	}

	@Override
	public String toString() {
		if (isFinished()) {
			return "Set won by " + winner.getName() + " with score " + getScoreString();
		}
		return "Current set score: " + getScoreString();
	}
}
