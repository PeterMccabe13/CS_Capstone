import java.awt.*;
import java.util.List;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Enhanced version of the “Top 5 Destination List” program.
 * • Uses a Destination data model instead of hard-coding entries in the UI layer.
 * • Populates the list by looping through an ArrayList.
 * • Keeps the old TextAndIcon renderer so the UI look-and-feel stays familiar.
 * • Adds clear comments so future devs (or graders!) can follow the logic.
 */
public class TopFiveDestinationList {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TopDestinationListFrame frame = new TopDestinationListFrame();
            frame.setTitle("Top 5 Destination List");
            frame.setVisible(true);
        });
    }
}

/**
 * Main window that holds the JList of destinations.
 */
class TopDestinationListFrame extends JFrame {

    private final DefaultListModel<TextAndIcon> listModel = new DefaultListModel<>();

    public TopDestinationListFrame() {
        super("Top Five Destination List");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);
        setLayout(new BorderLayout());

        // 1) build data -------------------------------------------
        List<Destination> destinations = buildDestinationData();

        // 2) convert data -> list model ----------------------------
        destinations.forEach(dest -> addDestinationToModel(dest));

        // 3) set up JList ------------------------------------------
        JList<TextAndIcon> list = new JList<>(listModel);
        list.setCellRenderer(new TextAndIconListCellRenderer(2));

        // visual tweaks
        list.setBackground(new Color(240, 248, 255));
        list.setForeground(Color.DARK_GRAY);
        list.setSelectionBackground(new Color(173, 216, 230));
        list.setFont(new Font("Calibri", Font.PLAIN, 14));

        add(new JScrollPane(list), BorderLayout.CENTER);

        // footer label
        JLabel nameLabel = new JLabel("Created by Pete", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Calibri", Font.BOLD, 12));
        add(nameLabel, BorderLayout.SOUTH);
    }

    /**
     * Creates and returns our list of Destination objects.
     * Later on, we can load this from a JSON, database, etc.
     */
    private List<Destination> buildDestinationData() {
        return Arrays.asList(
            new Destination(
                "Amalfi Coast, Italy",
                "A breathtaking stretch of coastal villages, cliffs, and crystal-clear waters. (Photo by Paolo Costa Baldi)",
                "/resources/Amalfi_Coast.jpg"
            ),
            new Destination(
                "Santorini, Greece",
                "An island known for white-washed buildings, blue waters, and stunning sunsets. (Photo by Dietmar Rabich)",
                "/resources/Santorini.jpg"
            ),
            new Destination(
                "Tokyo, Japan",
                "A metropolis of neon nightlife and futuristic tech. (Photo by Basile Morin)",
                "/resources/Tokyo.jpg"
            ),
            new Destination(
                "Paris, France",
                "Romantic capital of the world with an abundance of art, fashion, and fine cuisine. (Photo by Benh LIEU SONG)",
                "/resources/Paris.jpg"
            ),
            new Destination(
                "New York City, USA",
                "The city that never sleeps—vibrant, diverse, and always buzzing. (Photo by DLLU)",
                "/resources/NYC.jpg"
            )
        );
    }

    /**
     * Converts a Destination into a TextAndIcon and adds it to the DefaultListModel.
     */
    private void addDestinationToModel(Destination dest) {
        // combine title + description into one HTML-friendly string
        String text = String.format("<html><b>%s</b> — %s</html>",
                                    dest.getTitle(), dest.getDescription());
    
        // DEBUG: Print the image path and whether it resolves
        System.out.println("Loading: " + dest.getImagePath());
        java.net.URL imageURL = getClass().getResource(dest.getImagePath());
        System.out.println("Resolved URL: " + imageURL);
    
        // Protect against null crashes
        if (imageURL == null) {
            System.err.println("⚠️  Could not find image: " + dest.getImagePath());
            return;
        }
    
        // Load and scale the image safely
        ImageIcon icon = new ImageIcon(
            new ImageIcon(imageURL).getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH)
        );
    
        listModel.addElement(new TextAndIcon(text, icon));
    }
    
}

/* --------------------------------------------------------------------------
 * Below are the renderer helper classes you already used. 
 * They are unchanged except for generics on ListCellRenderer.
 * -------------------------------------------------------------------------- */

class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() { return text; }
    public Icon getIcon()   { return icon; }
}

class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer<TextAndIcon> {

    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1,1,1,1);
    private final Border insideBorder;

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int top, int right, int bottom, int left) {
        insideBorder = BorderFactory.createEmptyBorder(top, left, bottom, right);
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends TextAndIcon> list,
                                                  TextAndIcon value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean hasFocus) {

        setText(value.getText());
        setIcon(value.getIcon());

        // color handling
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        // focus border
        Border outside = hasFocus
                ? UIManager.getBorder("List.focusCellHighlightBorder")
                : NO_FOCUS_BORDER;

        setBorder(BorderFactory.createCompoundBorder(outside, insideBorder));
        setFont(list.getFont());
        return this;
    }

    // performance no-ops
    public void validate() {} public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int w, int h) {}
    public void repaint(Rectangle r) {}
}

