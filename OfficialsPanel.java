import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class OfficialsPanel extends JPanel {
    private JButton viewDetailsButton;
    private JButton amendDetailsButton;
    private JButton removeCompetitorButton;
    private JButton registerCompetitorButton;
    private JButton viewResultsButton;
    private JButton viewAllButton;
    private JButton sortByFirstNameButton;
    private JButton sortByScoresButton;
    private JButton generateReportButton;
    private GUI gui;

    public OfficialsPanel(GUI gui) {
        this.gui = gui;

        viewDetailsButton = new JButton("View Competitor Details");
        amendDetailsButton = new JButton("Amend Competitor Details");
        removeCompetitorButton = new JButton("Remove Competitor");
        registerCompetitorButton = new JButton("Register Competitor");
        viewResultsButton = new JButton("View Results");
        viewAllButton = new JButton("View All Competitors");
        sortByFirstNameButton = new JButton("Sort Competitors By First Name");
        sortByScoresButton = new JButton("Sort Competitors By Scores");
        generateReportButton = new JButton("Generate Report");

        setLayout(new FlowLayout());
        add(viewDetailsButton);
        add(amendDetailsButton);
        add(removeCompetitorButton);
        add(registerCompetitorButton);
        add(viewResultsButton);
        add(viewAllButton);
        add(sortByFirstNameButton);
        add(sortByScoresButton);
        add(generateReportButton);

        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showDetailsDialog();
            }
        });

        amendDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showAmendDialog();
            }
        });

        removeCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showRemoveDialog();
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

        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showResultsDialog();
            }
        });
    }
}
