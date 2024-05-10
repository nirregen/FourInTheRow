import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
/**
 * Controller class for the Four in the Row game.
 */
public class FourInTheRowController {

    @FXML private Button ClearButton;
    @FXML private GridPane bordgame;
    private final int columNumber = 7;
    private final int rowNumber = 6;
    private final int radiosOfTheCircle = 22;// the radios of the circle to be in the middle
    private int player = 1;// which player is play now player one is 1 player two is 2
    private Button bts[];
    private game gameLogic;
    /**
     * Initializes the controller after all FXML elements have been loaded.
     */
    @FXML
    void initialize() {
        gameLogic = new game(rowNumber, columNumber);//the game bord logic 
        createButton();//create the button we need 
        creategrid();//create the grid
        bordgame.setGridLinesVisible(true);

    }
    /**
     * Creates buttons for each column to allow players to make moves.
     */
    private void createButton() {
        bts = new Button[columNumber];
        for (int i = 0; i < columNumber; i++) {
            bts[i] = new Button((i + 1) + "");
            bts[i].setPrefSize(bordgame.getPrefWidth() / columNumber, bordgame.getPrefHeight() / columNumber);
            bordgame.add(bts[i], i, columNumber - 1);//place them form left bottom
            int columnIndex = i;
            bts[i].setOnAction(event -> createcircle(columnIndex));
        }
    }
    /**
     * Creates the grid layout for the game board.
     */
    private void creategrid() {
        for (int i = 0; i < columNumber; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(bordgame.getPrefWidth() / rowNumber);
            bordgame.getColumnConstraints().add(column);
        }

        for (int i = 0; i < rowNumber; i++) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(bordgame.getPrefHeight() / columNumber); // Set the preferred height for each row
            bordgame.getRowConstraints().add(row);
        }
    }

    /**
     * Event handler for when a player makes a move by clicking a column button.
     * 
     * @param columnIndex The index of the column where the move is made.
     */
    @FXML
    public void createcircle(int columnIndex) {
        // check if it turn player one or player two
        if (player == 1) {// player one play
            creatColerCircle(columnIndex, Color.RED);//create red circle 
            gameLogic.addPlayerCercle(columnIndex, player);// Update game logic for player 1
            player = 2;// give the next turn to player two
        } else {// player two play
            creatColerCircle(columnIndex, Color.BLUE);// Create a blue circle
            gameLogic.addPlayerCercle(columnIndex, player);// Update game logic for player 2
            player = 1;// give the next turn to player one
        }
        // check for winer or draw
        int winner = gameLogic.checkWin();
        displayWinMessage(winner);
        DisableButton(columnIndex); // Disable the clicked button if the column is full
    }

    /**
     * Creates a colored circle in the specified column.
     * @param column The index of the column.
     * @param color The color of the circle.
     */
    private void creatColerCircle(int column,Color color){
        int row = gameLogic.findEmptyRow(column);// Find the empty row in the specified column
        if (row != -1) {// insure that have empty place slot
            Circle circle = new Circle(); // Create a new circle
            circle.setRadius(radiosOfTheCircle); // Set the radius of the circle
            circle.setFill(color); // Set the fill color to red

            // Set the alignment of the circle within its cell to center
            GridPane.setHalignment(circle, HPos.CENTER);
            GridPane.setValignment(circle, VPos.CENTER);

            bordgame.add(circle, column, row); // Add the circle to the GridPane
        }
    }

    /**
     * Displays a message indicating the winner or a draw.
     * @param winner The identifier of the winning player (1 or 2), or 0 if it's a draw.
     */
    private void displayWinMessage(int winner) {
        if (winner == 1) {
            // Player 1 wins
            showMessage("Player 1 Wins!", "Congratulations, Player 1 has won the game!");
            disableOrEnabaleAllButton(true);
        } else if (winner == 2) {
            // Player 2 wins
            showMessage("Player 2 Wins!", "Congratulations, Player 2 has won the game!");
            disableOrEnabaleAllButton(true);
        } else {
            // No winner, check for draw
            if (gameLogic.isBoardFull()) {
                // Game is a draw
                showMessage("Game Over", "It's a draw! Nobody wins.");
                disableOrEnabaleAllButton(true);
            }
        }
    }

    /**
     * 
     * @param title
     * @param message
     */
    private void showMessage(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Clears the game board and resets the game state.
     * @param event The action event triggered by clicking the Clear button
     */
    @FXML
    private void clearButtonPressed(ActionEvent event) {
        bordgame.getChildren().removeIf(node -> node instanceof Circle);//remove the circle objects
        gameLogic.cleanbord();
        player = 1;//make the player one to play
        disableOrEnabaleAllButton(false);
    }

    /**
     * Disables or enables all column buttons.
     * @param disable If true, buttons will be disabled; otherwise, they will be enabled.
     */
    private void disableOrEnabaleAllButton(boolean disable) {
        for (int i = 0; i < bts.length; i++) {
            bts[i].setDisable(disable);
        }
    }
    /**
     *  Disables the button for the specified column if the column is full.
     * @param columnIndex The index of the column.
     */
    private void DisableButton(int columindex) {
        if (gameLogic.getValueAt(0, columindex) != 0) {
            bts[columindex].setDisable(true);
        }
    }

}