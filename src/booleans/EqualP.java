package booleans;

import java.util.List;

import turtle.ArgumentNumberException;
import turtle.Command;
import turtle.TurtleState;

public class EqualP extends Command {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(args.get(0) == args.get(1) ? 1 : 0);
		return state;
	}

	@Override
	public int getNumArgs() {
		// TODO Auto-generated method stub
		return 0;
	}

}
