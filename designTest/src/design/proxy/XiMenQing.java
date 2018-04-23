package design.proxy;

public class XiMenQing {
	public static void main(String[] args) {
		WangPo wangpo = new WangPo(new JiaShi());
		wangpo.makeEyesWithMan();
		wangpo.happyWithMan();
		
	}

}
