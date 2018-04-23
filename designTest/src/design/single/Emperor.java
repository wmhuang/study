package design.single;

public class Emperor {
	private static final Emperor emperor = new Emperor();
	private static int  randNum = (int)(Math.random()*100);
	private Emperor() {

	}

	public synchronized static Emperor getInstance() {
		 return emperor;
	}

	public static void emperorInfo(){
		System.out.println("�Ҿ���ĳĳĳ�ʵ�"+randNum);
	}
}
