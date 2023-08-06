package com.sportradar;

import java.util.List;

public interface Scoreboard {
    void startMatch(String homeTeam, String awayTeam);

    void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore);

    void finishMatch(String homeTeam, String awayTeam);

    List<Match> getMatchesSummary();

    void showOngoingMatches(List<Match> matches);
}
