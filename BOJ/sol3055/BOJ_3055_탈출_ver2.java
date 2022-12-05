package com.ssafy.recur.BOJ.sol3055;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/*
 *  1. 입력값 구분 : 비어있는 곳(.),물(*),돌(X) , 비버(O), 고슴도치(S)
 *  2. 매분마다 물과 고슴도치는 사방으로 이동한다.
 *  3. 물이 이동된 영역은 물로 변경해야 한다.( 물은 비버의 영역으로 이동 불가 )
 *  4. 물과 고슴도치는 돌 영역은 이동 불가
 *  5. 고슴도치는 물이 차있는 영역 이동 불가
 *  
 *  물과 고슴도치 같이 이동 불가능 : 돌
 *  고슴도치만 이동 가능 : 물
 *  물만 이동 불가능 : 비버집
 *  
 *  최단시간 -> BFS
 */
public class BOJ_3055_탈출_ver2 {
	static int R,C;
	static char[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map= new char[R][C];
		for (int i = 0; i <R ; i++) {
			map[i] = sc.next().toCharArray();
		}
		// 물과 고슴도치를 구분, 좌표
		Queue<int[]> q = new ArrayDeque<>();
		int[] S = null;
		for (int i = 0 ; i <R ; i++) {
			for (int j = 0 ; j<C ; j++) {
				if(map[i][j]== '*') q.offer(new int[] {i,j,1});
				else if(map[i][j]== 'S') S =new int[] {i,j,2};
			}
		}
		q.offer(S);
		
		int[] dr = {-1,0,1,0};
		int[] dc = {0,1,0,-1};
		int time=0;
		while(!q.isEmpty()) {
			++time;
			int size =q.size();
			for (int i = 0; i < size ; i++) {
				int[] temp =q.poll();
				for (int d=  0 ; d < 4 ; d++) {
					int nr = temp[0] + dr[d];
					int nc = temp[1] + dc[d];
					if (nr < 0 || nr>=R || nc < 0 || nc >=C || map[nr][nc] =='X' ||  map[nr][nc] =='*' ) continue;
					if (temp[2]==1) { // 물
						if (map[nr][nc]== '.' || map[nr][nc]== 'S') {
							q.offer(new int[] { nr,nc,1});
							map[nr][nc] = '*';
						}
					}else { // 고슴도치 
						if (map[nr][nc]=='S') continue;
						if (map[nr][nc]=='D') {
							System.out.println(time);
							return;
						}
						if (map[nr][nc]=='.') {
							q.offer(new int[] {nr,nc,2});
							map[nr][nc]='S';
						}
					}
				}
			}
		}
		System.out.println("KAKTUS");
		
	}
}
