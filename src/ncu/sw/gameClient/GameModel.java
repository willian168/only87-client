package ncu.sw.gameClient;

import ncu.sw.CmdGameUtility.*;
import ncu.sw.RenderGameUtility.*;

import java.util.ArrayList;

public class GameModel {
    private static GameModel instance= null;
    private ArrayList<GameObjectR> dynamicObjectList;
    private PlayerR mplayer = null;
    private String identity;
    private GameModel(){
        dynamicObjectList = new ArrayList<>();
    }
    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }

        return instance;
    }
    public ArrayList<GameObjectR> getDynamicObjectList(){
        return dynamicObjectList;
    }
    public void update(Cmd cmd){
        mplayer = null;
        dynamicObjectList.clear ();
        for( Player player:cmd.getPlayerArrayList ()){
            PlayerR playerR = new PlayerR (  );
            playerR.copyFromCmd ( player );
            dynamicObjectList.add ( playerR );
            if(identity == playerR.getIdentity()){
                mplayer = playerR;
            }
        }
        for( Coin coin:cmd.getCoinArrayList ()){
            CoinR coinR = new CoinR (  );
            coinR.copyFromCmd ( coin );
            dynamicObjectList.add ( coinR );
        }
        for( Item item:cmd.getItemArrayList ()){
            ItemR itemR = new ItemR (  );
            itemR.copyFromCmd ( item );
            dynamicObjectList.add ( itemR );
        }
        for( Obstacle obstacle:cmd.getObstacleArrayList ()){
            ObstacleR obstacleR = new ObstacleR (  );
            obstacleR.copyFromCmd ( obstacle );
            dynamicObjectList.add ( obstacleR );
        }
    }
    public void setMyID(String id){
        identity = id;
    }
    public Point getPlayerXY(){
        if(mplayer!= null)
            return mplayer.getPosition();
        return null;
    }
}