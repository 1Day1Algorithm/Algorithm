import java.io.*;
import java.util.*;

public class Main {
	static int[][] place;
	static ArrayList<Pos> cctvArr;
	static int N, M;
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌
	static int[] dc = {0, 1, 0, -1};
	static int minCnt = Integer.MAX_VALUE; // 사각지대의 최소 크기
	public static class Pos {
		int r;
		int c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static class Spot {
		int r;
		int c;
		int d; // 방향
		public Spot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		place = new int[N][M];
		cctvArr = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				place[i][j] = Integer.parseInt(st.nextToken());
				
				if(isCCTV(place[i][j])) cctvArr.add(new Pos(i, j));
			}
		}
	
		int[] direction = new int[cctvArr.size()];
		getCCTVDirection(direction, 0, cctvArr.size());
		
		System.out.println(minCnt);
	}
	
	public static void getBlindSpot(int[] direction) {
		Queue<Spot> q = new LinkedList<>();
		boolean[][] isNotBlind = new boolean[N][M];
		boolean[][] isVisited = new boolean[N][M];
		
		Pos cctv;
		Spot spot;
		int nextR, nextC;
		int[] isCheckDir;
		for(int i=0; i<direction.length; i++) {
			cctv = cctvArr.get(i);
			isVisited = new boolean[N][M];
			isVisited[cctv.r][cctv.c] = true;
			
			isCheckDir = getNextDir(place[cctv.r][cctv.c], direction[i]);
			
			for(int j=0; j<isCheckDir.length; j++) {
				q.offer(new Spot(cctv.r, cctv.c, isCheckDir[j]));
			}
			
			while(!q.isEmpty()) {
				spot = q.poll();
				
				nextR = spot.r + dr[spot.d];
				nextC = spot.c + dc[spot.d];
				
				if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) continue;
				if(isWall(place[nextR][nextC])) continue;
				if(!isVisited[nextR][nextC]) {
					isVisited[nextR][nextC] = true;
					isNotBlind[nextR][nextC] = true;
					q.offer(new Spot(nextR, nextC, spot.d));
				}
				
			}
		}
		
		int cnt = 0; // 사각지대 크기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!isNotBlind[i][j] && !isWall(place[i][j]) && !isCCTV(place[i][j])) cnt += 1;
			}
		}
		
		minCnt = Math.min(minCnt, cnt);
	}
	
	public static void getCCTVDirection(int[] direction, int idx, int target) {
		if(idx == target) {
			getBlindSpot(direction);
			return;
		}
		
		for(int i=0; i<4; i++) {
			direction[idx] = i;
			getCCTVDirection(direction, idx+1, target);
		}
	}
	
	public static int[] getNextDir(int cctvType, int direction) {
		int[][] nextDir = {{}, {0}, {0, 2}, {0, 1}, {0, 1, 3}, {0, 1, 2, 3}};
		
		for(int i=0; i<nextDir[cctvType].length; i++) {
			nextDir[cctvType][i] = (nextDir[cctvType][i] + direction) % 4;
		}
		
		return nextDir[cctvType];
		
	}
	
	public static boolean isWall(int spot) {
		return spot == 6;
	}
	
	public static boolean isCCTV(int cctv) {
		return cctv >= 1 && cctv < 6;
	}
}
