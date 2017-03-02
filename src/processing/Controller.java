package processing;

import visuals.View;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import parser.CommandParser;
import parser.Node;
import turtle.ArgumentNumberException;
import turtle.Command;
import turtle.CommandProcessException;
import turtle.Turtle;
import turtlecommands.PenUp;
import visuals.SplashPage;

public class Controller {
	private Stage theStage;
	private View theView;
	private ResourceBundle myResourceBundle;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private CommandParser parser;
	private Turtle turtle;
	private String language = "English";
	
	public Controller(Stage s){
		theStage = s;
		parser = new CommandParser();
		turtle = new Turtle();
		
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
		submit.setOnAction(e -> parseCommands(theView.getCommandString()));
		theView = new View(submit, myResourceBundle);
		theStage.setScene(theView.getScene());
	}
	
	private void parseCommands(String cmd){
		parser.parseInputtedCommand(cmd);
		Node node = parser.buildTree();
		Command command = node.getCommandObject();
		PenUp p = (PenUp) command;
		System.out.println(turtle.getState().isPenDown());
		try {
			turtle.process(p);
		} catch (CommandProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArgumentNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(turtle.getState().isPenDown());
	}
}
