package design.factory;

public class WhiteMan implements Human {

	@Override
	public void cry() {
		System.out.println("白种人哭");
	}

	@Override
	public void laugh() {
		System.out.println("白种人笑");

	}

	@Override
	public void talk() {
		System.out.println("白种人说话");

	}

}
