package design.factory;

public class HumanFactory {
	public static Human getHuman(Class<?> c) {
		Human human = null;
		try {
			human = (Human) Class.forName(c.getName()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return human;
	}

}
