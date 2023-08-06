# Live Football World Cup Score Board

Provide the implementation of the Live Football World Cup Score Board

## Requirements
1. Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
   This should capture following parameters:
    * Home team
    * Away team
2. Update score. This should receive a pair of absolute scores: home team score and away team score.
3. Finish match currently in progress. This removes a match from the scoreboard.
4. Get a summary of matches in progress ordered by their total score. The matches with the same total score will be returned ordered by the most recently started match in the scoreboard.

## Assumptions
We assume that after football match initialization:
1. score value cannot be negative
2. score value cannot be decreased
3. score value can only be sequentially increased by 1 for one team at a time
4. each match starts with some delay, so we can always distinguish which is the most recent

## Notes
Please note that ```showOngoingMatches(List<Match> matches)``` method added just to print results in the console.