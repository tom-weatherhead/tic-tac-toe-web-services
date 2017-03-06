/**
 * SOAPTicTacToe.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.thaw.webservices.soaptictactoe;

public interface SOAPTicTacToe extends java.rmi.Remote {
    public int findBestMove(int boardDimension, java.lang.String boardAsString, java.lang.String playerAsString, int playerPly) throws java.rmi.RemoteException;
}
