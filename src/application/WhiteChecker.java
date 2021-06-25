package application;

public class WhiteChecker extends Checker{
	Type Type;
	public WhiteChecker(Position p, Type c) {
		super(p,c);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "W ";
	}

}
