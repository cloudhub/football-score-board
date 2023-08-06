package com.sportradar;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

class FootballMatchTest {

    @Test
    void testFootballMatchInit() {
        FootballMatch match = new FootballMatch("Canada", "Brazil");

        assertEquals("Canada", match.getHomeTeam());
        assertEquals("Brazil", match.getAwayTeam());
        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getAwayTeamScore());
    }

    @Test
    void testUpdateScore() {
        FootballMatch match = new FootballMatch("Canada", "Brazil");
        match.updateScore(1, 0);
        match.updateScore(1, 1);
        match.updateScore(2, 1);
        match.updateScore(3, 1);
        match.updateScore(3, 2);

        assertEquals(3, match.getHomeTeamScore());
        assertEquals(2, match.getAwayTeamScore());
    }

    @Test
    void testUpdateScoreWithValueMoreThanOneThenThrowException() {
        FootballMatch match = new FootballMatch("Canada", "Brazil");

        assertThrows(IllegalArgumentException.class, () -> match.updateScore(2, 0));
    }

    @Test
    void testUpdateScoreWithOnePointForEachTeamThenThrowException() {
        FootballMatch match = new FootballMatch("Canada", "Brazil");

        assertThrows(IllegalArgumentException.class, () -> match.updateScore(1, 1));
    }

    @Test
    void testUpdateScoreWhenDecreaseValueThenThrowException() {
        FootballMatch match = new FootballMatch("Canada", "Brazil");
        match.updateScore(1, 0);
        match.updateScore(2, 0);

        assertThrows(IllegalArgumentException.class, () -> match.updateScore(1, 0));
    }

    @Test
    void testUpdateScoreWithNegativeValuesThenThrowException() {
        FootballMatch match = new FootballMatch("Canada", "Brazil");

        assertThrows(IllegalArgumentException.class, () -> match.updateScore(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> match.updateScore(0, -1));
        assertThrows(IllegalArgumentException.class, () -> match.updateScore(-1, -1));
    }

    @Test
    void testUpdateScoreWhenNegativeValuesAndScoreRemainUnchanged() {
        FootballMatch match = new FootballMatch("Canada", "Brazil");

        match.updateScore(1, 0);
        match.updateScore(1, 1);
        match.updateScore(2, 1);
        assertEquals(2, match.getHomeTeamScore());
        assertEquals(1, match.getAwayTeamScore());

        assertThrows(IllegalArgumentException.class, () -> match.updateScore(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> match.updateScore(2, -1));

        assertEquals(2, match.getHomeTeamScore());
        assertEquals(1, match.getAwayTeamScore());
    }

    @Test
    void testTotalScore() {
        FootballMatch match = new FootballMatch("Canada", "Brazil");
        match.updateScore(1, 0);
        match.updateScore(1, 1);
        match.updateScore(2, 1);
        match.updateScore(3, 1);
        match.updateScore(3, 2);

        assertEquals(5, match.getTotalScore());
    }

    @Test
    void testMatchComparisonWhenSameTotalScoreAndDifferentStartTime() {
        FootballMatch match1 = new FootballMatch("Canada", "Brazil");

        await().atMost(Duration.ofMillis(150)).until(() -> true);
        FootballMatch match2 = new FootballMatch("Germany", "Portugal");

        match1.updateScore(0, 1);
        match1.updateScore(0, 2);
        match1.updateScore(1, 2);
        match1.updateScore(2, 2);

        match2.updateScore(0, 1);
        match2.updateScore(1, 1);
        match2.updateScore(2, 1);
        match2.updateScore(3, 1);

        assertTrue(match1.compareTo(match2) < 0);
        assertTrue(match2.compareTo(match1) > 0);
    }

    @Test
    void testMatchComparisonWhenDifferentTotalScore() {
        FootballMatch match1 = new FootballMatch("Canada", "Brazil");
        FootballMatch match2 = new FootballMatch("Germany", "Portugal");

        match1.updateScore(1, 0);
        match1.updateScore(1, 1);
        match1.updateScore(2, 1);
        match1.updateScore(2, 2);

        match2.updateScore(1, 0);
        match2.updateScore(2, 0);
        match2.updateScore(3, 0);

        assertTrue(match1.compareTo(match2) > 0);
        assertTrue(match2.compareTo(match1) < 0);
    }
}