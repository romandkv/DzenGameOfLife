import java.util.concurrent.TimeUnit;

public class Game {
    public static void main(String[] args){
        Desk        desk;

        if (args == null){
            System.out.println("Program arguments required.");
            return;
        }
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
            desk = new Desk(Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]));
            desk.initDesk();
        }
        else {
            InputParser inputParser;
            int[][]     inputDesk;

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
