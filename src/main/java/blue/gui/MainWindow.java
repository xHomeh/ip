package blue.gui;

import java.util.HashSet;
import java.util.Set;

import blue.Blue;
import blue.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static final Set<String> EXIT_WORDS = new HashSet<>(Set.of("bye", "exit", "quit", "q"));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Blue blue;
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Lindon.png"));
    private Image blueImage = new Image(this.getClass().getResourceAsStream("/images/LilBlue.png"));

    /**
     * Initialises the main window and displays a greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String greeting = ui.greet();
        dialogContainer.getChildren().add(
                DialogBox.getBlueDialog(greeting, blueImage)
        );
    }

    /** Injects the Blue instance */
    public void setBlue(Blue b) {
        blue = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();

        if (EXIT_WORDS.contains(input.toLowerCase())) {
            // Show goodbye message from Blue
            String goodbye = blue.getResponse("bye");
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBlueDialog(goodbye, blueImage)
            );

            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();

            userInput.clear();
            userInput.setDisable(true); // prevent further input
            return;
        }

        String response = blue.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBlueDialog(response, blueImage)
        );
        userInput.clear();
    }
}
