import java.util.Stack;

/**
 * An interface for a Word Search Puzzle. A Word Search Puzzle is an n-by-n grid 
 * of lower-case English letters. Each cell in the grid can be represented by 
 * its row number and column number. Row numbers and column numbers are in the 
 * range 0 to n-1 inclusive. 
 *  
 * A cell is adjacent to another if it is next to it along 
 * any of the eight directions: N, NE, E, SE, S, SW, W, and NW. The directions 
 * wrap around the grid. You can think of the first and last columns and the 
 * first and last rows as if they are next to each other. For example, the cell 
 * next to (0, 0) along the North direction is (n-1, 0).
 */

public interface WordSearchInterface {

    //The eight directions of cell adjacency
    public enum Direction {N, NE, E, SE, S, SW, W, NW};

    /**
     * loads an n-by-n Word Search Puzzle from a 2-d array of characters
     * @param puzzle the char[][] array containing the puzzle characters
     * @param n the int puzzle size
     */
    public void initialize(char[][] puzzle, int n);

    /**
     * finds a given word in the Puzzle. The solution is a Stack of unique cells 
     * containing-from bottom to top-the cells holding the letters of the word 
     * such that each two consecutive cells in the Stack are adjacent in the grid.
     * 
     * @param word the String word to find
     * @return a stack of Cells with last letter's position at the top 
     *          or null if word is not found in the grid.
     */
    public Stack<Cell> findWord(String word);

    /**
     * retrieves a deep copy of the underlying grid of lower-case letters. 
     * @return a deep copy of the 2-d char array representing the underlying 
     *          grid
     */
    public char[][] getPuzzle();  
    
}
