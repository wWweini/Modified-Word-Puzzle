import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class A4Test {
    private static Scanner scan;
    private WordSearchInterface ws;

    public static void main(String[] args){

        A4Test a4 = new A4Test();
        scan = new Scanner(System.in);
        while(true){
          switch(a4.menu()){
            case 1:
              a4.readFromFile();
              break;
            case 2:
              a4.printPuzzle();
              break;
            case 3:
              a4.findWordAutomatically();
              break;
            case 4:
              scan.close();
              System.exit(0);
              break;
            default:
              System.out.println("Incorrect option");
          }
          System.out.println("Press ENTER to continue ...");
          scan.nextLine();
        }
      }

    private void printPuzzle() {
      if(ws == null){
        System.out.println("Please create a puzzle first.");
      } else {
        printPuzzle(ws.getPuzzle());
      }
    }

    public A4Test(){
    }
    
    private void findWordAutomatically() {
        if(ws == null){
            System.out.println("Please create a puzzle first.");
        } else {
            char[][] puzzle = ws.getPuzzle();
            printPuzzle(puzzle);
            System.out.println("Please enter a word to search for:");
            String word = scan.nextLine();
            Stack<Cell> solution = ws.findWord(word);
            if(solution == null){
              System.out.println("Word was not found in the puzzle");
            } else {
              for(Cell c: solution){
                puzzle[c.row()][c.col()] = 
                    Character.toUpperCase(puzzle[c.row()][c.col()]);
              }
              printPuzzle(puzzle);
            }
        }
    }

    private void printPuzzle(char[][] puzzle) {
        for(int i=0; i<puzzle.length; i++){
            for(int j=0; j<puzzle.length; j++){
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void readFromFile() {
        System.out.println("Please enter filename:");
        String fileName = scan.nextLine();
        try (Scanner scan = new Scanner(new File(fileName))) {
            int size = scan.nextInt();
            scan.nextLine();
            char[][] puzzle = new char[size][size];
            for(int i=0; i<size; i++){
                String line = scan.nextLine();
                for(int j=0; j<size; j++){
                    puzzle[i][j] = line.charAt(j);
                }
            }
            ws = new WordSearchPuzzle();
            ws.initialize(puzzle, size);
            System.out.println("Puzzle file loaded successfully!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }      
    }

    private int menu(){
        int choice = -1;
        System.out.println("*********************************");
        System.out.println("Welcome to Four Four Five Word Puzzle Program!");
        System.out.println("1. Read a puzzle from a file");
        System.out.println("2. Display the puzzle");
        System.out.println("3. Find a word in the puzzle");
        System.out.println("4. Exit.");
        System.out.println("*********************************");
   
        while(true){
            System.out.print("Please choose a menu option (1-4): ");
            try{
              choice = Integer.parseInt(scan.nextLine());
              break;
            } catch(Exception e){
              System.out.println("Invalid input: " + e.getMessage());
            }
          }
          return choice;      
        }    
}
