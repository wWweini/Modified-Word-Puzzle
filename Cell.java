/**
 * A class representing cells in the word search puzzle grid
 */
public class Cell {
    private int row;
    private int col;

    public Cell(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    public int row(){
        return row;
    }

    public int col(){
        return col;
    }
}
