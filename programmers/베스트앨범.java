package programmers;

import java.util.*;

public class 베스트앨범 {

	class Genre implements Comparable<Genre> {
		String genre;
		int playCountSum;

		Genre(String genre, int playCountSum) {
			this.genre = genre;
			this.playCountSum = playCountSum;
		}

		@Override
		public int compareTo(Genre o) {
			return -1 * (this.playCountSum - o.playCountSum);
		}
	}

	class Song implements Comparable<Song> {
		int idx, playCount;

		Song(int idx, int playCount) {
			this.idx = idx;
			this.playCount = playCount;
		}

		@Override
		public int compareTo(Song o) {
			if (this.playCount != o.playCount) {
				return -1 * (this.playCount - o.playCount);
			} else {
				return this.idx - o.idx;
			}
		}
	}

	public int[] solution(String[] genres, int[] plays) {
		Map<String, Integer> genreMap = new HashMap<String, Integer>();
		Map<String, LinkedList<Song>> songListMap = new HashMap<String, LinkedList<Song>>();
		
		//재생횟수 총합을 genreMap에 넣어주고, 각 장르에 대한 재생횟수와 인덱스를 map에 넣어준다 
		for (int i = 0; i < genres.length; i++) {
			if (genreMap.containsKey(genres[i])) {
				genreMap.put(genres[i], genreMap.get(genres[i]) + plays[i]);
			} else {
				genreMap.put(genres[i], plays[i]);
				songListMap.put(genres[i], new LinkedList<Song>());
			}
			songListMap.get(genres[i]).add(new Song(i, plays[i]));
		}

		//재생횟수와 장르 이름을 genreList에 넣어준다. 
		ArrayList<Genre> genreList = new ArrayList<Genre>();
		for (String key : genreMap.keySet()) {
			genreList.add(new Genre(key, genreMap.get(key)));
			Collections.sort(songListMap.get(key));
		}
		//재생횟수가 많은 순으로 정렬해준다. 
		Collections.sort(genreList);

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < genreList.size(); i++) {
			for (int j = 0; j < songListMap.get(genreList.get(i).genre).size(); j++) {
				if (j == 2)
					break;
				list.add(songListMap.get(genreList.get(i).genre).get(j).idx);
			}
		}
		
		int[] answer = new int[list.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}
}