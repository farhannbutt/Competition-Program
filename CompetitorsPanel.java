import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class CompetitorsPanel extends JPanel {
    private JButton viewResultsButton;
    private JButton viewSummaryResultsButton;
    private JButton viewAllButton;
    private JButton sortByFirstNameButton;
    private JButton sortByScoresButton;
    private JButton viewDetailsButton;
    private JButton registerCompetitorButton;
    private GUI gui;

    public CompetitorsPanel(GUI gui) {
        this.gui = gui;

        viewResultsButton = new JButton("View Results");
        viewSummaryResultsButton = new JButton("View Result Summary");
        viewDetailsButton = new JButton("View Competitor Details");
        registerCompetitorButton = new JButton("Register for Competition");
        viewAllButton = new JButton("View All Competitors");
        sortByFirstNameButton = new JButton("Sort Competitors By First Name");
        sortByScoresButton = new JButton("Sort Competitors By Scores");

        setLayout(new FlowLayout());
        add(viewResultsButton);
        add(viewSummaryResultsButton);
        add(viewDetailsButton);
        add(registerCompetitorButton);
        add(viewAllButton);
        add(sortByFirstNameButton);
        add(sortByScoresButton);

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
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showDetailsDialog();
            }
        });
        registerCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showRegisterCompetitorDialog();
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
    }
}
