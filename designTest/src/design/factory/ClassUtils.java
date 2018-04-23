package design.factory;

import java.util.ArrayList;
import java.util.List;

public class ClassUtils {

	public static void main(String[] args) {
		getInterFaceChildClass(Human.class);
	}

	public static List<Class> getInterFaceChildClass(Class c) {
		List<Class> list = new ArrayList<Class>();
		
		String packageName = c.getPackage().getName();
		System.out.println(packageName);

		return list;
	}
}
