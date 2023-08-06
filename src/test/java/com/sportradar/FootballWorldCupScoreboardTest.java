package com.sportradar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FootballWorldCupScoreboardTest {

    private FootballWorldCupScoreboard scoreboard;

    @BeforeEach
    void setUp() {
        scoreboard = new FootballWorldCupScoreboard();
    }

    @Test
    void testStartMatch() {
        scoreboard.startMatch("Canada", "Brazil");

        await().atMost(Duration.ofMillis(150)).until(() -> true);
        scoreboard.startMatch("Germany", "Portugal");

        List<Match> matchesSummary = scoreboard.getMatchesSummary();
        assertEquals(2, matchesSummary.size());
        assertEquals("Germany", matchesSummary.get(0).getHomeTeam());
        assertEquals("Portugal", matchesSummary.get(0).getAwayTeam());
        assertEquals("Canada", matchesSummary.get(1).getHomeTeam());
        assertEquals("Brazil", matchesSummary.get(1).getAwayTeam());
    }

    @Test
    void testUpdateScore() {
        scoreboard.startMatch("Canada", "Brazil");
        scoreboard.updateScore("Canada", "Brazil", 1, 0);
        scoreboard.updateScore("Canada", "Brazil", 1, 1);
        scoreboard.updateScore("Canada", "Brazil", 2, 1);

        List<Match> matchesSummary = scoreboard.getMatchesSummary();
        assertEquals(1, matchesSummary.size());
        assertEquals(2, matchesSummary.get(0).getHomeTeamScore());
        assertEquals(1, matchesSummary.get(0).getAwayTeamScore());
    }

    @Test
    void testFinishMatch() {
        scoreboard.startMatch("Canada", "Brazil");

        await().atMost(Duration.ofMillis(150)).until(() -> true);
        scoreboard.startMatch("Germany", "Portugal");

        scoreboard.finishMatch("Canada", "Brazil");

        List<Match> matchesSummary = scoreboard.getMatchesSummary();
        assertEquals(1, matchesSummary.size());
        assertEquals("Germany", matchesSummary.get(0).getHomeTeam());
        assertEquals("Portugal", matchesSummary.get(0).getAwayTeam());
    }

    @Test
    void testGetMatchesSummary() {
        scoreboard.startMatch("Mexico", "Canada");

        await().atMost(Duration.ofMillis(150)).until(() -> true);
        scoreboard.startMatch("Spain", "Brazil");

        await().atMost(Duration.ofMillis(150)).until(() -> true);
        scoreboard.startMatch("Germany", "France");

        await().atMost(Duration.ofMillis(150)).until(() -> true);
        scoreboard.startMatch("Uruguay", "Italy");

        await().atMost(Duration.ofMillis(150)).until(() -> true);
        scoreboard.startMatch("Argentina", "Australia");

        scoreboard.updateScore("Mexico", "Canada", 0, 1);
        scoreboard.updateScore("Mexico", "Canada", 0, 2);
        scoreboard.updateScore("Mexico", "Canada", 0, 3);
        scoreboard.updateScore("Mexico", "Canada", 0, 4);
        scoreboard.updateScore("Mexico", "Canada", 0, 5);

        scoreboard.updateScore("Spain", "Brazil", 0, 1);
        scoreboard.updateScore("Spain", "Brazil", 0, 2);
        scoreboard.updateScore("Spain", "Brazil", 1, 2);
        scoreboard.updateScore("Spain", "Brazil", 2, 2);
        scoreboard.updateScore("Spain", "Brazil", 3, 2);
        scoreboard.updateScore("Spain", "Brazil", 4, 2);
        scoreboard.updateScore("Spain", "Brazil", 5, 2);
        scoreboard.updateScore("Spain", "Brazil", 6, 2);
        scoreboard.updateScore("Spain", "Brazil", 7, 2);
        scoreboard.updateScore("Spain", "Brazil", 8, 2);
        scoreboard.updateScore("Spain", "Brazil", 9, 2);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);

        scoreboard.updateScore("Germany", "France", 0, 1);
        scoreboard.updateScore("Germany", "France", 1, 1);
        scoreboard.updateScore("Germany", "France", 2, 1);
        scoreboard.updateScore("Germany", "France", 2, 2);

        scoreboard.updateScore("Uruguay", "Italy", 0, 1);
        scoreboard.updateScore("Uruguay", "Italy", 0, 2);
        scoreboard.updateScore("Uruguay", "Italy", 0, 3);
        scoreboard.updateScore("Uruguay", "Italy", 0, 4);
        scoreboard.updateScore("Uruguay", "Italy", 0, 5);
        scoreboard.updateScore("Uruguay", "Italy", 0, 6);
        scoreboard.updateScore("Uruguay", "Italy", 1, 6);
        scoreboard.updateScore("Uruguay", "Italy", 2, 6);
        scoreboard.updateScore("Uruguay", "Italy", 3, 6);
        scoreboard.updateScore("Uruguay", "Italy", 4, 6);
        scoreboard.updateScore("Uruguay", "Italy", 5, 6);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);

        scoreboard.updateScore("Argentina", "Australia", 0, 1);
        scoreboard.updateScore("Argentina", "Australia", 1, 1);
        scoreboard.updateScore("Argentina", "Australia", 2, 1);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        List<Match> matchesSummary = scoreboard.getMatchesSummary();
        assertEquals("Uruguay", matchesSummary.get(0).getHomeTeam());
        assertEquals("Italy", matchesSummary.get(0).getAwayTeam());
        assertEquals("Spain", matchesSummary.get(1).getHomeTeam());
        assertEquals("Brazil", matchesSummary.get(1).getAwayTeam());
        assertEquals("Mexico", matchesSummary.get(2).getHomeTeam());
        assertEquals("Canada", matchesSummary.get(2).getAwayTeam());
        assertEquals("Argentina", matchesSummary.get(3).getHomeTeam());
        assertEquals("Australia", matchesSummary.get(3).getAwayTeam());
        assertEquals("Germany", matchesSummary.get(4).getHomeTeam());
        assertEquals("France", matchesSummary.get(4).getAwayTeam());

        scoreboard.showOngoingMatches(matchesSummary);
    }
}