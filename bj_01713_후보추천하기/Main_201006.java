package bj_01713_후보추천하기;

import java.io.*;
import java.util.*;

public class Main_201006 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> idxMap = new HashMap<Integer, Integer>();		
		
		int N = Integer.parseInt(br.readLine());
		int size = Integer.parseInt(br.readLine());
		
//		if (size == 0) return;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < size; i++) {
			int temp = Integer.parseInt(st.nextToken());
			
			if (countMap.containsKey(temp)) {
				countMap.put(temp, countMap.get(temp) + 1);
			} else {
				if (countMap.size() >= N) {
					int minVal = Integer.MAX_VALUE;
					int minIdx = Integer.MAX_VALUE;
					int minIdxKey = 0;
					for (Integer key : countMap.keySet()) {
						if (minVal > countMap.get(key)) {
							minVal = countMap.get(key);
							minIdxKey = key;
						}
					}

					for (Integer idxKey : idxMap.keySet()) {
						if (minVal == countMap.get(idxKey)) {
							if (minIdx > idxMap.get(idxKey)) {
								minIdx = idxMap.get(idxKey);
								minIdxKey = idxKey;
							}
						}
					}
					countMap.remove(minIdxKey);
					idxMap.remove(minIdxKey);
				}
				countMap.put(temp, 1);
				idxMap.put(temp, i);
			}
		}
		
		List<Integer> list = new ArrayList<>(countMap.keySet());
		Collections.sort(list);
		for (Integer key : list)
			sb.append(key + " ");
		System.out.println(sb);
	}
}