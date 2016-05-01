package usecases;

public class VariableEffectUseCase implements EffectUseCase{

	private double spriteValue;
	private double increaseAmount;
	
	public VariableEffectUseCase(double spriteInput, double increaseInput) {
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
		return value +(increment);
	}

}
