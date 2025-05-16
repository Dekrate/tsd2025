package com.yourcompany.game.tennis;

import com.yourcompany.game.Contestant;
import com.yourcompany.game.Result;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class TennisResult implements Result {
	private TennisPlayer player1;
	private TennisPlayer player2;
	private List<TennisSet> sets;
	private Integer setsWonPlayer1;
	private Integer setsWonPlayer2;
	private TennisPlayer winner;
	private Integer totalSetsToWin;


	public TennisPlayer getPlayer2() {
		return player2;
	}

	public void setPlayer2(TennisPlayer player2) {
		this.player2 = player2;
	}

	public TennisPlayer getPlayer1() {
		return player1;
	}

	public void setPlayer1(TennisPlayer player1) {
		this.player1 = player1;
	}

	public TennisResult(TennisPlayer player1, TennisPlayer player2, int totalSetsToWin) {
		this.player1 = player1;
		this.player2 = player2;
		this.sets = new ArrayList<>();
		this.setsWonPlayer1 = 0;
		this.setsWonPlayer2 = 0;
		this.winner = null;
		this.totalSetsToWin = totalSetsToWin;
		startNewSet();
	}

	public List<TennisSet> getSets() {
		return sets;
	}

	public Integer getSetsWonPlayer1() {
		return setsWonPlayer1;
	}

	public Integer getSetsWonPlayer2() {
		return setsWonPlayer2;
	}

	public TennisPlayer getWinner() {
		return winner;
	}

	public void startNewSet() {
		if (isMatchFinished()) {
			return;
		}
		TennisSet newSet = new TennisSet(player1, player2);
		sets.add(newSet);
	}

	public void pointWonBy(TennisPlayer scoringPlayer) {
		if (isMatchFinished()) {
			return;
		}

		TennisSet currentSet = getCurrentSet();
		if (currentSet == null) {
			return;
		}

		currentSet.pointWonBy(scoringPlayer);

		if (currentSet.isFinished()) {
			if (currentSet.getWinner().equals(player1)) {
				setsWonPlayer1++;
			} else {
				setsWonPlayer2++;
			}
			checkMatchEnd();
			if (!isMatchFinished()) {
				startNewSet();
			}
		}
	}

	public TennisSet getCurrentSet() {
		if (isMatchFinished() || sets.isEmpty()) {
			return null;
		}
		return sets.getLast();
	}

	private void checkMatchEnd() {
		if (setsWonPlayer1.equals(totalSetsToWin)) {
			winner = player1;
		} else if (setsWonPlayer2.equals(totalSetsToWin)) {
			winner = player2;
		}
	}

	@Override
	public Boolean isMatchFinished() {
		return winner != null;
	}

	public String getScoreString() {
		StringBuilder score = new StringBuilder();
		for (int i = 0; i < sets.size(); i++) {
			score.append(sets.get(i).getScoreString());
			if (i < sets.size() - 1) {
				score.append(", ");
			}
		}
		return score.toString();
	}

	@Override
	public String toString() {
		if (isMatchFinished()) {
			return "Match won by " + winner.getName() + " with score " + getScoreString();
		}
		return "Current match score: " + getScoreString();
	}

}
