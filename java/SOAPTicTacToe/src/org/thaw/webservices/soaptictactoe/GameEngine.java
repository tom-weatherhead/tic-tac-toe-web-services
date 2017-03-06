package org.thaw.webservices.soaptictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum SquareContentType {
	EMPTY_SQUARE, // = -1, // Java constant names are upper case.
	X, // = 0,
	O // = 1
}

public class GameEngine {
	private static final int MINIMUM_BOARD_DIMENSION = 3;     // I.e. the board has at least 3 rows and 3 columns.
	private static final int MAXIMUM_BOARD_DIMENSION = 4;     // I.e. the board has at most 4 rows and 4 columns.
	private int boardDimension;
	private int boardArea;      // board.Length may make this unnecessary.
	private SquareContentType[] board;
	private SquareContentType currentPlayer;
    private int ply;
    private static final int MINIMUM_PLY = 1;
    private static final int MAXIMUM_PLY = 16;
    private int boardPopulation;
    private static final int VICTORY_VALUE = 100;
    private static final int DEFEAT_VALUE = -VICTORY_VALUE;

    public GameEngine(int boardDimension, String boardAsString, String playerAsString, int playerPly) throws Exception
    {
    	
    	if (boardDimension < MINIMUM_BOARD_DIMENSION)
    	{
    		throw new Exception("The board dimension is too small");
    	}
    	else if (boardDimension > MAXIMUM_BOARD_DIMENSION)
    	{
    		throw new Exception("The board dimension is too large");
    	}
    	
    	this.boardDimension = boardDimension;
    	this.boardArea = boardDimension * boardDimension;
    	
    	if (boardAsString.length() != this.boardArea)
    	{
    		throw new Exception("boardAsString has the wrong length");
    	}
    	
    	this.board = new SquareContentType[this.boardArea];
    	this.boardPopulation = 0;
    	
    	for (int i = 0; i < this.boardArea; ++i)
    	{
    		char c = boardAsString.charAt(i);
    		
    		if (c == 'X')
    		{
    			this.board[i] = SquareContentType.X;
    			++this.boardPopulation;
    		}
    		else if (c == 'O')
    		{
    			this.board[i] = SquareContentType.O;
    			++this.boardPopulation;
    		}
    		else
    		{
    			this.board[i] = SquareContentType.EMPTY_SQUARE;
    		}
    	}
    	
		if (this.boardPopulation == this.boardArea)
		{
			throw new Exception("The board is already full");
		}
		
    	if (playerAsString.equals("X"))
    	{
    		this.currentPlayer = SquareContentType.X;
    	}
    	else if (playerAsString.equals("O"))
    	{
    		this.currentPlayer = SquareContentType.O;
    	}
    	else
    	{
    		throw new Exception("Unrecognized playerAsString");
    	}

    	if (playerPly < MINIMUM_PLY)
    	{
    		throw new Exception("The ply is too small");
    	}
    	else if (playerPly > MAXIMUM_PLY)
    	{
    		throw new Exception("The ply is too large");
    	}
    	
    	this.ply = playerPly;
    }
    
    private boolean isVictory(SquareContentType player, int row, int column)
    {
        // 1) Check the specified row.
        boolean victory = true;

        for (int column2 = 0; column2 < this.boardDimension; ++column2)
        {

            if (this.board[row * this.boardDimension + column2] != player)
            {
                victory = false;
                break;
            }
        }

        if (victory)
        {
            return true;
        }

        // 2) Check the specified column.
        victory = true;

        for (int row2 = 0; row2 < this.boardDimension; ++row2)
        {

            if (this.board[row2 * this.boardDimension + column] != player)
            {
                victory = false;
                break;
            }
        }

        if (victory)
        {
            return true;
        }

        if (row == column)
        {
            // 3) Check the primary diagonal.
            victory = true;

            for (int i = 0; i < this.boardDimension; ++i)
            {

                if (this.board[i * this.boardDimension + i] != player)
                {
                    victory = false;
                    break;
                }
            }

            if (victory)
            {
                return true;
            }
        }

        if (row + column == this.boardDimension - 1)
        {
            // 4) Check the secondary diagonal.
            victory = true;

            for (int i = 0; i < this.boardDimension; ++i)
            {

                if (this.board[i * this.boardDimension + this.boardDimension - 1 - i] != player)
                {
                    victory = false;
                    break;
                }
            }

            if (victory)
            {
                return true;
            }
        }

        return false;
    }

    private boolean placePiece(SquareContentType player, int row, int column) throws Exception
    {
        // If player is X or O, the square being written to must be empty just before the move is made.
        // If player is Empty, the square being written to must be non-empty just before the move is made, and displayMove must be false.

        if (row < 0 || row >= this.boardDimension)
        {
            throw new Exception("placePiece() : row is out of range");
        }

        if (column < 0 || column >= this.boardDimension)
        {
            throw new Exception("placePiece() : column is out of range");
        }

        SquareContentType oldSquareContent = this.board[row * this.boardDimension + column];

        if (player != SquareContentType.EMPTY_SQUARE)
        {

            if (oldSquareContent != SquareContentType.EMPTY_SQUARE)
            {
                throw new Exception("placePiece() : Attempted to write an X or an O into a non-empty square");
            }
        }
        else
        {

            if (oldSquareContent == SquareContentType.EMPTY_SQUARE)
            {
                throw new Exception("placePiece() : Attempted to erase an already-empty square");
            }
        }

        this.board[row * this.boardDimension + column] = player;

        if (player == SquareContentType.EMPTY_SQUARE)
        {
            --this.boardPopulation;
        }
        else
        {
            ++this.boardPopulation;
        }

        boolean victory = player != SquareContentType.EMPTY_SQUARE && isVictory(player, row, column);

        return victory; // This can return true for real or speculative moves.
    }

    private int getNumOpenLines(SquareContentType opponent)
    {
        int numOpenLines = 2 * this.boardDimension + 2;
        int row;
        int column;

        // 1) Check all rows.

        for (row = 0; row < this.boardDimension; ++row)
        {

            for (column = 0; column < this.boardDimension; ++column)
            {

                if (this.board[row * this.boardDimension + column] == opponent)
                {
                    --numOpenLines;
                    break;
                }
            }
        }

        // 2) Check all columns.

        for (column = 0; column < this.boardDimension; ++column)
        {

            for (row = 0; row < this.boardDimension; ++row)
            {

                if (this.board[row * this.boardDimension + column] == opponent)
                {
                    --numOpenLines;
                    break;
                }
            }
        }

        // 3) Check the primary diagonal.

        for (row = 0; row < this.boardDimension; ++row)
        {

            if (this.board[row * this.boardDimension + row] == opponent)
            {
                --numOpenLines;
                break;
            }
        }

        // 4) Check the secondary diagonal.

        for (row = 0; row < this.boardDimension; ++row)
        {

            if (this.board[row * this.boardDimension + this.boardDimension - 1 - row] == opponent)
            {
                --numOpenLines;
                break;
            }
        }

        return numOpenLines;
    }

    private int getBoardValue(SquareContentType player, SquareContentType opponent)
    {
    	// 2014/06/30 : This is backwards:
        //return getNumOpenLines(player) - getNumOpenLines(opponent);
    	// This is correct:
        return getNumOpenLines(opponent) - getNumOpenLines(player);
    }

    private int findBestMove(SquareContentType player, int ply,
        int bestUncleRecursiveScore,	// For alpha-beta pruning.
        boolean returnBestCoordinates,
        int[] bestMove) throws Exception // An array of length 1; simulates an int passed by reference.
    {
    	SquareContentType opponent = (player == SquareContentType.X) ? SquareContentType.O : SquareContentType.X;
        int bestMoveValue = DEFEAT_VALUE - 1;     // Worse than the worst possible move value.
        List<Integer> bestMoveList = new ArrayList<Integer>();
        boolean doneSearching = false;

        for (int row = 0; row < this.boardDimension && !doneSearching; ++row)
        {

            for (int column = 0; column < this.boardDimension; ++column)
            {
                int moveValue = 0;
                int currentSquareIndex = row * this.boardDimension + column;

                if (this.board[currentSquareIndex] != SquareContentType.EMPTY_SQUARE)
                {
                    continue;
                }

                if (placePiece(player, row, column)) // I.e. if this move results in immediate victory.
                {
                    moveValue = VICTORY_VALUE;
                }
                else if (this.boardPopulation < this.boardArea && ply > 1)
                {
                    int[] dummyBestMove = new int[1];

                    moveValue = -findBestMove(opponent, ply - 1, bestMoveValue, false, dummyBestMove);
                }
                else
                {
                    moveValue = getBoardValue(player, opponent);
                }

                placePiece(SquareContentType.EMPTY_SQUARE, row, column);

                if (moveValue == bestMoveValue && returnBestCoordinates)
                {
                    bestMoveList.add(currentSquareIndex);
                }
                else if (moveValue > bestMoveValue)
                {
                    bestMoveValue = moveValue;

                    if (bestMoveValue > -bestUncleRecursiveScore) 
                    {
                        // Alpha-beta pruning.  Because of the initial parameters for the top-level move, this break is never executed for the top-level move.
                        doneSearching = true;
                        break; // ie. return.
                    }
                    else if (returnBestCoordinates)
                    {
                        bestMoveList.clear();
                        bestMoveList.add(currentSquareIndex);
                    }
                    else if (bestMoveValue == VICTORY_VALUE)
                    {
                        // Prune the search tree, since we are not constructing a list of all of the best moves.
                        doneSearching = true;
                        break;
                    }
                }
            }
        }

        if (bestMoveValue < DEFEAT_VALUE || bestMoveValue > VICTORY_VALUE)
        {
            throw new Exception("findBestMove() : bestMoveValue is out of range.");
        }
        else if (!returnBestCoordinates)
        {
            bestMove[0] = -1;
        }
        else if (bestMoveList.size() == 0)
        {
            throw new Exception("findBestMove() : The bestMoveList is empty at the end of the method.");
        }
        else
        {
        	Random randomNumberGenerator = new Random();
            int index = randomNumberGenerator.nextInt(bestMoveList.size());
            
            bestMove[0] = bestMoveList.get(index);
        }

        return bestMoveValue;
    }

	public int doWork() throws Exception
	{
		int[] bestMove = new int[1];
		
		bestMove[0] = -1;
		findBestMove(this.currentPlayer, this.ply, DEFEAT_VALUE - 1, true, bestMove);
		return bestMove[0];
	}
}
