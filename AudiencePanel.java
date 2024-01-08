import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class AudiencePanel extends JPanel {
    private JButton viewResultsButton;
    private JButton viewAllButton;
    private JButton viewDetailsButton;
    private GUI gui;

    public AudiencePanel(GUI gui) {
        this.gui = gui;

        viewResultsButton = new JButton("View Results");
        viewDetailsButton = new JButton("View Competitor Details");
        viewAllButton = new JButton("View All Competitors");

        setLayout(new FlowLayout());
        add(viewResultsButton);
        add(viewDetailsButton);
        add(viewAllButton);

        viewResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showResultsDialog();
            }
        });
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showDetailsDialog();
            }
        });
        viewResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showResultsDialog();
            }
        });
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showAllCompetitorsDialog();
            }
        });
    }
}
