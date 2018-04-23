package design.multation;

import java.util.ArrayList;
import java.util.Random;

public class Emperor {
	private static final int MAX_COUNT_EMPEROR = 2;
	private static ArrayList<String> emperorInfoList = new ArrayList<String>(
			MAX_COUNT_EMPEROR);
	private static ArrayList<Emperor> emperorList = new ArrayList<Emperor>(
			MAX_COUNT_EMPEROR);
	private static int countNumOfEmperor = 0;

	private Emperor(String info) {
		emperorInfoList.add(info);
	}

	static {
		for (int i = 0; i < MAX_COUNT_EMPEROR; i++) {
			emperorList.add(new Emperor("emperor" + i));
		}
	}

	public static Emperor getIntance(String params) {
		if (params != null) {
			countNumOfEmperor = 0;
		} else {
			Random random = new Random();
			countNumOfEmperor = random.nextInt(MAX_COUNT_EMPEROR);
		}

		return emperorList.get(countNumOfEmperor);
	}

	public void getEmperorInfo() {
		System.out.println(emperorInfoList.get(countNumOfEmperor));
	}
}
