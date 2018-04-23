package design.factory;

public class Test {
	public static void main(String[] args) {
		Human human = HumanFactory.getHuman(WhiteMan.class);
		human.cry();
	}

}
