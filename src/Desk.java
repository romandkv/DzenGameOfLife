import java.io.IOException;
import java.util.Random;

class Desk {
	private int		M;
	private int		N;
	private int[][]		cellsPast;
	private int[][]		cellsFuture;

	/* Constructor which will be called if 
	 * option -r is active
	*/
	public Desk(int M, int N)
	{
		this.M = M;
		this.N = N;
	}
	/* If desk was supposed to 
	 * be initialized from file
	 * this constructor is reqired.
	*/
	public Desk(int[][] cells, int M, int N)
	{
		this.M = M;
		this.N = N;
		this.cellsPast = cells;
	}
	/* Randomizing values of desk */
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
	/* realNeibour function returns pair {x,y}.
	 * If cell has neibour with indexes which are 
	 * out of bounds of array, it means we should
	 * search for this cell on the opposite side 
	 * of desk, which has the shape of a torus.
	*/
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
	/* checkCell counts alive neibours 
	 * and decide which cell will die or reborn
	*/
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
	/* nextConfigDesk goes through the desk
	 * and depending on checkCell returning value
	 * assign value to each cell
	*/
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
	/* Print current configuration of desk */
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
