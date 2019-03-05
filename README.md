# Dynamite Playground

This project is designed to help you create and prototype bots that play [Dynamite](https://dynamite.softwire.com/).

Start by creating an account there - it might take a while for your registration to come through so it's worth doing today even if we won't actually need your account until later on.

## Rules

The rules of Dynamite are very simple. Dynamite is based on Rock-Paper-Scissors. Scissors beats Paper, which beats Rock, which beat Scissors. The game is played until one player has 1000 points.

Then Dynamite is added into the mix. Dynamite beats all of Rock, Paper and Scissors, but you can only use it 100 times throughout the whole game. If you try to use Dynamite after you've run out, you'll lose!

Finally, you can also play Water Balloon. Unlike Dynamite, Water Balloon is unlimited. It beats Dynamite, but loses to everything else.

If a round is a draw, then the points for that round carry over to the next round. So if there are two draws in a row, then the next round will be worth 3 points instead of just one.

## Playing the game

You can play two bots against each other by editing the code in between the two comments of the `Main` class. The first line defines the rules for the game (the score needed to win, the maximum number of dynamites, and after how many rounds the game should be declared a draw if there's no winner yet).

The second line tells the program which two bots to play against each other.

## Some example bots

There are some example bots in the `bots` package of this repository:

`RockBot` will always play Rock.

`ExceptionBot` will always throw an error.

`UserBot` isn't really a bot at all, but is a "pretend bot" that lets the user play the game using console input.

## Get started

Before you write your own bot to play Dynamite, it will be helpful to become familiar with how to write a bot. Here are some examples that you should write so that you become familiar with the process.

### ScissorsBot

Write a bot that always plays Scissors. This should be very similar to RockBot. 

### RandomBot

Write a bot that plays a random choice of Rock, Paper or Scissors.

### EagerBot

Write a bot that plays all its dynamite first, then switches to random choices.

### CleverBot

Write a bot that looks at what move the opponent played last, and plays a move that will beat it.

This is the first bot which needs you to actually look at the history of the game to work out what to do. Here's how:

The `makeMove` method on your bot accepts the `Gamestate` as input.

`Gamestate` is a class that has a method `getRounds` which returns a list of the rounds from the game so far.

Each round has two methods `getP1` and `getP2` which returns the moves made by the two players in that round. Your bot should be written from the perspective of Player 1 - so `getP1` returns your move, and `getP2` returns your opponent's move.

So you can use the `Gamestate` to examine the previous round, and use that to work out what to do.

## Write your own bot!

See what strategies you can come up with. Some things to think about:

 - When to use randomness and when to do something deliberate.

 - When is the best time to use your dynamites? Can you predict when your opponent will use dynamite and play water balloon?

Remember that your bot must implement the `Bot` interface to be accepted by the program. This just means that your bot needs a `makeMove` method that accepts the game state as an input and returns the next move to make.
