package com.sportradar;

public interface Match {
    String getHomeTeam();

    String getAwayTeam();

    int getHomeTeamScore();

    int getAwayTeamScore();

    void updateScore(int homeScore, int awayScore);

    int getTotalScore();
}
