package kamrul1157024.files;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileData {
    private final SimpleStringProperty name,size;

    public boolean isCheck() {
        return check.get();
    }

    public SimpleBooleanProperty checkProperty() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check.set(check);
    }

    private final SimpleBooleanProperty check;
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getSize() {
        return size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return size;
    }

    FileData(String name,String size,boolean check)
    {
        this.name=new SimpleStringProperty(name);
        this.size=new SimpleStringProperty(size);
        this.check=new SimpleBooleanProperty(check);
    }
}
