/**
 * Simple data model for a wellness resort used in the slideshow.
 */
public class Resort {
    private final String name;
    private final String description;
    private final String imagePath;

    public Resort(String name, String description, String imagePath) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}
