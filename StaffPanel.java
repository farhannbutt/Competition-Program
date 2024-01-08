import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class StaffPanel extends JPanel {
    private JButton viewDetailsButton;
    private JButton viewResultsButton;
    private JButton viewAllButton;
    private JButton sortByFirstNameButton;
    private JButton sortByScoresButton;
    private JButton recordScoresButton;
    private JButton viewSummaryResultsButton;
    private GUI gui;

    public StaffPanel(GUI gui) {
        this.gui = gui;

        viewDetailsButton = new JButton("View Competitor Details");
        viewResultsButton = new JButton("View Results");
        viewSummaryResultsButton = new JButton("View Result Summary");
        viewAllButton = new JButton("View All Competitors");
        sortByFirstNameButton = new JButton("Sort Competitors By First Name");
        sortByScoresButton = new JButton("Sort Competitors By Scores");
        recordScoresButton = new JButton("Record Competitor Scores");

        setLayout(new FlowLayout());
        add(viewDetailsButton);
        add(viewResultsButton);
        add(viewAllButton);
        add(sortByFirstNameButton);
        add(sortByScoresButton);
        add(recordScoresButton);

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
        viewSummaryResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showSummaryResultsDialog();
            }
        });
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showAllCompetitorsDialog();
            }
        });

        sortByFirstNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.getCompetitorList().sortCompetitorsByName();
                gui.showAllCompetitorsDialog();
            }
        });

        sortByScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.getCompetitorList().sortCompetitorsByScores();
                gui.showAllCompetitorsDialog();
            }
        });
        recordScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showRecordScoresDialog();
            }
        });
    }
}
