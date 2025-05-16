package com.yourcompany.game;

import java.util.List;

public interface Tournament<T extends Contestant, M extends Match<T, ?>> {

	String getName();

	List<T> getParticipants();

	void startTournament();

	void endTournament();

	boolean isStarted();

	boolean isFinished();

	TournamentPhase<T, M> getCurrentPhase();
}
