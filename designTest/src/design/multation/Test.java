package design.multation;

public class Test {
	public static void main(String[] args) {
		Emperor emperor = null;
		for (int i = 0; i < 10; i++) {
			if (i % 3 == 0) {
				System.out.println("我是有骨气的 我一定要支持0号皇帝");
				emperor = Emperor.getIntance(new String("AA"));
			} else {
				System.out.println("我就无所谓了，谁是皇帝跟我关系不大");
				emperor = Emperor.getIntance(null);

			}
			emperor.getEmperorInfo();
		}
	}

}
