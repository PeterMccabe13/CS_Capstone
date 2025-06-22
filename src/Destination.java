/**
 * A simple data class that represents a travel destination.
 * Each Destination knows its name / title, a short description,
 * and the class-path location of its image.
 */
public class Destination {

    private final String title;       // e.g., "Amalfi Coast, Italy"
    private final String description; // short blurb for the list
    private final String imagePath;   // resource path, e.g., "/resources/Amalfi_Coast.jpg"

    public Destination(String title, String description, String imagePath) {
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}
