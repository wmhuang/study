package cj;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		File file = new File("D:/chengjing/data");
		File files[]= file.listFiles();
		ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>> ();
		for(int i=0;i<files.length;i++){
			System.out.println(files[i].getName());
			result.addAll(ExcelUtil.readExcel(files[i]));
		}

		List<Map<String, Object>> a = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> b = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> c = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> d = new ArrayList<Map<String, Object>>();


		for (int i = 0; i < result.size(); i++) {
			// 获取一行数据
			List<Object> l = result.get(i);
			String pcComeKey = (String) l.get(1);
			if (pcComeKey != null && !"null".equals(pcComeKey)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("key", pcComeKey);
				map.put("value", (int) l.get(2));
				a.add(map);
			}
			String pcSuccessKey = (String) l.get(3);
			if (pcSuccessKey != null && !"null".equals(pcSuccessKey)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("key", pcSuccessKey);
				map.put("value", (int) l.get(4));
				b.add(map);
			}

			String mobelComeKey = (String) l.get(5);
			if (mobelComeKey != null && !"null".equals(mobelComeKey)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("key", mobelComeKey);
				map.put("value", (int) l.get(6));
				c.add(map);
			}

			String mobelSuccessKey = (String) l.get(7);
			if (mobelSuccessKey != null && !"null".equals(mobelSuccessKey)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("key", mobelSuccessKey);
				map.put("value", (int) l.get(8));
				d.add(map);
			}
		}

		Map<String, Integer> mapa = getDataMap(a);
		Map<String, Integer> mapb = getDataMap(b);
		Map<String, Integer> mapc = getDataMap(c);
		Map<String, Integer> mapd = getDataMap(d);
		outputExcel(mapa,"pc端访问量");
		outputExcel(mapb,"pc端成交量");
		outputExcel(mapc,"无线端访问量");
		outputExcel(mapd,"无线端成交量");
		
	}

	public static Map<String, Integer> getDataMap(List<Map<String, Object>> a) {
		List<String> aa = new ArrayList<String>();
		Map<String, Integer> aaresult = new HashMap<String, Integer>();

		for (int i = 0; i < a.size(); i++) {
			Map<String, Object> map = a.get(i);
			String key = (String) map.get("key");
			int value = (int) map.get("value");
			if (aa.contains(key)) {
				int oldValue = aaresult.get(key);
				aaresult.put(key, oldValue + value);
			} else {
				aaresult.put(key, value);
				aa.add(key);
			}
		}
		return aaresult;
	}
	
	public static void outputExcel(Map<String, Integer> map,String fileName){
		String resultDir = "D:/chengjing/result";
		File dir = new File(resultDir);
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		ArrayList<ArrayList<Object>> output = new ArrayList<ArrayList<Object>>();
		Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> entry = it.next();
			ArrayList<Object> aaa = new ArrayList<Object>();
			aaa.add(entry.getKey());
			aaa.add(entry.getValue());
			output.add(aaa);
		}
		ExcelUtil.writeExcel(output, "D:/chengjing/result/"+fileName+".xls");
	}
}
