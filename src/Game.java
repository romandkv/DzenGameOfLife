import java.util.concurrent.TimeUnit;

public class Game {
    public static void main(String[] args){
        Desk        desk;

	/* There are two cases:
	 * 1) Run with random values on desk and two dimensions of desk. (3 args)
	 * 2) Run existing desk from file. (1 arg)
	*/

        if (args.length != 3 && args.length != 1){
            System.out.println("Incorrect number of arguments.");
            return;
        }
        if (args.length == 3){
            if (!args[0].equals("-r")){
                System.out.println("Incorrect input.");
                return;
            }
            try {
		/* If option run is executed, 
		 * there are two dimension, which should be
		 * nonnegative, integers. 
		*/
                if (Integer.parseInt(args[1]) < 1 ||
                        Integer.parseInt(args[2]) < 1) {
                    System.out.println("Negative size of desk.");
                    return;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Dimensions must be only integer, nonnegative numbers.");
                return;
            }
        }
        if (args[0].equals("-r"))
        {
		/* For desk to be generated only reqires 
		 * two dimensions.
		*/
            desk = new Desk(Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]));
            desk.initDesk();
        }
        else {
            InputParser inputParser; //Object, which gets filename and parses it to int[][] array
            int[][]     inputDesk; //Array, which represents first configuration of desk

            inputParser = new InputParser(args[0]);
            if ((inputDesk = inputParser.getDeskFromFile()) == null){
                return;
            }
            desk = new Desk(inputDesk,
                            inputParser.getM(),
                            inputParser.getN());
        }
        desk.printDesk();
        while (true){
            desk.nextConfigDesk();
            desk.printDesk();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
