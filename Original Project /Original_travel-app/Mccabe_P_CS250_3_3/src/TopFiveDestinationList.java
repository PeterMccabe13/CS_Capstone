import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}


class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel();


        //Make updates to your top 5 list below. Import the new image files to resources directory.
        addDestinationNameAndPicture("1. Amalfi Coast, Italy - A breathtaking stretch of coastal villages, cliffs, and crystal clear waters. (Photo by Paolo Costa Baldi)",new ImageIcon( new ImageIcon(getClass().getResource("/resources/Amalfi_Coast.jpg"))
        		.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
        addDestinationNameAndPicture("2. Santorini, Greece - An island known for whitewashed buildings, blue waters, and stunning sunsets. (Photo by Dietmar Rabich)",new ImageIcon( new ImageIcon(getClass().getResource("/resources/Santorini.jpg"))
        		.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
        addDestinationNameAndPicture("3. Tokyo, Japan - A metropolis of neon nightlife and futuristic tech. (Photo by Basile Morin)",new ImageIcon( new ImageIcon(getClass().getResource("/resources/Tokyo.jpg"))
        		.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
        addDestinationNameAndPicture("4. Paris, France - Romantic capital of the world with an abundance of art, fashion, and fine cuisine. (Photo by Benh LIEU SONG)",new ImageIcon( new ImageIcon(getClass().getResource("/resources/Paris.jpg"))
        		.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
        addDestinationNameAndPicture("5. New York City, New York - The city that never sleeps is a vibrant, diverse, and bustling global hub for culture and entertainment. (Photo by DLLU)",new ImageIcon( new ImageIcon(getClass().getResource("/resources/NYC.jpg"))
        		.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
        
        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(2);

        list.setCellRenderer(renderer);

        //Color and Text Customization
        list.setBackground(new Color(240, 248, 255));
        list.setForeground(Color.DARK_GRAY);
        list.setSelectionBackground(new Color(173, 216, 230));
        list.setFont(new Font("Calibri", Font.PLAIN, 14));
        
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        //Label with my name
        JLabel nameLabel = new JLabel("Created by Pete", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Calibri", Font.BOLD, 12));;
        getContentPane().add(nameLabel, BorderLayout.SOUTH);
    }

    private void addDestinationNameAndPicture(String text, Icon icon) {
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }
}


class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}


class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean hasFocus) {
        // The object from the combo box model MUST be a TextAndIcon.
        TextAndIcon tai = (TextAndIcon) value;

        // Sets text and icon on 'this' JLabel.
        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}