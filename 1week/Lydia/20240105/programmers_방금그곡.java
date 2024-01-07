import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {
    public static class Type {
		private String start;
		private String end;
		private String title;
		private String music;
		
		public Type(String start, String end, String title, String music) {
			this.start = start;
			this.end = end;
			this.title = title;
			this.music = music;
		}
	}
	public static class Music {
		private String title;
		private String music;
		
		public Music(String title, String music) {
			this.title = title;
			this.music = music;
		}
	}
    public static class MyType implements Comparable<MyType>{
		private String title;
		private int len;
		
		public MyType(String title, int len) {
			this.title = title;
			this.len = len;
		}
		
		@Override
		public int compareTo(MyType m) {
			return m.len - this.len; // 내림차순
		}
	}
    public String solution(String m, String[] musicinfos) {
        String answer = "";
		ArrayList<Type> arr = new ArrayList<>();
		ArrayList<Music> resultArr = new ArrayList<>();
		m = changeCode(m);
		PriorityQueue<MyType> pq = new PriorityQueue<>();
		
		for(int i=0; i<musicinfos.length; i++) {
			String[] s = musicinfos[i].split(",");
			s[3] = changeCode(s[3]); // # 변환
			arr.add(new Type(s[0], s[1], s[2], s[3]));
		}
		
		int hour = 0;
		int min = 0;
		int sum = 0;
		
		for(Type t: arr) {
			String[] startTimeArr = t.start.split(":");
			int startHour = Integer.parseInt(startTimeArr[0]);
			int startMin = Integer.parseInt(startTimeArr[1]);
			
			String[] endTimeArr = t.end.split(":");
			int endHour = Integer.parseInt(endTimeArr[0]);
			int endMin = Integer.parseInt(endTimeArr[1]);
			
			if(startHour > endHour) {
				hour = 23 - startHour;
				min = 59 - startMin;
			} else {
				hour = endHour - startHour;
				min = endMin - startMin;
			}
			
			sum = hour*60 + min;
			String newMusic = "";
			
			for(int i=0; i<sum; i++) {
				newMusic += t.music.charAt(i%(t.music.length()));
			}
			
			resultArr.add(new Music(t.title, newMusic));
		}
		
		for(Music r : resultArr) {
			if(r.music.contains(m)) {
				pq.add(new MyType(r.title, r.music.length()));
				answer = r.title;
			}
		}
		
		if(pq.size()==0) {
			answer = "(None)";
		} else {
			answer = pq.poll().title;
		}
		
		return answer;
    }
    public static String changeCode(String s) {
		s = s.replaceAll("C#", "H");
		s = s.replaceAll("D#", "I");
		s = s.replaceAll("E#", "J");
		s = s.replaceAll("F#", "K");
        s = s.replaceAll("G#", "L");
		String newS = s.replaceAll("A#", "M");
		return newS;
	}
}
