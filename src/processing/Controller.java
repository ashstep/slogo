package processing;

import visuals.View;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import visuals.SplashPage;

public class Controller {
	private Stage theStage;
	private View theView;
	private ResourceBundle myResourceBundle;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private String language = "Chinese";
	
	public Controller(Stage s){
		theStage = s;
		myResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		Button start = new Button(myResourceBundle.getString("StartPrompt"));
		start.setOnAction(event -> makeView());
		SplashPage splash = new SplashPage(start);
		
		theStage.setScene(splash.getScene());
		theStage.show();
	}
	
	private void makeView(){
		
		Button submit = new Button(myResourceBundle.getString("SubmitPrompt"));
		submit.setMaxWidth(View.WIDTH / 2);
		submit.setOnAction(e -> System.out.println(theView.getCommandString())); //TODO: Replace with controller's main method

		theView = new View(submit, myResourceBundle);

		theStage.setScene(theView.getScene());
	}
}
