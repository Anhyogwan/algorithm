package com.ssafy.recur.swea.sol5653;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Cell{
	int x, y;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

public class Solution {
	// 하나의 줄기세포는 가로세로크기가 1인 정사각형
	// 배양 용기는 격자 그리드 하나으 ㅣ그리드 셀은 줄기 세포의 크기오 ㅏ동일한 가로 세로 크기가 1인 정사각형
	// 초기는 비활성화 , 생명력수치가 x인 줄기세포는 x시간동안 비활성 상태, x시간이 지나면 활성상태
	// 활성되면 x시간동안 살아있고 x시간 지나면 세포는 죽음
	// 죽으면 안사라지고 그리드셀을 죽은 상태로 차지함
	// 활성된 줄기세포는 첫 1시간에 4방번식 -> 그러니까 활성화되면 4방으로 수치 바꿔라 이말인듯?
	// 번식된 줄기세포는 비활성 상태
	// 셀 하나에는 하나의 줄기세포만 가능 - > 두개 이상의 줄기세포가 동시에 번식하려는 경우 생명력 수치가 높은애가 차지
	// k 시간후 살아남은 줄기세포 출력 -> 죽은애 빼고 비활성인 애들도 체크해야한다
	// 이미 살아난 애를 체크하는 배열 필요
	static int N, M, K;
	static ArrayList<Cell> cell;
	static ArrayList<Cell> activated;
	static int[][] map;
	static boolean[][] visited;
	static int[][] map2;
	static int[][] map3;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			cell = new ArrayList<>();
			activated = new ArrayList<>();
			queue = new PriorityQueue<>((o1,o2) -> map2[o1.x][o1.y] - map2[o2.x][o2.y]);
			map = new int[400][400];
			map2 = new int[400][400];
			map3 = new int [400][400];
			visited = new boolean[400][400];
			next = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num != 0) {
						cell.add(new Cell(200 - N + i, 200 - M + j) );
						visited[200 - N + i][200 - M + j] = true;
					}
					map[200 - N + i][200- M + j] = num;
					map2[200 - N + i][200 - M + j] = num;
					map3[200 - N + i][200 - M + j] = num;
				}
			}
			
			for (int i = 0; i < K; i++) {
				breeding();
//				System.out.println(i+1);
//				for (int j = 0; j <map.length ; j++) {
//					System.out.println(Arrays.toString(map2[j]));
//				}
			}
			System.out.println("#" + tc + " " + (activated.size() +cell.size()));
		}
	}

	// 아무리봐도 2중리스트로 힘들거 같음
	// 그리디적 해결법을 찾아야 할듯
	// 좌표와 값을 클래스변수 생성해서 저장한 다음
	// Contact 문제랑 비슷한가?
	// 시간마다 1씩 빼주면서 0이되면 큐에 집어넣어서 bfs?
	// k 시간이 지난 후에 리스트 검사해서 살아있는애 체크
	// 겹쳤을 떄가 문젠데..
	// 배열을 두개를 관리하면서
	// 2중 리스트를 탐색해서 현재 숫자와 맞을경우 사방으로 늘려주기
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static PriorityQueue<Cell> queue;
	static ArrayList<Cell> next;
	
	static void breeding() {
		ArrayList<int[]> checked = new ArrayList<>();
		// 0이 되면 사방탐색으로 퍼트리고 해당 세포는 활성상태가 됨
		for (int i = 0 ; i < next.size(); i++) {
			queue.offer(next.get(i));
		}
		next=new ArrayList<>();
		
		for (int i=0 ; i<activated.size() ; i ++) {
			if(--map3[activated.get(i).x][activated.get(i).y] <=0) {
				activated.remove(i);
				i--;
			}
		}

		
		for (int i=0; i <cell.size(); i ++) { // 세포들이 만약 카운트가 0이 되면  활성화 된거니 큐에 추가해서 번식
			if(--map[cell.get(i).x][cell.get(i).y] <=0){
				next.add(cell.get(i));
				activated.add(new Cell(cell.get(i).x,cell.get(i).y));
				cell.remove(i);
				i--;
			}
		}
		//boolean[][] visited_2 = new boolean[400][400];
		int size = queue.size();
		for (int i = 0; i < size; i++) { // 활성화가 되어 큐의 사이즈가 0이 아니게 됬으면
			Cell act = queue.poll(); // 큐에 있는것들을 하나씩 뺴서
			for (int j = 0; j < 4; j++) { // 사방탐색을 돌리고
				int nx = act.x + dx[j];
				int ny = act.y + dy[j];
				if (visited[nx][ny]) // 만약 이전 시간부터 있엇던 애라면 거기는 건드리지 말고
					continue;
				//System.out.println(nx + " " + ny + " " + map[nx][ny] + " " + map2[act.x][act.y]);
				checked.add(new int[] { nx, ny }); // 그게 아니라면 nx ny에 세포 배양 하고 체크할 리스트에 추가
				if (map[nx][ny]!= 0) { // 얘가 방문 안한곳인데 0이상이다? 그러면 이미 같은 depth에서 누군가가 다녀갔다는 소리니까 값 비교 후 갱신
					map[nx][ny] =map2[act.x][act.y]; // 만약 현재 depth에서 겹치면 더 큰거
				}else {
//					if (visited_2[nx][ny]) continue;
//					visited_2[nx][ny]=true;
					map[nx][ny] =map2[act.x][act.y]; // 그게 아닐 경우 일단 값을 갱신 해주고
					cell.add(new Cell(nx, ny)); // 현재 세포 목록에 추가 
				}
			}
		}
		
		for (int i = 0; i < checked.size(); i++) {
			map3[checked.get(i)[0]][checked.get(i)[1]]= map[checked.get(i)[0]][checked.get(i)[1]];
			map2[checked.get(i)[0]][checked.get(i)[1]]= map[checked.get(i)[0]][checked.get(i)[1]];
			visited[checked.get(i)[0]][checked.get(i)[1]] = true;
		}

	}
}
