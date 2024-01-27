# Domain: Tennis Set Scoring

## Invariants (Rules)

* Player points: Love (== 0), 15, 30, 40
* 40 points && win the ball ⇒ win set
* both player 40 ⇒ players are deuce
* when deuce:
  * the winner of a ball ⇒ advantage
  * if advantage && win the ball ⇒  win set
  * if not advantage && win the ball ⇒ deuce (again)
  (so, when deuce, there could be a player with advantage... -or not)
  * simplification: when duce, we don't care about the points each player has

## How could we Enforce these Invariants?

(TODO)
