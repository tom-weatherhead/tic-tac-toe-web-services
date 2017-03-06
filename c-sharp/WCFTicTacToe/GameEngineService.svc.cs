using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using Rhino.Mocks;
using WPFTicTacToe.GameEngine;

namespace WCFTicTacToe
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    public class GameEngineService : IGameEngineService
    {
        /*
        public string GetData(int value)
        {
            return string.Format("You entered: {0}", value);
        }

        public CompositeType GetDataUsingDataContract(CompositeType composite)
        {
            if (composite == null)
            {
                throw new ArgumentNullException("composite");
            }
            if (composite.BoolValue)
            {
                composite.StringValue += "Suffix";
            }
            return composite;
        }
         */

        public int FindBestMove(int boardDimension, string boardAsString, bool playerIsX, int ply)
        {
            // See http://wrightthisblog.blogspot.ca/2011/03/using-rhinomocks-quick-guide-to.html
            var gameWindow = MockRepository.GenerateMock<IGameWindow>();
            
            var gameEngine = new GameEngine(gameWindow, boardDimension, boardAsString);

            int bestRow;
            int bestColumn;
            int bestMoveValue = gameEngine.FindBestMove(playerIsX ? SquareContentType.X : SquareContentType.O,
                ply, true, null, out bestRow, out bestColumn);

            return bestRow * boardDimension + bestColumn;
        }
    }
}
