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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import turtle.Turtle;
import turtle.TurtleState;

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
	private GraphicsContext myTurtleDrawer;
	private Image myTurtleImage;
	private ImageView myTurtle;
	private double turtleXPos;
	private double turtleYPos;
	private double turtleAngle;

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
		myTurtleDrawer = TurtleView.getGraphicsContext2D();
		turtleXPos = WIDTH/4;
		turtleYPos = HEIGHT/2;
		turtleAngle = 0;
		
		myTurtle = new ImageView(myTurtleImage);
		return TurtleView;
	}
	
	/**
	 * Filler method just to test if the line is displayed properly
	 * NOT ACTUALLY GOING TO BE USED THIS WAY.
	 * @param x
	 * @param y
	 */
	private void drawTurtlePath(double x, double y, boolean pen){
		myTurtleDrawer.moveTo(x,y);
		if(pen) {
			myTurtleDrawer.lineTo(x, y);		
			myTurtleDrawer.stroke();
		}
	}
	
	@Override
	public void passString(String input) {
		// TODO Auto-generated method stub

	}

	
	
	private void updateTurtle(TurtleState newTurtle){
		turtleXPos = newTurtle.getX();
		turtleYPos = newTurtle.getY();
		turtleAngle = newTurtle.getAngle();
		myTurtle.setX(turtleXPos);
		myTurtle.setY(turtleYPos);
		myTurtle.setRotate(turtleAngle); //how does setRotate work? absolute or relative angles?
		
		drawTurtlePath(turtleXPos, turtleYPos, newTurtle.isPenDown());
		
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