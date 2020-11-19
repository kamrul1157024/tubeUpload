package kamrul1157024.youtube;

public class VideoData {
    private String title,description,catagoryId,privacy,filename;

    public String getTitle() {
        return title;
    }

    public VideoData setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public VideoData setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCatagoryId() {
        return catagoryId;
    }

    public VideoData setCatagoryId(String catagoryId) {
        this.catagoryId = catagoryId;
        return this;
    }

    public String getPrivacy() {
        return privacy;
    }

    public VideoData setPrivacy(String privacy) {
        this.privacy = privacy;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public VideoData setFilename(String filename) {
        this.filename = filename;
        return this;
    }
}
