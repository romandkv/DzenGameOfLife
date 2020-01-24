import java.io.IOException;
import java.util.Random;

class Desk {
	private int		M;
	private int		N;
	private int[][]	cellsPast;
	private int[][] cellsFuture;

	public Desk(int M, int N)
	{
		this.M = M;
		this.N = N;
	}

	public Desk(int[][] cells, int M, int N)
	{
		this.M = M;
		this.N = N;
		this.cellsPast = cells;
	}

	public void initDesk(){
		Random rnd;

		rnd = new Random();
		cellsPast = new int[M][N];
		for (int i = 0; i < M; i++){
			for (int j = 0; j < N; j++){
				cellsPast[i][j] = rnd.nextInt(2);
			}
		}
	}

	private int[] realNeibour(int i, int j){
		int[]		pair;

		pair = new int[2];
		if (i < 0)
			pair[0] = i + M;
		else if (i > M - 1)
			pair[0] = i - M;
		else
			pair[0] = i;
		if (j < 0)
			pair[1] = j + N;
		else if (j > N - 1)
			pair[1] = j - N;
		else
			pair[1] = j;
		return pair;

	}
	public boolean checkCell(int x, int y){
		int[]	realCoord;
		int		countAliveNeibour;

		countAliveNeibour = 0;
		for (int i = -1; i < 2; i++){
			for (int j = -1; j < 2; j++){
				if (i + x == x && j + y == y)
					continue;
				realCoord = realNeibour(i + x, j + y);
				if (cellsPast[realCoord[0]][realCoord[1]] == 1)
					countAliveNeibour++;
			}
		}
		if (cellsPast[x][y] == 1)
			return (countAliveNeibour == 2 ||
					countAliveNeibour == 3);
		else
			return (countAliveNeibour == 3);
	}

	public void		nextConfigDesk(){
		cellsFuture = new int[M][N];
		for (int i = 0; i < M; i++){
		 	for (int j = 0; j < N; j++){
				//cellsFuture[i][j] = checkCell(i, j) == true ? 1 : 0;
				if (checkCell(i, j) == true)
					cellsFuture[i][j] = 1;
				else
					cellsFuture[i][j] = 0;
			}
		 }
		cellsPast = cellsFuture;
	}

	public void printDesk(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
		for (int i = 0; i < M; i++){
			for (int j = 0; j < N; j++){
				System.out.print(cellsPast[i][j]);
			}
			System.out.print('\n');
		}
		System.out.println('\n');
	}

}