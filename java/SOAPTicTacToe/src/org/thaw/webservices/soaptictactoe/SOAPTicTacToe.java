package org.thaw.webservices.soaptictactoe;

public class SOAPTicTacToe {
	// This class implements the SOAP Web service interface.
	
    public int findBestMove(int boardDimension, String boardAsString, String playerAsString, int playerPly) throws Exception
    {
    	GameEngine gameEngine = new GameEngine(boardDimension, boardAsString, playerAsString, playerPly);
    	
    	return gameEngine.doWork();
    }
}
