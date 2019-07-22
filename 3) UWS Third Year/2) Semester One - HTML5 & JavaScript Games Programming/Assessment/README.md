Made in 2017, Bubble Aliens is a short serious game designed to teach people about hydrocarbons, cracking and fractional distillation and their uses. Through repetitive gameplay the player will learn what each hydrocarbon can be used for. 

The game is endless, competing to get the highest score. There are several bugs - collision handling sometimes messes up, text sprites aren't destroyed correctly, sounds overlap, and a major bug of which bubbles don't reset correctly until it's been popped again and thus the same unit can spawn multiple times.

Responsible for – Scripting, finding free assets and editing if necessary.\
Content creators are credited.

<!-- Screenshots Slideshow -->

[Portfolio](https://yuchingho.com/bubble-aliens)\
[Play on Itch](https://yuchingho.itch.io/bubble-aliens)\
[YouTube](https://youtu.be/KCYWLSK5n3I)

<!-- What each hydrocarbon spawns:
2:35 - Bitumen
0:16 - Fuel
0:35 - Diesel
0:50 - Kerosene
1:07 - Naphtha
4:30 - Petrol
5:55 - Refinery Gases -->
<!-- Code on GitHub, before YouTube -->
<!-- Game Design Document, after YouTube -->

## Assessment
Some Phaser Example Sprites were used or created through Piskel, Photoshop. The map created with Tiled and the background music was taken from Phaser Example Bodenstaendig_2000. Other audio files were downloaded from [SoundBible](http://soundbible.com) or created myself and edited with Audacity. 

If downloaded, an editor like [Brackets](http://brackets.io/), [Webstorm](https://www.jetbrains.com/webstorm/), [Komodo Edit](https://www.activestate.com/komodo-ide/downloads/edit) or using [XAMPP](https://www.apachefriends.org/index.html) is required to open the project and play the game via the "index.html" file. 

<p align="center">
  <img src="https://raw.githubusercontent.com/yuchingho/University/master/3)%20UWS%20Third%20Year/2)%20Semester%20One%20-%20HTML5%20%26%20JavaScript%20Games%20Programming/XAMPP.png?raw=true" alt="XAMPP"/>
</p>

## Marks Breakdown
I received 91/100, A1 - Lecturer's comments:
> Simple game design. Perhaps it is better to have a main page which introduce the game and a separate game page to host the game. The overview of the game is included. More details on the research would be beneficial, comprehensive design document.
>
> Very creative idea. The fact that it looks like a engineering management game but it is actually an arcade game (every element contributed to the army which is spawn on the 'battlefield' on the right hand side of the screen) makes the game interesting. There are some collission bugs on the bubble.
>
> A menu including the instructions are implemented at the start of the game. The assets are fairly basic but it is clear and relevant. The spawned objects on the battlefield looks good (and funny) too.

---

# Game Design Document

Change Log:
- 25/09/2017: Document Completed
- 27/09/2017: Document Updated for clarity and nature of game
- 07/10/2017: Embedded into website
- 07/12/2017: Design Document Final Version

Genres:
- Casual
- Platform
- Serious
- Tower Defence

Market Research\
Searching online and through Steam produced very little results similar to the game I'm thinking of; there are educational video games but they don't play like one.

Target Audience\
Mainly aimed towards male teenages from the ages of 14 - 16 who prefers interactive stimulation to learning. Anyone else who doesn't know chemistry and plays it - assuming they enjoy the game - should be able to explain what fuels from a fractional distillation are used for to a GCSE level.

Game Overview\
This will be a serious game in learning Chemistry to a high-school graduate level. Since this game will be extremely big - an AAA video game standard - I will be breaking down the game into lots of different parts: mini games inside a big game. In the final version of the game, the player would be taken into story mode and as progressing, learn chemistry passively whilst enjoying the game. In this document, I will assume I have already achieved that and thus will be focusing on a "mini-game" about Fractional Distillation at a GCSE Level.

Core Objective\
The core objective of the game is defending against an ALIEN. The secondary objective is to passively learn fractional distillation to a high-school standard. I take these concepts from <a class="LB" href="http://www.bbc.co.uk/education/guides/zm2v4wx/revision">GCSE Bitesize Fractional Distillation</a>:
- Crude Oil
- Hydrocarbons
- Fractions
- Different uses of fractions
- Positive and side effects of using fractions
- The different boiling points, viscosity, flammability
- Interesting side tidbits e.g. <a class="LB" href="https://www.youtube.com/watch?v=7nL10C7FSbE">colour of fuel when burnt</a> (things you won't see inside a high-school setting)
- Cracking
- The process of cracking

Expected Experience\
Playing through the game won't feel like "learning", and instead people who play it will want to play it over and over again not for the learning aspect, instead for the game aspect - i.e. beat the game with a higher score or pure enjoyment of the game.

Gameplay Theme\
The game will be similar to a game called [Bubble Trouble](https://www.youtube.com/embed/hVFri15sqsE), with a unique twist.

Game Structure\
Bubble Aliens is a unique defense game - defence isn't placed by the player, instead the player defeats the ALIEN by supplying the fuel for Earth. Crude Oil bubbles will bounce around close to the player and the player has to pop the bubble the right number of times to supply the correct fuel.

GUI and Environment\
The screen will be split into two: the left side is based off a fractionating column and on the right side, an animation of the Earth's Defense that updates according to the fuel supplied by the player. Below is a rough sketch of the game area to be designed.

<p align="center">
  <img src="https://github.com/yuchingho/University/blob/master/3)%20UWS%20Third%20Year/2)%20Semester%20One%20-%20HTML5%20%26%20JavaScript%20Games%20Programming/Assessment/BubbleAliens%20Prototype.png" alt="BubbleAliens Prototype"/>
</p>

<p align="center">
  <img src="https://github.com/yuchingho/University/blob/master/3)%20UWS%20Third%20Year/2)%20Semester%20One%20-%20HTML5%20%26%20JavaScript%20Games%20Programming/Assessment/BubbleAliens%20Fractioning%20Column.png" alt="BubbleAliens Fractioning Column"/>
</p>

Features Implemented\
Earth's default defense will be a Ground-to-Air tower defense system, and supplying the correct fuel will call in these defenses:
- Fuel - Battleships
- Diesel - Tanks
- Kerosene - Planes
- Naphtha - Charging up a bomb
- Gasoline/Petrol - Trucks
- Refinergy Gases - Increasing the army's damage

Player Mechanics:
- Arrow Key Left - Move Left
- Arrow Key Right - Move Right
- Arrow Key Up - Fire special weapon
- Space - Shoot

Gameplay, Interaction &amp; Consequences\
Black bubbles enter from the left hand side of the screen, and bouncing quite low to the ground and slowly to the right hand side. These bubbles represent unbroken "Crude Oil" - and they will keep bouncing until they are broken. The player controls a bunsen burner and the default weapon will be shooting out fireballs to heat up and crack the bubble. When the player cracks Crude Oil, the bubble becomes Fuel. Fuel can become Diesel, Desiel to Kerosene, etc. until the bubble becomes Refinery Gas which floats to the top. As each bubble is cracked, they bounce higher and higher and the Residue left behind will drip down.

Each time a bubble touches the right-hand side of the fractionating column, the bubble is destroyed and its representative faction is added to the Earth Defense System. In terms of firepower the game doesn't make sense: battleships does the least amount of damage whilst trucks do the most. This is because of how the fractional column is set out, because getting Fuel is very easy - the bubble needs to be popped once - and getting gasoline/petrol is difficult.

To add a bit of knowledge, whenever a bubble is popped its cracking temperature is shown. This information is sourced from <a class="LB" href="https://sophiesrevisionblog.blogspot.co.uk/2014/02/gces-chemistry-unit-1-crude-oil.html">GCSE's Chemistry Unit 1</a>.

<p align="center">
  <img src="https://github.com/yuchingho/University/blob/master/3)%20UWS%20Third%20Year/2)%20Semester%20One%20-%20HTML5%20%26%20JavaScript%20Games%20Programming/Assessment/BubbleAliens%20Fractioning%20Column%20Temperatures.png" alt="BubbleAliens Fractioning Column Temperatures"/>
</p>

Time is of essence here! If the player does not kill the ALIEN within the time-limit, Earth will lose! How to go about winning this game is up to the player: spawning lots and lots of battleships, or call more powerful forces and increase their damage with Refinery Gas, or charge up a bomb that will do massive damage to the ALIEN. Residue is equally important as the rest of the fractions being made as it repairs the base defense system.

The player has three chances before the game is lost. To not lose thoses chances, Residue must be avoided because if in contact, Residue will drench the player. The player will become flammable for a few seconds. If the player tries to fire during that time period, the player will explode, lose a life and ultimately, slowing down Earth's Defense. To get rid of the drench, the player must wait until they are dry again before firing.

<!--
<h1><span style="text-decoration: underline;">Design Document</span></h1>
<ul>
 	<li style="list-style-type: none;"><span style="text-decoration: underline;">Change Log:</span></li>
 	<li>25/09/2017: Document Completed</li>
 	<li>27/09/2017: Document Updated for clarity and nature of game</li>
 	<li>07/10/2017: Embedded into website</li>
 	<li>07/12/2017: Design Document Final Version</li>
</ul>
<ul>
 	<li style="list-style-type: none;"><span style="text-decoration: underline;">Genres:</span></li>
 	<li>Casual</li>
 	<li>Platform</li>
 	<li>Serious</li>
 	<li>Tower Defence</li>
</ul>
<u>Market Research</u>
Searching online and through Steam produced very little results similar to the game I'm thinking of; there are educational video games but they don't play like one.

<!--<u>Target Audience</u>
Mainly aimed towards male teenages from the ages of 14 - 16 who prefers interactive stimulation to learning. Anyone else who doesn't know chemistry and plays it - assuming they enjoy the game - should be able to explain what fuels from a fractional distillation are used for to a GCSE level.

<!--<u>Game Overview</u>
This will be a serious game in learning Chemistry to a high-school graduate level. Since this game will be extremely big - an AAA video game standard - I will be breaking down the game into lots of different parts: mini games inside a big game. In the final version of the game, the player would be taken into story mode and as progressing, learn chemistry passively whilst enjoying the game. In this document, I will assume I have already achieved that and thus will be focusing on a "mini-game" about Fractional Distillation at a GCSE Level.

<!--<u>Core Objective</u>
The core objective of the game is defending against an ALIEN. The secondary objective is to passively learn fractional distillation to a high-school standard. I take these concepts from <a class="LB" href="http://www.bbc.co.uk/education/guides/zm2v4wx/revision">GCSE Bitesize Fractional Distillation</a>:
<ul>
 	<li>Crude Oil</li>
 	<li>Hydrocarbons</li>
 	<li>Fractions</li>
 	<li>Different uses of fractions</li>
 	<li>Positive and side effects of using fractions</li>
 	<li>The different boiling points, viscosity, flammability</li>
 	<li>Interesting side tidbits e.g. <a class="LB" href="https://www.youtube.com/watch?v=7nL10C7FSbE">colour of fuel when burnt</a>
(things you won't see inside a high-school setting)</li>
 	<li>Cracking</li>
 	<li>The process of cracking</li>
</ul>
<u>Expected Experience</u>
Playing through the game won't feel like "learning", and instead people who play it will want to play it over and over again not for the learning aspect, instead for the game aspect - i.e. beat the game with a higher score or pure enjoyment of the game.

<!--<u>Gameplay Theme</u>
The game will be similar to a game called "Bubble Trouble", with a unique twist.

<!--<iframe src="https://www.youtube.com/embed/hVFri15sqsE" width="854" height="480" frameborder="0" allowfullscreen="allowfullscreen" data-mce-fragment="1"></iframe>

<!--<u>Game Structure</u>
Bubble Aliens is a unique defense game - defence isn't placed by the player, instead the player defeats the ALIEN by supplying the fuel for Earth. Crude Oil bubbles will bounce around close to the player and the player has to pop the bubble the right number of times to supply the correct fuel.

<!--<u>GUI and Environment</u>
The screen will be split into two: the left side is based off a fractionating column and on the right side, an animation of the Earth's Defense that updates according to the fuel supplied by the player. Below is a rough sketch of the game area to be designed.

<!--<img class="hoverZoomLink aligncenter wp-image-1343 size-full" src="https://yuchingho.com/wp-content/uploads/BubbleAliens-Prototype.png" alt="" width="1366" height="768" />

<!--<img class="hoverZoomLink aligncenter wp-image-901 size-full" src="https://yuchingho.com/wp-content/uploads/2018/05/infoFractioningColumn.png" alt="" width="546" height="522" />

<!--<u>Features Implemented</u>
Earth's default defense will be a Ground-to-Air tower defense system, and supplying the correct fuel will call in these defenses:
<ul>
 	<li>Fuel - Battleships</li>
 	<li>Diesel - Tanks</li>
 	<li>Kerosene - Planes</li>
 	<li>Naphtha - Charging up a bomb</li>
 	<li>Gasoline/Petrol - Trucks</li>
 	<li>Refinergy Gases - Increasing the army's damage</li>
</ul>
<ul>
 	<li style="list-style-type: none;">
<ul>Player Mechanics:</ul>
</li>
</ul>
<ul>
 	<li>Arrow Key Left - Move Left</li>
 	<li>Arrow Key Right - Move Right</li>
 	<li>Arrow Key Up - Fire special weapon</li>
 	<li>Space - Shoot</li>
</ul>
<u>Gameplay, Interaction &amp; Consequences</u>
Black bubbles enter from the left hand side of the screen, and bouncing quite low to the ground and slowly to the right hand side. These bubbles represent unbroken "Crude Oil" - and they will keep bouncing until they are broken. The player controls a bunsen burner and the default weapon will be shooting out fireballs to heat up and crack the bubble. When the player cracks Crude Oil, the bubble becomes Fuel. Fuel can become Diesel, Desiel to Kerosene, etc. until the bubble becomes Refinery Gas which floats to the top. As each bubble is cracked, they bounce higher and higher and the Residue left behind will drip down.

<!--Each time a bubble touches the right-hand side of the fractionating column, the bubble is destroyed and its representative faction is added to the Earth Defense System. In terms of firepower the game doesn't make sense: battleships does the least amount of damage whilst trucks do the most. This is because of how the fractional column is set out, because getting Fuel is very easy - the bubble needs to be popped once - and getting gasoline/petrol is difficult.
To add a bit of knowledge, whenever a bubble is popped its cracking temperature is shown. This information is sourced from <a class="LB" href="https://sophiesrevisionblog.blogspot.co.uk/2014/02/gces-chemistry-unit-1-crude-oil.html">GCSE's Chemistry Unit 1</a>.

<!--<center><img class="hoverZoomLink aligncenter wp-image-902 size-full" src="https://yuchingho.com/wp-content/uploads/2018/05/infoFractioningColumnTemperature.jpg" alt="" width="546" height="400" /></center>Time is of essence here! If the player does not kill the ALIEN within the time-limit, Earth will lose! How to go about winning this game is up to the player: spawning lots and lots of battleships, or call more powerful forces and increase their damage with Refinery Gas, or charge up a bomb that will do massive damage to the ALIEN. Residue is equally important as the rest of the fractions being made as it repairs the base defense system.

<!--The player has three chances before the game is lost. To not lose thoses chances, Residue must be avoided because if in contact, Residue will drench the player. The player will become flammable for a few seconds. If the player tries to fire during that time period, the player will explode, lose a life and ultimately, slowing down Earth's Defense. To get rid of the drench, the player must wait until they are dry again before firing.