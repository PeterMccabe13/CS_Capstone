import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Arrays;
import javax.swing.*;

/**
 * Enhanced slideshow for wellness resorts.
 * Replaces hardcoded logic with reusable Resort model and looped layout.
 */
public class SlideShow extends JFrame {

    private JPanel slidePane;
    private JPanel textPane;
    private JPanel buttonPane;
    private CardLayout card;
    private CardLayout cardText;
    private JButton btnPrev;
    private JButton btnNext;

    public SlideShow() {
        initComponent();
    }

    private void initComponent() {
        card = new CardLayout();
        cardText = new CardLayout();
        slidePane = new JPanel(card);
        textPane = new JPanel(cardText);
        buttonPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Window setup
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Top 5 Detox and Wellness Destinations SlideShow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 50));

        // Light blue background for description area
        textPane.setBackground(new Color(173, 216, 230));
        textPane.setPreferredSize(new Dimension(800, 60));

        // Build resort data
        List<Resort> resorts = buildResortData();

        // Add slides and descriptions
        for (int i = 0; i < resorts.size(); i++) {
            Resort resort = resorts.get(i);

            // Load image from classpath
            java.net.URL imageURL = getClass().getResource(resort.getImagePath());
            JLabel lblSlide = new JLabel();
            if (imageURL != null) {
                ImageIcon icon = new ImageIcon(
                    new ImageIcon(imageURL).getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH)
                );
                lblSlide.setIcon(icon);
            } else {
                lblSlide.setText("Image not found: " + resort.getImagePath());
            }

            // Build description label
            JLabel lblText = new JLabel(
                "<html><body><font size='5'><b>" + resort.getName() + "</b></font><br>" + resort.getDescription() + "</body></html>"
            );

            slidePane.add(lblSlide, "card" + i);
            textPane.add(lblText, "cardText" + i);
        }

        // Add buttons
        btnPrev = new JButton("Previous");
        btnPrev.addActionListener(e -> {
            card.previous(slidePane);
            cardText.previous(textPane);
        });

        btnNext = new JButton("Next");
        btnNext.addActionListener(e -> {
            card.next(slidePane);
            cardText.next(textPane);
        });

        buttonPane.add(btnPrev);
        buttonPane.add(btnNext);

        // Add panels to frame
        getContentPane().add(slidePane, BorderLayout.CENTER);
        getContentPane().add(textPane, BorderLayout.SOUTH);
        getContentPane().add(buttonPane, BorderLayout.NORTH);
    }

    /**
     * Returns a list of Resort objects.
     */
    private List<Resort> buildResortData() {
        return Arrays.asList(
            new Resort("Blue Dreams Resort & Spa, Turkey",
                "Unwind on the Turkish coastline with luxury spa treatments and ocean views.",
                "/resources/Blue_Dreams_Turkey.jpg"),

            new Resort("Catalonia Resort & Spa, Cancun",
                "Recharge in Cancun with beach yoga, fresh cuisine, and wellness programs.",
                "/resources/Catalonia_Cancun.jpg"),

            new Resort("Hyatt Regency Lake Tahoe Resort & Spa, Nevada",
                "Relax lakeside with massages, forest hikes, and spa and casino access in the Sierra Nevadas.",
                "/resources/Hyatt_Regency_Lake_Tahoe.jpg"),

            new Resort("Mansion Resort & Spa, Bali",
                "Detox in the heart of Bali with artful surroundings, herbal treatments, and tranquility.",
                "/resources/Mansion_Resort_and_Spa_Bali.jpg"),

            new Resort("Vale Resort, Golf, & Spa, UK",
                "Escape to the Welsh countryside with golf, spa therapy, and peaceful scenery.",
                "/resources/Vale_Hotel_UK.jpg")
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SlideShow ss = new SlideShow();
            ss.setVisible(true);
        });
    }
}

