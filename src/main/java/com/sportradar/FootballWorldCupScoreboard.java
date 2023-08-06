package com.sportradar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FootballWorldCupScoreboard implements Scoreboard {

    private final List<FootballMatch> matches;

    public FootballWorldCupScoreboard() {
        this.matches = new ArrayList<>();
    }

    @Override
    public void startMatch(String homeTeam, String awayTeam) {
        FootballMatch match = new FootballMatch(homeTeam, awayTeam);
        matches.add(match);
    }

    @Override
    public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
        for (Match match : matches) {
            if (isMatchExists(homeTeam, awayTeam, match)) {
                match.updateScore(homeTeamScore, awayTeamScore);
                break;
            }
        }
    }

    @Override
    public void finishMatch(String homeTeam, String awayTeam) {
        matches.removeIf(match -> isMatchExists(homeTeam, awayTeam, match));
    }

    @Override
    public List<Match> getMatchesSummary() {
        List<Match> sortedMatches = new ArrayList<>(matches);
        sortedMatches.sort(Comparator.comparing(Match::getTotalScore).reversed().thenComparing(Collections.reverseOrder()));
        return sortedMatches;
    }

    @Override
    public void showOngoingMatches(List<Match> matches) {
        for (Match match : matches)
            System.out.println(match.getHomeTeam() + " " + match.getHomeTeamScore() + " - " +
                    match.getAwayTeam() + " " + match.getAwayTeamScore());
    }

    private static boolean isMatchExists(String homeTeam, String awayTeam, Match match) {
        return match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam);
    }
}
