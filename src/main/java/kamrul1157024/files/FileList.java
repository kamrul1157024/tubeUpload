package kamrul1157024.files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import kamrul1157024.scene_management.SceneController;
import kamrul1157024.static_saver.Saver;
import kamrul1157024.static_saver.SceneControllerSaver;

import java.io.File;
import java.util.ArrayList;

public class FileList {
    @FXML
    TableView tableView;
    @FXML
    TableColumn name;
    @FXML
    TableColumn size;
    @FXML
    TableColumn check;
    @FXML
    public void initialize() {
        ObservableList observableFileList= FXCollections.observableList(Saver.fileDatalists);
        name.setCellValueFactory(new PropertyValueFactory("name"));
        size.setCellValueFactory(new PropertyValueFactory("size"));
        check.setCellValueFactory(new PropertyValueFactory("check"));
        check.setCellFactory(new Callback() {
            public Object call(Object column) {
                return new CheckBoxTableCell();
            }
        });
        tableView.setItems(observableFileList);
        tableView.setEditable(true);

    }
    @FXML
    void close()
    {
        for(String video:Saver.videos)
        {
            Saver.fileToUpload=video;
        }
        SceneControllerSaver.primaryStage.setScene(SceneControllerSaver.sceneController.getScene("Progress"));
        Saver.stage.close();
    }



}
