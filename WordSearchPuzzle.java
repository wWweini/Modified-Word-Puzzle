import java.util.Stack;

public class WordSearchPuzzle implements WordSearchInterface {

    private char[][] puzzle; //the 2-d grid of letters
    private int size; //the grid size
    private static Direction[] directions = {Direction.N, Direction.E, 
                                             Direction.S, Direction.W, 
                                             Direction.NE, 
                                             Direction.SE, 
                                             Direction.SW, 
                                             Direction.NW};


    @Override
    
    //TODO complete this method. You will need to call the helper solve method for each 
    //cell in the grid
    public Stack<Cell> findWord(String word) 
    {
        Stack<Cell> positions = new Stack<>();
        word = word.toLowerCase();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < puzzle.length; i++) 
        {
            for (int j = 0; j < puzzle.length; j++) 
            {
                if (puzzle[i][j] == word.charAt(0)) 
                {
                    Cell temp = new Cell(i, j);
                    positions.push(temp);
                    s.append(word.charAt(0));
                    puzzle[i][j] = Character.toUpperCase(puzzle[i][j]);
                    if (word.length() == 1) 
                    {
                       return positions;
                    } 
                    else 
                    {
                        if (solve(i, j, s, word, positions)) 
                        {
                            return positions;
                        }
            
                    }
                    puzzle[i][j] = Character.toLowerCase(puzzle[i][j]);
                    s.deleteCharAt(s.length() - 1);
                    positions.pop();
    
                }
            }
        }
    
        return null;
    }

    /**
     * A helper method for findWord. This method uses backtracking to find a word in the 2-d grid of letters puzzle.
     * @param row the row of the starting cell
     * @param col the col of the starting cell
     * @param current the subword found so far
     * @param wordToFind the word to find
     * @param positions the stack of positions corresponding to the subword found fo far
     * @return
     */    
    private boolean solve(int row, int col, StringBuilder current, String wordToFind, Stack<Cell> positions)
    {
        boolean result = false;
        int index = current.length();
        for (int d = 0; d < directions.length; d++) 
        {   // 8 directions
            int nextRow = nextRow(row, directions[d]);
            int nextCol = nextCol(col, directions[d]);
            //(if hasn't been used yet(if it's lower case) and equal to the letter){mark as used}
            if (isValid(nextRow, nextCol) && puzzle[nextRow][nextCol] == wordToFind.charAt(index) && !positions.isEmpty()) 
            {
                puzzle[nextRow][nextCol] = Character.toUpperCase(puzzle[nextRow][nextCol]);
                current.append(puzzle[nextRow][nextCol]);
                Cell word = new Cell(nextRow, nextCol);
                positions.push(word);
                if (current.length() == wordToFind.length()) 
                {
                    result = true;
                    break;
                }
                //else if(solve recursion true){return true} 
                else 
                {
                    if (solve(nextRow, nextCol, current, wordToFind, positions)) 
                    {
                        result = true;
                        break;
                    }
                }
                // unmark the neighbor
                puzzle[nextRow][nextCol] = Character.toLowerCase(puzzle[nextRow][nextCol]);
                current.deleteCharAt(current.length() - 1);
                positions.pop();
            }
        }
        return result;
    }

    /**
     * check is a cell is legal and hasn't been used before
     * @param row the row of the cell
     * @param col the col of the cell
     * @return true if cell is legal and false otherwise
     */
    private boolean isValid(int row, int col){
        boolean result = false;
        if((row >= 0) && (col >= 0) 
            && (row < puzzle.length) && (col < puzzle[0].length) 
            && Character.isLowerCase(puzzle[row][col])){
                result = true;
            }
        return result;
    }

    /**
     * return the next row down a certain direction
     * @param row the current row
     * @param direction the direction
     * @return the next row down the given direction
     */
    private int nextRow(int row, Direction direction){
        int result = row;
        if(direction == Direction.N || direction == Direction.NE || 
            direction == Direction.NW){
            result = (row - 1 + size)%size;
        }
        if(direction == Direction.S || direction == Direction.SE || 
            direction == Direction.SW){
            result = (row + 1)%size;
        }
        return result;
    }

    /**
     * return the next column down a certain direction
     * @param row the current column
     * @param direction the direction
     * @return the next column down the given direction
     */
    private int nextCol(int col, Direction direction){
        int result = col;
        if(direction == Direction.E || direction == Direction.NE || 
            direction == Direction.SE){
            result = (col + 1)%size;
        }
        if(direction == Direction.W || direction == Direction.NW || 
            direction == Direction.SW){
            result = (col - 1 + size)%size;
        }
        return result;
    }
   

    @Override
    public void initialize(char[][] puzzle, int n) 
    {
        this.puzzle = new char[n][n];
        for(int i = 0; i < puzzle.length; i++)
        {
            for(int j = 0; j < puzzle.length; j++)
            {
                
                
                this.puzzle[i][j] = puzzle[i][j];
            
            }
        }
        this.size = n;
    }

    @Override
    public char[][] getPuzzle() 
    {
        char[][] temp = new char[puzzle.length][puzzle.length];
        for(int i = 0; i < puzzle.length; i++)
        {
            for(int j = 0; j < puzzle.length; j++)
            {
                temp[i][j] = this.puzzle[i][j];
            }
        }
        return temp;
    }    
}
