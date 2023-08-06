package com.sportradar;

import java.time.LocalDateTime;
import java.util.Objects;

public class FootballMatch implements Match, Comparable<FootballMatch> {
    private final String homeTeam;
    private final String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private final LocalDateTime startTime;

    /**
     * Initialize a new match assuming initial score 0 - 0 and current Date-Time
     *
     * @param homeTeam Home team
     * @param awayTeam Away team
     */
    public FootballMatch(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
        this.startTime = LocalDateTime.now();
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void updateScore(int homeTeamScore, int awayTeamScore) {
        if (homeTeamScore >= 0 && awayTeamScore >= 0) {
            if (homeTeamScore >= this.homeTeamScore && awayTeamScore >= this.awayTeamScore) {
                if (homeTeamScore - this.homeTeamScore <= 1 && awayTeamScore - this.awayTeamScore <= 1) {
                    this.homeTeamScore = homeTeamScore;
                    this.awayTeamScore = awayTeamScore;
                } else {
                    throw new IllegalArgumentException("Score value can only be increased by 1");
                }
            } else {
                throw new IllegalArgumentException("Score value cannot be decreased");
            }
        } else {
            throw new IllegalArgumentException("Score value cannot be negative");
        }
    }

    public int getTotalScore() {
        return getHomeTeamScore() + getAwayTeamScore();
    }

    @Override
    public int compareTo(FootballMatch o) {
        int matchComparison = Integer.compare(this.getTotalScore(), o.getTotalScore());
        if (matchComparison == 0) {
            return this.startTime.compareTo(o.startTime);
        }
        return matchComparison;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballMatch that = (FootballMatch) o;
        return homeTeamScore == that.homeTeamScore && awayTeamScore == that.awayTeamScore && Objects.equals(homeTeam, that.homeTeam) && Objects.equals(awayTeam, that.awayTeam) && Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, homeTeamScore, awayTeamScore, startTime);
    }
}
