package design.strategy;

public class Third implements IsStrategy {

	@Override
	public void operate() {
		System.out.println("第三个策略");
	}
}
