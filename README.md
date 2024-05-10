# Four in the Row Game

This repository contains Java code for a simple Four in the Row game implemented using JavaFX.

## How to Play

1. Run the application.
2. Click on the numbered buttons at the top to drop a colored circle into the corresponding column.
3. The objective is to be the first player to connect four of your colored circles in a row, column, or diagonal.

## Code Overview

### Controller Class

The `FourInTheRowController` class serves as the controller for the game. It handles button clicks, updates the game board, and checks for a winner or a draw.

### Methods

- `initialize()`: Initializes the game board and logic.
- `createButton()`: Dynamically creates buttons for each column.
- `creategrid()`: Sets up the grid layout for the game board.
- `createcircle(int columnIndex)`: Event handler for player moves, creates colored circles.
- `displayWinMessage(int winner)`: Displays a message indicating the winner or a draw.
- `showMessage(String title, String message)`: Displays an alert message.
- `clearButtonPressed(ActionEvent event)`: Clears the game board and resets the game state.
- `disableOrEnabaleAllButton(boolean disable)`: Disables or enables all column buttons.
- `DisableButton(int columnIndex)`: Disables the button for a column if it's full.

### Other Components

- `gameLogic`: Manages the game logic, such as checking for a win or a draw.

## Dependencies

- JavaFX: For building the graphical user interface.

## Running the Application

Ensure you have JavaFX set up in your development environment. Compile and run the `FourInTheRowController` class to start the game.

