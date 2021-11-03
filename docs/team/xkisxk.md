# Jiang Xing Kai - Project Portfolio Page

## Project: CardLi
CardLI is a Command Line Interface (CLI) desktop app designed to help users manage and test their flashcards. 
CardLI can help users keep track of all their flashcards and to test their knowledge. 
It is written in Java and contains about 4kLoC.

Given below are my contributions to the project. 

- Review Feature:
  - Tests all cards that scored less than 50% across all tests combined.
  - This feature allows users to test themselves on cards that they need help with, increasing the efficiency
  of their studying.
  - The implementation was challenging due to the need of requiring the need for keeping track of 
  the user score and total score of each flashcard, which led to the addition of view flashcard statistics
  feature. Another reason is that the review feature, like test feature, works quite differently compared
  to the other features. This required many revisions to get it working.


- Delete Feature:
  - Deletes the flashcards given an index
  - This feature allows users to remove flashcards that they don't need or added by mistake.
  - Originally the delete feature also allowed `String` inputs but because of problems with duplicate
  cards, it was discarded as there was not enough time to implement an elegant way to allow the user
  to differentiate them.
  

- View Statistics Feature:
  - Allows users to view a single of all previous test scores and all accumulated flashcard scores.
  - This feature allows users to keep track of their previous tests, and focus on the tests that
  require more work.
  - This required a `TestHistory` class that keeps track of all tests, and as mentioned in the Review Feature, 
  expanded the `Flashcard` class to keep track of a user score and total score. 
  

- Code Contributed: 
[RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=xkisxk&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=xkisxk&tabRepo=AY2122S1-CS2113T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


- Enhancements to existing features:
  - Wrote Junit tests to existing features, mainly `TestManager`, `TestUi` and `TestHistory`
  - - Allow testing for individual decks or all decks, and repackaged it


- Documentation:
  - User Guide: 
    - Added documentation features for features `viewtest`, `viewfc`, `review`, `delete`
    [#75](https://github.com/AY2122S1-CS2113T-F12-1/tp/commit/a1d5f600678dce14bbf3438fbfbe8e78641ef377)
  - Developer Guide: 
    - Wrote implementation details of `test` feature including the UML diagrams. 
    [#89](https://github.com/AY2122S1-CS2113T-F12-1/tp/commit/1c3adf0706446b7a775c1ba744394abfbf5edba8)

- Community:
  - Reported bugs and suggestions to improve other team's 
  [developer guide](https://github.com/nus-cs2113-AY2122S1/tp/pull/1/files)
  - Tested and reported bugs for other team's program
  Examples: [1](https://github.com/xkisxk/ped/issues/1) ,[2](https://github.com/xkisxk/ped/issues/2)
  , [3](https://github.com/xkisxk/ped/issues/3)


## Contributions to User Guide

## Contributions to Developer Guide
