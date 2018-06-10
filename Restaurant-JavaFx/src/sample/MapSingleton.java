package sample;

import javafx.scene.image.*;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Master Faster on 10.01.2017.
 * Singleton to store map
 */
public class MapSingleton {

    private final static MapSingleton instance = new MapSingleton();
    private int mapSize = 15;
    private int[][] map = new int[mapSize][mapSize];
    private int tileSize;
    private int cashInRestaurant = 0;

    public MapSingleton(){
        tileSize = 40;
        map = new int[15][15];
        int x=0;
        int y=0;
        int i;
        File file = new File("C:/Users/Master Faster/Documents/Informatyka III semestr/Programowanie Obiektowe/javaProject/Restaurant-JavaFx/src/sample/Map/map.txt");
        try {
            FileReader reader = new FileReader(file);
            while ((i = reader.read())!=-1) {
                i-=48;  //bo odczytuje znaki w ascii
                if( i >= 0 && i <=9) {
                    map[x][y] = i;
                    y++;
                    if(y==15){
                        y=0;
                        x++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MapSingleton getInstance(){
        return instance;
    }

    public int[][] getMap(){ return map; }

    public int getMapSize(){ return mapSize; }

    public  List<int[]> getPossibleClientLocations(){
        List<int[]> freeFields = new ArrayList<>();
        for(int y=0;y<mapSize;y++){
            for(int x=0;x<mapSize;x++){
                if(map[y][x] == 7){
                    int[] coord = new int[2];
                    coord[0] = y;
                    coord[1] = x;
                    freeFields.add(coord);
                }
            }
        }
        return freeFields;
    }

    public synchronized void increaseMapTile(int[] location){   map[location[0]][location[1]]++; }
    public synchronized void decreaseMapTile(int[] location){   map[location[0]][location[1]]--; }

    public void setMapTile(int[] location, int value){
        map[location[0]][location[1]] = value;
    }

    public int getTileSize(){
        return tileSize;
    }

    public synchronized void addCashInRestaurant(int cash){ cashInRestaurant+= cash; }

    public synchronized int getCashInRestaurant(){ return cashInRestaurant; }

}
