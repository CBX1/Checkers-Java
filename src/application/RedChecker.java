package application;

public class RedChecker extends Checker{ 
	Type Type;
	public RedChecker(Position p, Type c) {
		super(p,c);
	}

	@Override
	public String toString() {
		return "R ";
	}
	


}
