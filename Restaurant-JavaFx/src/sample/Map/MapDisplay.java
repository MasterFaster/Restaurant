package sample.Map;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.ClientsDataBaseSingleton;
import sample.DeliverersDataBaseSingleton;
import sample.MapSingleton;
import sample.People.Client;
import sample.People.Human;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Master Faster on 08.01.2017.
 * class, which draws map and refresh it
 */
public class MapDisplay {

    //private Stage stage;
    private Canvas canvas;
    private int[][] map;
    private Image ground;
    private Image clientHouse;
    private Image grass;
    private Image tree;
    private Image restaurant;
    private Image car;
    private Image cars;
    private Image money;
    private MapSingleton mapSingleton;
    private ClientsDataBaseSingleton clientsDataBaseSingleton;
    //private List<Human> clientDataBase;
    private DeliverersDataBaseSingleton deliverersDataBaseSingleton;

    /**
     * we load here all images to draw map
     * when user clicks on client or deliverer, window appears with some details
     * contains timer to refresh the map
     * @param mapCanvas canvas for map
     * @param clientImageView image to draw client on the map
     * @param delivererImageView image to draw deliverer on the map
     * @param moneyImageView image to draw some money, next to this image we count income
     * @param clientCountText displays amount of clients
     * @param delivererCountText displays amount of deliverers
     * @param moneyCountText displays incount
     */
    public MapDisplay(Canvas mapCanvas, ImageView clientImageView, ImageView delivererImageView, ImageView moneyImageView, Text clientCountText, Text delivererCountText, Text moneyCountText){
        //canvas = new Canvas(600,600);
        this.canvas = mapCanvas;
        clientsDataBaseSingleton = ClientsDataBaseSingleton.getInstance();
        mapSingleton = MapSingleton.getInstance();
        map = mapSingleton.getMap();
        deliverersDataBaseSingleton = DeliverersDataBaseSingleton.getInstance();
        //stage = new Stage();
        //stage.setTitle("City Map");
        Image icon = new Image("map.png");
        //stage.getIcons().add(icon);
        //Group root = new Group();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ground = new Image("/sample/Map/ground.png");
        clientHouse = new Image("/sample/Map/clientHouse.jpg");
        grass = new Image("/sample/Map/grass.jpg");
        tree = new Image("/sample/Map/tree.jpg");
        restaurant = new Image("/sample/Map/restaurant.jpg");
        car = new Image("/sample/Map/car.png");
        cars = new Image("/sample/Map/cars.png");
        money = new Image("/sample/Map/money.png");
        clientImageView.setImage(clientHouse);
        clientCountText.setText(String.valueOf(clientsDataBaseSingleton.getClientDataBase().size()));
        delivererImageView.setImage(car);
        delivererCountText.setText(String.valueOf(deliverersDataBaseSingleton.getDelivererDataBase().size()));
        moneyImageView.setImage(money);
        moneyCountText.setText(String.valueOf(mapSingleton.getCashInRestaurant()));
        drawMap(gc);

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                drawMap(gc);
                int[] location = new int[2];
                getTileFromMouse(event.getX(), event.getY(), location);
                for(int i=0;i<clientsDataBaseSingleton.getClientDataBase().size();i++){
                    if(Arrays.equals(location,clientsDataBaseSingleton.getClientDataBase().get(i).getLocation())){
                        clientsDataBaseSingleton.getClientDataBase().get(i).introduction();
                        try {
                            if(showClientInfo(clientsDataBaseSingleton.getClientDataBase().get(i),i)){
                                drawMap(gc);
                            }
                        }catch(Exception ex){
                            System.out.println(ex);
                        }
                        break;
                    }
                }
                for(int i=0;i<deliverersDataBaseSingleton.getDelivererDataBase().size();i++){
                    if(Arrays.equals(location,deliverersDataBaseSingleton.getDelivererDataBase().get(i).getLocation())){
                        deliverersDataBaseSingleton.getDelivererDataBase().get(i).introduction();
                        try {
                            if(showClientInfo(deliverersDataBaseSingleton.getDelivererDataBase().get(i),i)){
                                drawMap(gc);
                            }
                        }catch(Exception ex){
                            System.out.println(ex);
                        }
                        break;
                    }
                }

            }
        });

        new AnimationTimer(){
            @Override
            public void handle(long now) {
                clientCountText.setText(String.valueOf(clientsDataBaseSingleton.getClientDataBase() .size()));
                delivererCountText.setText(String.valueOf(deliverersDataBaseSingleton.getDelivererDataBase().size()));
                moneyCountText.setText(String.valueOf(mapSingleton.getCashInRestaurant()));
                drawMap(gc);
            }
        }.start();

        //root.getChildren().add(canvas);
        //stage.setScene(new Scene(root));
        //stage.showAndWait();

    }

    /**
     * in map.txt 0 is ground, 6 is clientHouse, 7 is grass, 8 is tree, 9 is restaurant
     * @param gc
     */
    private void drawMap(GraphicsContext gc){
        for(int x=0;x<15;x++){
            for(int y=0;y<15;y++){
                switch(map[y][x]) {
                    case 0:
                        gc.drawImage(ground, x * 40, y * 40);
                        break;
                    case 1:
                        gc.drawImage(car, x * 40, y *40);
                        break;
                    case 2:
                        gc.drawImage(cars, x * 40, y * 40);
                        break;
                    case 6:
                        gc.drawImage(clientHouse,x * 40,y * 40);
                        break;
                    case 7:
                        gc.drawImage(grass,x * 40,y * 40);
                        break;
                    case 8:
                        gc.drawImage(tree,x * 40,y * 40);
                        break;
                    case 9:
                        gc.drawImage(restaurant,x * 40, y*40);
                        break;
                }
            }
        }
    }

    /**
     * counts indexes to map from mouse location during click
     * @param x mouse x coordinate
     * @param y mouse y coordinate
     * @param location indexes in int table
     */
    private void getTileFromMouse(double x, double y, int[] location){
        int tileSize = mapSingleton.getTileSize();

        for(int yM=0;yM<mapSingleton.getMapSize();yM++){
            for(int xM=0;xM<mapSingleton.getMapSize();xM++){
                if(yM * tileSize < y && (yM+1) * tileSize > y && xM * tileSize < x && (xM+1) * tileSize > x){
                    //System.out.println(xM +" "+ yM);
                    location[0] = yM;
                    location[1] = xM;
                    break;
                }
            }
        }
    }

    /**
     * shows window with information about clients and deliverers
     * user can delete human/deliverer, return deliverer
     * @param human human, to get his info
     * @param index human index from the dataBase
     * @return
     * @throws Exception throws Exception because of fxmlLoader
     */
    private boolean showClientInfo(Human human, int index)throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowClientMap.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        ShowClientMapController controller = fxmlLoader.<ShowClientMapController>getController();
        controller.setClient(human, index);
        Stage clientStage = new Stage();
        if(Client.class.isInstance(human)){
            clientStage.setTitle("Client Information");
        }else{
            clientStage.setTitle("Deliverer Information");
        }
        //Image icon = new Image("../clientIcon.png");
        //clientStage.getIcons().add(icon);
        clientStage.initModality(Modality.APPLICATION_MODAL);
        clientStage.initStyle(StageStyle.UNDECORATED);
        clientStage.setScene(new Scene(root, 400, 280));
        clientStage.showAndWait();
        return controller.getIfUpdate();
    }

}
