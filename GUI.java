import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private CompetitorList competitorList;
    private JButton viewDetailsButton;
    private JButton amendDetailsButton;
    private JButton removeCompetitorButton;

    public GUI(CompetitorList competitorList) {
        initializeComponents();
        setupLayout();
        setupListeners();
        this.competitorList = competitorList;
    }

    private void initializeComponents() {
        viewDetailsButton = new JButton("View Competitor Details");
        amendDetailsButton = new JButton("Amend Competitor Details");
        removeCompetitorButton = new JButton("Remove Competitor");
    }

    private void setupLayout() {
        setLayout(new FlowLayout());
        add(viewDetailsButton);
        add(amendDetailsButton);
        add(removeCompetitorButton);
    }

    private void setupListeners() {
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetailsDialog();
            }
        });

        amendDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAmendDialog();
            }
        });

        removeCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRemoveDialog();
            }
        });
    }

    private void showDetailsDialog() {
        String competitorID = JOptionPane.showInputDialog("Enter Competitor ID:");
        if (competitorID != null && !competitorID.isEmpty()) {
            int id = Integer.parseInt(competitorID);
            Competitor competitor = competitorList.searchCompetitorByNumber(id);
            if (competitor != null) {
                showDetailsOptionsDialog(competitor);
            } else {
                JOptionPane.showMessageDialog(this, "Competitor not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showDetailsOptionsDialog(Competitor competitor) {
        Object[] options = { "Short Details", "Full Details" };
        int choice = JOptionPane.showOptionDialog(this,
                "Choose details type:",
                "Details Options",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            showDetails(competitor.generateShortDetails());
        } else if (choice == JOptionPane.NO_OPTION) {
            showDetails(competitor.generateFullDetails());
        }
    }

    private void showAmendDialog() {
        String competitorID = JOptionPane.showInputDialog("Enter Competitor ID:");
        if (competitorID != null && !competitorID.isEmpty()) {
            // Assume you have a method in Officials to get competitor details by ID
            Competitor details = competitorList.searchCompetitorByNumber(Integer.parseInt(competitorID));
            if (details != null) {
                showAmendDetailsDialog(details);
            } else {
                JOptionPane.showMessageDialog(this, "Competitor not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showDetails(String details) {
        JTextArea textArea = new JTextArea(details);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Competitor Details", JOptionPane.PLAIN_MESSAGE);
    }

    private void showAmendDetailsDialog(Competitor competitor) {
        // Assume you have text fields for each detail in the GUI
        JTextField firstNameField = new JTextField(competitor.getName().getFirstName());
        JTextField lastNameField = new JTextField(competitor.getName().getLastName());
        JTextField emailField = new JTextField(competitor.getEmail());
        JTextField dateOfBirthField = new JTextField(competitor.getDateOfBirth());
        JTextField categoryField = new JTextField(competitor.getCategory());
        JTextField levelField = new JTextField(competitor.getLevel());
        // Add more fields for other details...

        Object[] message = {
                "First Name:", firstNameField,
                "Last Name:", lastNameField,
                "Email:", emailField,
                "Date of Birth:", dateOfBirthField,
                "Category:", categoryField,
                "Level:", levelField,
                // Add more fields...
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Amend Competitor Details",
                JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            // Collect the values from the text fields
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String dateOfBirth = dateOfBirthField.getText();
            String category = categoryField.getText();
            String level = levelField.getText();
            // Get other details...

            // Create a new Competitor object
            Competitor newDetails = new Competitor(competitor.getCompetitorNumber(), new Name(firstName, lastName),
                    email,
                    dateOfBirth, category, level, competitor.getScores());

            // Assume you have a method in Officials to amend competitor details
            Official official = new Official(1, null, null);
            official.amendCompetitorDetails(competitor.getCompetitorNumber(), newDetails, competitorList);

            JOptionPane.showMessageDialog(this, "Competitor details amended successfully.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showRemoveDialog() {
        String competitorID = JOptionPane.showInputDialog("Enter Competitor ID:");
        if (competitorID != null && !competitorID.isEmpty()) {
            int id = Integer.parseInt(competitorID);
            // Assume you have a method in Officials to remove a competitor
            Official official = new Official(1, null, null);
            official.removeCompetitor(id, competitorList);

            JOptionPane.showMessageDialog(this, "Competitor removed successfully.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void launchGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Competitor Details Viewer");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
