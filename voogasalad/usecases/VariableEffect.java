package usecases;

public class VariableEffect implements Effect{

	double spriteValue;
	double increaseAmount;
	
	public VariableEffect(double spriteInput, double increaseInput) {
		spriteValue = spriteInput;
		increaseAmount = increaseInput;
		// TODO Auto-generated constructor stub
	}

	@Override
	public double performEffect() {
		// TODO Auto-generated method stub
		return increaseD(spriteValue,increaseAmount);
	}

	public double increaseD(double value, double increment) {
		// TODO Auto-generated method stub
		return value+increment;
	}

}
