# swingy
42 Unit Factory Project

This project implements a text-based RPG. The program follows the Model-View-Controller architecture and allows
switching between the console view and GUI view.

## Gameplay

A player can have multiple heroes of different types.

**When the player starts the game he has 2 options:**

- Create a hero
- Select a previously created hero.

**In either case, the player can see the hero stats:**
- Hero name
- Hero class
- Level
- Experience
- Attack
- Defense
- Hit Points

**Hero stats are affected by the hero level and artifacts. There are 3 types of artefacs:**
- Weapon - increases the attack
- Armor - increases defense
- Helm - increases hit points

After choosing a hero the actual game begins. The hero needs to navigate a square map.

**Each turn he can move one position in one of the 4 four directions:**
- North
- East
- South
- West

**When a hero moves to a position occupied by a villain, the hero has 2 options:**
- Fight, which engages him in a battle with the villain
- Run, which gives him a 50% chance of returning to the previous position. If the
odds aren’t on his side, he must fight the villain.

If a hero looses a battle, he dies.

**If a hero wins a battle, he gains:**
- Experience points, based on the villain power. Of course, he will level up if he
reaches the next level experience.
- An artifact, which he can keep or leave. Of course, winning a battle doesn’t guarantee
that an artefact will be droped and the quality of the artefact also varies
depending on the villain’s strenght.

## Bonus

**All heroes are stored in relational database.**