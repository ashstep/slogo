package turtlecommands;

import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class ShowTurtle extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		return new TurtleState(state.getX(), state.getY(), state.getAngle(), state.isPenDown(), true);
	}

	@Override
	public int getNumArgs() {
		return 0;
	}
}
