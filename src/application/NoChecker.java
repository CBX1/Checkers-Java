package application;

public class NoChecker extends Checker {
	Type Type;
	public NoChecker(Position p, Type c) {
		super(p,c);
	}
	
	@Override
	public String toString() {
		return "  ";
	}

}
