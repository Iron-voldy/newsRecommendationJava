package model;

public class News {

    private int id;            // Add the 'id' field to store the article's ID
    private String title;
    private String description;
    private String url;
    private String category;

    // Constructor to initialize the article
    public News(int id, String title, String description, String url, String category) {
        this.id = id;           // Initialize the 'id' in the constructor
        this.title = title;
        this.description = description;
        this.url = url;
        this.category = category;
    }

    // Getter for 'id'
    public int getId() {
        return id;
    }

    // Setters and getters for other fields
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
