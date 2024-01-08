import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class AudiencePanel extends JPanel {
    private JButton viewResultsButton;
    private JButton viewAllButton;
    private GUI gui;

    public AudiencePanel(GUI gui) {
        this.gui = gui;

        viewResultsButton = new JButton("View Competition Results");
        viewAllButton = new JButton("View All Competitors");

        setLayout(new FlowLayout());
        add(viewResultsButton);
        add(viewAllButton);

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
