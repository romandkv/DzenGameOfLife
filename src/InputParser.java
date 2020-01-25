import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputParser {
    private String  fileName;
    private int     M;
    private int     N;

    public InputParser(String fileName){
        this.fileName = fileName;
    }

    private int[] getIntFromString(String line){
        int[] content;

        content = new int[line.length()];

        for (int i = 0; i < line.length(); i++){
            content[i] = Character.getNumericValue(line.charAt(i));
            if (content[i] != 1 && content[i] != 0){
                System.out.println("Incorrect data in file: invalid character");
                return null;
            }
        }
        return content;
    }

    private List<String> getContentFromFile() {
        List<String>    listOfLines;
        BufferedReader  buffer;
        String          contentLine;
        int             lineSize;
        int             countLines;

        listOfLines = new ArrayList<String>();
        countLines = 0;
        lineSize = 0;
        try {
            buffer = new BufferedReader(
                    new FileReader(fileName));
            if ((contentLine = buffer.readLine()) == null) {
                System.out.println("File is empty.");
                return null;
            }
            else {
                lineSize = contentLine.length();
                countLines++;
                listOfLines.add(contentLine);
            }
            while ((contentLine = buffer.readLine()) != null) {
                if (contentLine.length() != lineSize){
                    System.out.println("Incorrect data in file: length of lines are not regular.");
                    return null;
                }
                else {
                    listOfLines.add(contentLine);
                    countLines++;
                }
            }
            buffer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return null;
        } catch (IOException e) {
            System.out.println("Error while reading file.");
            return null;
        }
        M = countLines;
        N = lineSize;
        return listOfLines;
    }

    public int[][] getDeskFromFile(){
        List<String>    listOfLines;
        int[][]         desk;
        int             i;

        if ((listOfLines = getContentFromFile()) == null)
            return null;
        desk = new int[M][N];
        i = 0;
        for (String str:
             listOfLines) {
            if ((desk[i] = getIntFromString(str)) == null){
                return null;
            }
            i++;
        }
        return desk;
    }

    public int getM(){
        return M;
    }

    public int getN(){
        return N;
    }
}

