package turtle;

import java.util.ArrayList;
import java.util.List;

import parser.Node;

/**
 * Generic command class
 * @author Vishnu Gottiparthy
 *
 */
public abstract class Command {
	
	private List<Double> args;
	private double returnVal;
	
	/**
	 * Runs the command and sets its return value
	 * @param state The current state of the <code>Turtle</code>
	 * @return The next state of the <code>Turtle</code>
	 */
	public abstract TurtleState run(TurtleState state) throws ArgumentNumberException;
	
	/**
	 * Checks if the allowed number of arguments is supplied
	 * @param allowed Number of allowed arguments
	 * @param args Array of arguments
	 */
	protected void checkArgs() throws ArgumentNumberException {
		if(args == null){
			args = new ArrayList<Double>();
		}
		if(args.size() != getNumArgs()){
			throw new ArgumentNumberException("Got "+args.size()+" arguments, expected " + getNumArgs());
		}
	}
	
	/**
	 * Gets the arguments list of this <code>Command</code>
	 * @return The arguments list
	 */
	public List<Double> getArgs(){
		return args;
	}
	
	public void treeArgs(Node node){
		ArrayList<Node> children = node.getChildren();
		for(Node n : children){
			if(!n.checkifCommand()){
				node.getCommandObject().addArg(Double.parseDouble(n.getCommand()));
			}
			else{
				treeArgs(n);
			}
		}
	}
	
	public void addArg(double input){
		if(args == null){
			args = new ArrayList<Double>();
		}
		args.add(input);
	}
	
	/**
	 * Returns the number of allowed arguments
	 * @return The number of allowed arguments
	 */
	public abstract int getNumArgs();
	
	/**
	 * Sets the return value of the command
	 * @param val Return value to set
	 */
	protected void setReturnVal(double val){
		returnVal = val;
	}
	
	/**
	 * Gets the return value of the command	
	 * @return The return value of the command
	 */
	public double getReturnVal(){
		return returnVal;
	}
}
