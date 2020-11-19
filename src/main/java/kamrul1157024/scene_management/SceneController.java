package kamrul1157024.scene_management;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public class SceneController {
    private HashMap<String, Scene> screenMap = new HashMap<>();

    public SceneController() {
    }
    public void addScreen(String name,Pane pane)
    {
        screenMap.put(name,new Scene(pane));
    }
    public void removeScreen(String name){
        screenMap.remove(name);
    }

    public Scene getScene(String name){
        return screenMap.get(name);
    }
}