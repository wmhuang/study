package design.strategy;

public class Test {
	public static void main(String[] args) {
		Context c = new Context(new Second());
		c.opreate();
	}
}
