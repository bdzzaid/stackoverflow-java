package zbo;

import org.junit.Test;
import zbo.maze.MazeLocation;
import zbo.maze.MazeToSolve;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Resolve
 * https://stackoverflow.com/questions/64531989/why-isnt-my-recursive-method-base-case-not-returning-a-boolean-as-it-should-be?noredirect=1#comment114106117_64531989
 */
public class MazeLocationTest
{

    private char displayChar;
    PrintWriter fileWriter = new PrintWriter(System.out);
    Stack<MazeLocation> path;
    MazeToSolve mazeToSolve;

    @Test
    public void runTest() {

    }

    private boolean findPath(MazeLocation cur, MazeLocation finish) {
        int row = cur.row;
        int col = cur.col;
        mazeToSolve.setChar(row, col, 'o');
        fileWriter.println("\n"+mazeToSolve.toString());
        char strX = 'X';
        char strH = 'H';

        // First ,we need to scan the 4 directions around current location to see where to go
        MazeLocation up = new MazeLocation(row-1, col);
        MazeLocation down = new MazeLocation(row+1, col);
        MazeLocation right = new MazeLocation(row, col+1);
        MazeLocation left = new MazeLocation(row, col-1);

        // BASE CASE - WHEN WE'VE REACHED FINISH COORDINATES
        if(cur.row == finish.row && cur.col == finish.col){
            return true;
        }
        // SECOND BASE CASE - IF MAZE ISNT SOLVABLE
        if (path.isEmpty() == true){ // if the path is empty, then there is no solution.
            return false;
        }

        // Check if we can go up
        if(up.getRow() >= 0){
            if(mazeToSolve.getChar(up.getRow(), up.getCol()) == ' '){
                row = up.getRow();
                col = up.getCol();
                MazeLocation newCur = new MazeLocation(row, col);
                path.push(newCur);
                return findPath(newCur, finish);
            }
        }

        // Check if we can go down
        if(down.getRow() < mazeToSolve.getRows()){
            if(mazeToSolve.getChar(down.getRow(), down.getCol()) == ' '){
                row = down.getRow();
                col = down.getCol();
                MazeLocation newCur = new MazeLocation(row, col);
                path.push(newCur);
                return findPath(newCur, finish);
            }
        }

        // Check if we can go right
        if(right.getCol() < mazeToSolve.getCols()){
            if(mazeToSolve.getChar(right.getRow(), right.getCol()) == ' '){
                row = right.getRow();
                col = right.getCol();
                MazeLocation newCur = new MazeLocation(row, col);
                path.push(newCur);
                return findPath(newCur, finish);
            }
        }

        // Check if we can go left
        if(left.getCol() >= 0){
            if(mazeToSolve.getChar(left.getRow(), left.getCol()) == ' '){
                row = left.getRow();
                col = left.getCol();
                MazeLocation newCur = new MazeLocation(row, col);
                path.push(newCur);
                return findPath(newCur, finish);
            }
        }

        // If we cant do any of the above then we need to backtrack by popping the top of stack
        // and leaving X's until we can move up, down, left, or right again.
        MazeLocation newCur = new MazeLocation(row, col);
        MazeLocation top = path.pop(); // popping the cur where we are putting the x
        mazeToSolve.setChar(row, col, 'x'); // putting the x
        return findPath(top, finish); // now we need to return to the position where the stack is NOW after the pop
    }



}
