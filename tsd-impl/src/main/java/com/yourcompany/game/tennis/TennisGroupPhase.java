package com.yourcompany.game.tennis;

import com.yourcompany.game.TournamentPhase;
import com.yourcompany.game.DrawStrategy;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TennisGroupPhase implements TournamentPhase<TennisPlayer, TennisMatch> {

	private String name;
	private List<TennisPlayer> participants;
	private List<TennisMatch> matches;
	private TennisStandings standings;
	private boolean isStarted;
	private boolean isFinished;
	private List<TennisPlayer> qualifiers;

	public TennisGroupPhase(String name, List<TennisPlayer> participants, List<DrawStrategy<TennisPlayer>> drawStrategies) {
		this.name = name;
		this.participants = participants;
		this.matches = new ArrayList<>();
		this.standings = new TennisStandings(participants, drawStrategies);
		this.isStarted = false;
		this.isFinished = false;
		this.qualifiers = new ArrayList<>();
		generateMatches();
	}

	private void generateMatches() {
		for (int i = 0; i < participants.size(); i++) {
			for (int j = i + 1; j < participants.size(); j++) {
				TennisPlayer player1 = participants.get(i);
				TennisPlayer player2 = participants.get(j);
				this.matches.add(new TennisMatch(player1, player2, 2));
			}
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void startPhase() {
		if (!isStarted) {
			isStarted = true;
			System.out.println("Faza grupowa '" + name + "' rozpoczęła się!");
		} else {
			System.out.println("Faza grupowa '" + name + "' już trwa.");
		}
	}

	@Override
	public void endPhase() {
		if (isStarted && !isFinished) {
			boolean allMatchesFinished = true;
			for (TennisMatch match : matches) {
				if (!match.isFinished()) {
					allMatchesFinished = false;
					break;
				}
			}

			if (allMatchesFinished) {
				isFinished = true;
				System.out.println("Faza grupowa '" + name + "' zakończyła się!");
				determineQualifiers();
			} else {
				System.out.println("Nie można zakończyć fazy grupowej '" + name + "'. Nie wszystkie mecze zostały zakończone.");
			}
		} else if (!isStarted) {
			System.out.println("Faza grupowa '" + name + "' nie została jeszcze rozpoczęta.");
		} else {
			System.out.println("Faza grupowa '" + name + "' już się zakończyła.");
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

	@Override
	public List<TennisMatch> getMatches() {
		return matches;
	}

	@Override
	public List<TennisPlayer> getQualifiers() {
		if (!isFinished) {
			System.out.println("Faza grupowa '" + name + "' nie została zakończona. Nie można ustalić kwalifikantów.");
			return Collections.emptyList();
		}
		return qualifiers;
	}

	public void updateStandings() {
		standings.updateStandings(matches);
		System.out.println("\nKlasyfikacja w grupie '" + name + "' zaktualizowana:");
		System.out.println(standings.getStandingsTable());
	}

	private void determineQualifiers() {
		List<TennisPlayer> ranked = standings.getRankedContestants();
		if (ranked.size() >= 2) {
			qualifiers.add(ranked.get(0));
			qualifiers.add(ranked.get(1));
			System.out.println("Kwalifikanci z grupy '" + name + "': " + qualifiers.get(0).getName() + " i " + qualifiers.get(1).getName());
		} else {
			System.out.println("Nie można ustalić kwalifikantów z grupy '" + name + "'. Zbyt mało zawodników.");
		}
	}

	public TennisStandings getStandings() {
		return standings;
	}

	@Override
	public String toString() {
		if (!isStarted) {
			return "Faza grupowa '" + name + "' jeszcze się nie rozpoczęła.";
		} else if (isFinished) {
			return "Faza grupowa '" + name + "' zakończona. Kwalifikanci: " + qualifiers;
		} else {
			return "Faza grupowa '" + name + "' trwa. Mecze: " + matches.size() + ". Klasyfikacja:\n" + standings.getStandingsTable();
		}
	}
}
