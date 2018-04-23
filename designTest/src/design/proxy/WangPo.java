package design.proxy;

public class WangPo implements KindWomen {
	private KindWomen kindWomen;

	public WangPo() {
		this.kindWomen = new Panjinlian();
	}

	public WangPo(KindWomen kindWomen) {
		this.kindWomen = kindWomen;
	}

	@Override
	public void makeEyesWithMan() {

		this.kindWomen.makeEyesWithMan();

	}

	@Override
	public void happyWithMan() {
		this.kindWomen.happyWithMan();

	}

}
