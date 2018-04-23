package design.strategy;

public class First implements IsStrategy {

	@Override
	public void operate() {
		System.out.println("第一个策略");
	}

}
