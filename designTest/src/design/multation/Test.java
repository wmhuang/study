package design.multation;

public class Test {
	public static void main(String[] args) {
		Emperor emperor = null;
		for (int i = 0; i < 10; i++) {
			if (i % 3 == 0) {
				System.out.println("�����й����� ��һ��Ҫ֧��0�Żʵ�");
				emperor = Emperor.getIntance(new String("AA"));
			} else {
				System.out.println("�Ҿ�����ν�ˣ�˭�ǻʵ۸��ҹ�ϵ����");
				emperor = Emperor.getIntance(null);

			}
			emperor.getEmperorInfo();
		}
	}

}
