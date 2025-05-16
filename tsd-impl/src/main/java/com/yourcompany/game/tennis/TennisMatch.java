package com.yourcompany.game.tennis;

import com.yourcompany.game.Match;
import com.yourcompany.game.Penalty;
import com.yourcompany.game.Violation;
import com.yourcompany.game.Event;

import java.util.List;
import java.util.ArrayList;

public class TennisMatch implements Match<TennisPlayer, TennisResult> {

	private List<TennisPlayer> contestants;
	private TennisResult matchResult;
	private List<Event> eventLog;
	private boolean isStarted;
	private boolean isFinished;

	public TennisMatch(TennisPlayer player1, TennisPlayer player2, int totalSetsToWin) {
		this.contestants = new ArrayList<>();
		this.contestants.add(player1);
		this.contestants.add(player2);
		this.matchResult = new TennisResult(player1, player2, totalSetsToWin);
		this.eventLog = new ArrayList<>();
		this.isStarted = false;
		this.isFinished = false;
	}

	@Override
	public void startMatch() {
		if (!isStarted) {
			isStarted = true;
			System.out.println("Mecz tenisowy rozpoczął się!");
			eventLog.add(new Event("Mecz rozpoczął się", null));
		} else {
			System.out.println("Mecz już trwa.");
		}
	}

	@Override
	public void endMatch() {
		if (isStarted && !isFinished) {
			isFinished = true;
			System.out.println("Mecz tenisowy zakończył się!");
			eventLog.add(new Event("Mecz zakończył się", null));
		} else if (!isStarted) {
			System.out.println("Mecz nie został jeszcze rozpoczęty.");
		} else {
			System.out.println("Mecz już się zakończył.");
		}
	}

	@Override
	public void recordAction(TennisPlayer contestant, Object action) {
		if (!isStarted || isFinished) {
			System.out.println("Mecz nie jest w stanie, w którym można rejestrować akcje.");
			return;
		}

		if (action instanceof String && ((String) action).equalsIgnoreCase("point won")) {
			pointWonBy(contestant);
		} else {
			String description = "Akcja wykonana przez " + contestant.getName() + ": " + action.toString();
			eventLog.add(new Event(description, contestant));
			System.out.println("Zarejestrowano akcję: " + description);
		}
	}

	@Override
	public void recordViolation(Violation<TennisPlayer, TennisResult> violation) {
		if (!isStarted || isFinished) {
			System.out.println("Mecz nie jest w stanie, w którym można rejestrować naruszenia.");
			return;
		}
		String description = "Naruszenie zasad: " + violation.getDescription();
		TennisPlayer violatingPlayerForEvent = violation.getViolatingContestant();
		eventLog.add(new Event(description, violatingPlayerForEvent));

		System.out.println("Zarejestrowano naruszenie: " + description);

		Penalty<TennisPlayer, TennisResult> penalty = violation.getPenalty();
		if (penalty != null) {
			TennisPlayer violatingPlayer = violation.getViolatingContestant();

			penalty.applyPenalty(this, violatingPlayer);

		} else {
			System.out.println("Naruszenie nie wiąże się z karą.");
		}

		if (matchResult.isMatchFinished()) {
			endMatch();
		}
	}

	@Override
	public TennisResult getResult() {
		return matchResult;
	}

	@Override
	public List<TennisPlayer> getContestants() {
		return contestants;
	}

	public void pointWonBy(TennisPlayer scoringPlayer) {
		if (!isStarted || isFinished) {
			System.out.println("Mecz nie jest w stanie, w którym można zdobywać punkty.");
			return;
		}

		matchResult.pointWonBy(scoringPlayer);

		String description = "Punkt zdobyty przez " + scoringPlayer.getName();
		eventLog.add(new Event(description, scoringPlayer));
		System.out.println(description + ". Aktualny wynik: " + matchResult.getScoreString());

		if (matchResult.isMatchFinished()) {
			endMatch();
		}
	}

	@Override
	public boolean isStarted() {
		return isStarted;
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	public List<Event> getEventLog() {
		return eventLog;
	}

	@Override
	public String toString() {
		if (!isStarted) {
			return "Mecz tenisowy jeszcze się nie rozpoczął.";
		} else if (isFinished) {
			return "Mecz tenisowy zakończony. Wynik: " + matchResult.getScoreString() +
					". Zwycięzca: " + matchResult.getWinner().getName();
		} else {
			return "Mecz tenisowy trwa. Aktualny wynik: " + matchResult.getScoreString();
		}
	}
}
