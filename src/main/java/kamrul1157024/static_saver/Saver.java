package kamrul1157024.static_saver;

import javafx.stage.Stage;
import kamrul1157024.files.FileData;
import kamrul1157024.files.FileProcess;

import java.util.ArrayList;

public class Saver {
    public static Stage stage=null;
    public static String foldername=null;
    public static boolean isSubFolderIncluded=false;
    public static ArrayList<FileData> fileDatalists=new ArrayList<FileData>();
    public static FileProcess fileProcess=null;
    public static ArrayList<String> videos=null;
    public static String fileToUpload=null;
}
