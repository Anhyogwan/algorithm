package com.ssafy.recur.swea.sol2105;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	// 한 변의 길이가 N인 정사각형 모양
	// 원 안 숫자는 해당 디저트 카페에서 팔고 있는 디저트의 종류
	// 대각선 방향으로 움직이기 가능 -> 사각형 돌고 다시 돌아와야함
	// 범위 벗어나기 x, 같은거 먹기 x, 같은 숫자 x
	// 대각선 우측아래,좌측아래에 데이터가 있을 경우에만 포함됨 (위부터 순차적으로 검색했을
	// 범위 밖으로 나가기 전까지 dfs돌리면 될거같은데..
	// 최대 20*20의 판 종류는 1~100; 400
	static int N;
	static int[][] map;
	static int max_value;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max_value= Integer.MIN_VALUE;;
			// 가로 1 부터 N-1 까지 탐색하면서 검사
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					dfs(i+1, j+1,new boolean[101],0,1,i,j);
				}
			}
			System.out.println("#" + tc  +  " " + (max_value == Integer.MIN_VALUE ? -1 : max_value ));
		}
	}

	static int[] dx = { 1, 1, -1, -1 };
	static int[] dy = { 1, -1 ,- 1, 1 };
	
	private static void dfs(int x, int y,boolean[] checkNum,int idx,int cnt,int startx,int starty) {
		if(idx>3 || checkNum[map[x][y]]) return;
		if (x==startx && y==starty) {
			max_value = Math.max(max_value, cnt);
			return;
		}
		checkNum[map[x][y]]=true;
		int nx = x+dx[idx];
		int ny = y+dy[idx];
		if (idx<3 && !Isin(x+dx[idx+1],y+dy[idx+1])) dfs(x+dx[idx+1],y+dy[idx+1],checkNum.clone(),idx+1,cnt+1,startx,starty);
		if (!Isin(nx,ny)) dfs(nx,ny,checkNum.clone(),idx,cnt+1,startx,starty);
	}

	private static boolean Isin(int x, int y) {
		if (x >=N || x<0 || y>=N || y<0) return true;
		return false;
	}
}
