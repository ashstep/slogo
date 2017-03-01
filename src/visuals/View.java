package visuals;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is the class which controls the display of the GUI. It puts together all
 * the components that make up the screen.
 * 
 * @author Harry Liu, Christian Martindale
 **/

public class View implements ExternalUserInterface {

	private BorderPane BP;
	private ScrollPane SP;
	private Scene theScene;
	private Stage theStage;
	private int WIDTH = 800;
	private int HEIGHT = 600;
	private int SPACING = 10;
	private InputView inputView;
	private ColorPicker backgroundColorChooser;
	private ColorPicker strokeColorChooser;
	private Canvas TurtleView;
	private GraphicsContext gc;

	public View(Stage myStage) {

		inputView = new InputView(WIDTH, HEIGHT);

		theStage = myStage;
		BP = new BorderPane();
		SP = new ScrollPane();
		SP.setContent(initializeRightMenu());
		SP.setHbarPolicy(ScrollBarPolicy.NEVER);
		SP.setFitToWidth(true);

		inputView.setBackground(backgroundColorChooser, BP);
		BP.setRight(SP);
		TurtleView = initializeGraphicContent();
		inputView.setStroke(strokeColorChooser, gc);
		moveline(200,200);
		BP.setLeft(TurtleView);

		theScene = new Scene(BP, WIDTH, HEIGHT);
		theScene.getStylesheets().add(View.class.getResource("styles.css").toExternalForm());
		theStage.setScene(theScene);
		theStage.show();
	}

	
	public StackPane initializeMenu(){
		StackPane Menu = new StackPane();
		Menu.setMinHeight(WIDTH/4);
		
		
		Button submit = new Button("Submit");
		submit.setDefaultButton(true);
		StackPane.setAlignment(submit, Pos.BOTTOM_CENTER);
		submit.setMaxWidth(WIDTH/3);
		
		
		TextArea userInput = new TextArea();
		userInput.setMaxSize(WIDTH/3, WIDTH/4);
		StackPane.setMargin(userInput, new Insets(WIDTH/100,WIDTH/100,WIDTH/100,WIDTH/100));
		
		
		userInput.setPromptText("Enter Your Command");
		
		Menu.getChildren().addAll(userInput, submit);
		
		
		return Menu;
	}

	/**
	 * shows an error message popup
	 * @param errorMsg the message that will be displayed in the error popup
	 */



	/**
	 * Initialize the right side which has all the controls for the GUI
	 * @return
	 */
	private VBox initializeRightMenu() {
		VBox RightMenu = new VBox(SPACING);
		
		Label backgroundLabel = new Label("Change the Background Color!"); //Change to ResourceBundle Later
		Label lineColorLabel = new Label ("Change the color of the line");

		backgroundColorChooser = inputView.initializeColorPicker();
		strokeColorChooser = inputView.initializeColorPicker();	
		
		RightMenu.getChildren().addAll(inputView.initializeTextArea(), backgroundLabel, backgroundColorChooser, lineColorLabel, strokeColorChooser);
		return RightMenu;
	}
	
	/**
	 * Initialize the left size of the BorderPane (the Canvas) which displays the turtle movements
	 * @return TurtleView
	 */
	private Canvas initializeGraphicContent() {
		TurtleView = new Canvas (WIDTH*0.5, HEIGHT);
		gc = TurtleView.getGraphicsContext2D();
		return TurtleView;
	}
	
	/**
	 * Filler method just to test if the line is displayed properly
	 * NOT ACTUALLY GOING TO BE USED THIS WAY.
	 * @param x
	 * @param y
	 */
	private void moveline(double x, double y){
		gc.moveTo(50, 50);
		gc.lineTo(x, y);
		gc.stroke();
	}
	
	@Override
	public void passString(String input) {
		// TODO Auto-generated method stub

	}

	private void updateTurtle(TurtleState turtle){
		System.out.print("Hello");
	}
	
	@Override
	public void newTurtleState() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createErrorMessage(String whatHappened) {
		Alert alert = new Alert(AlertType.ERROR, "Error: "+ whatHappened);
		alert.showAndWait();

	}

}