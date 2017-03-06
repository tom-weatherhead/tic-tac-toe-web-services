package org.thaw.webservices.soaptictactoe;

public class SOAPTicTacToeProxy implements org.thaw.webservices.soaptictactoe.SOAPTicTacToe {
  private String _endpoint = null;
  private org.thaw.webservices.soaptictactoe.SOAPTicTacToe sOAPTicTacToe = null;
  
  public SOAPTicTacToeProxy() {
    _initSOAPTicTacToeProxy();
  }
  
  public SOAPTicTacToeProxy(String endpoint) {
    _endpoint = endpoint;
    _initSOAPTicTacToeProxy();
  }
  
  private void _initSOAPTicTacToeProxy() {
    try {
      sOAPTicTacToe = (new org.thaw.webservices.soaptictactoe.SOAPTicTacToeServiceLocator()).getSOAPTicTacToe();
      if (sOAPTicTacToe != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sOAPTicTacToe)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sOAPTicTacToe)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sOAPTicTacToe != null)
      ((javax.xml.rpc.Stub)sOAPTicTacToe)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.thaw.webservices.soaptictactoe.SOAPTicTacToe getSOAPTicTacToe() {
    if (sOAPTicTacToe == null)
      _initSOAPTicTacToeProxy();
    return sOAPTicTacToe;
  }
  
  public int findBestMove(int boardDimension, java.lang.String boardAsString, java.lang.String playerAsString, int playerPly) throws java.rmi.RemoteException{
    if (sOAPTicTacToe == null)
      _initSOAPTicTacToeProxy();
    return sOAPTicTacToe.findBestMove(boardDimension, boardAsString, playerAsString, playerPly);
  }
  
  
}