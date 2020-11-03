package zbo.maze;

import java.io.PrintWriter;
import java.util.Map;

public class MazeToSolve
{

    Map<Coordinate, Character> chars;

    int rows;

    int cols;

    MazeLocation head;

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public int getCols()
    {
        return cols;
    }

    public void setCols(int cols)
    {
        this.cols = cols;
    }

    public void setChar(int row, int col, char o)
    {
        chars.put(new Coordinate(row, col), o);
    }

    public char getChar(int row, int col)
    {
        return chars.getOrDefault(new Coordinate(row, col), ' ');
    }


    public boolean isEmpty() {
        if(head == null){
            return true;
        }
        return false;
    }


    static class Coordinate {

        int x;
        int y;

        public Coordinate(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Coordinate that = (Coordinate)o;
            if (x != that.x)
                return false;
            return y == that.y;
        }

        @Override
        public int hashCode()
        {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

}
