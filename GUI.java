import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUI extends JFrame {
    private CompetitorList competitorList;
    private JButton viewDetailsButton;
    private JButton amendDetailsButton;
    private JButton removeCompetitorButton;
    private JButton registerCompetitorButton;
    private JButton viewResultsButton;
    private JButton viewAllButton;
    private JButton sortByFirstNameButton;
    private JButton sortByScoresButton;
    private JButton recordScoresButton;

    public GUI(CompetitorList competitorList) {
        initializeComponents();
        setupLayout();
        setupListeners();
        this.competitorList = competitorList;
    }

    public CompetitorList getCompetitorList() {
        return this.competitorList;
    }

    private void initializeComponents() {
        viewDetailsButton = new JButton("View Competitor Details");
        amendDetailsButton = new JButton("Amend Competitor Details");
        removeCompetitorButton = new JButton("Remove Competitor");
        registerCompetitorButton = new JButton("Register Competitor");
        viewResultsButton = new JButton("View Results");
        viewAllButton = new JButton("View All Competitors");
        sortByFirstNameButton = new JButton("Sort Competitors By First Name");
        sortByScoresButton = new JButton("Sort Competitors By Scores");
        recordScoresButton = new JButton("Record Competitor Scores");
    }

    private void setupLayout() {
        setLayout(new FlowLayout());
        add(viewDetailsButton);
        add(amendDetailsButton);
        add(removeCompetitorButton);
        add(registerCompetitorButton);
        add(viewResultsButton);
        add(viewAllButton);
        add(sortByFirstNameButton);
        add(sortByScoresButton);
        add(recordScoresButton);
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

        registerCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRegisterCompetitorDialog();
            }
        });
        viewResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResultsDialog();
            }
        });
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllCompetitorsDialog();
            }
        });

        sortByFirstNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                competitorList.sortCompetitorsByName();
                showAllCompetitorsDialog();
            }
        });

        sortByScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                competitorList.sortCompetitorsByScores();
                showAllCompetitorsDialog();
            }
        });
        recordScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRecordScoresDialog();
            }
        });
    }

    private void showRoleSelectionDialog() {
        // Create a dialog to select a role
        String[] roles = { "Officials", "Staff", "Audience", "Competitors" };

        String selectedRole = (String) JOptionPane.showInputDialog(
                this,
                "Select a Role:",
                "Role Selection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                roles,
                roles[0]);

        // Handle the selected role
        if (selectedRole != null) {
            showPanel(selectedRole);
        }
    }

    private void showPanel(String role) {
        // Determine which panel to show based on the selected role
        switch (role) {
            case "Officials":
                OfficialsPanel officialsPanel = new OfficialsPanel(this);
                showCustomDialog("Officials Panel", officialsPanel);
                break;
            case "Staff":
                StaffPanel staffPanel = new StaffPanel(this);
                showCustomDialog("Staff Panel", staffPanel);
                break;
            case "Audience":
                AudiencePanel audiencePanel = new AudiencePanel(this);
                showCustomDialog("Audience Panel", audiencePanel);
                break;
            case "Competitors":
                CompetitorsPanel competitorsPanel = new CompetitorsPanel(this);
                showCustomDialog("Competitors Panel", competitorsPanel);
                break;
            default:
                // Handle unknown roles
                break;
        }
    }

    private void showCustomDialog(String title, JPanel panel) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public void showRecordScoresDialog() {
        JTextField competitorNumberField = new JTextField();
        JTextField scoresField = new JTextField();

        Object[] message = {
                "Competitor Number:", competitorNumberField,
                "Scores (comma-separated):", scoresField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Record Scores", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int competitorNumber = Integer.parseInt(competitorNumberField.getText());
                String[] scoresStr = scoresField.getText().split(",");
                int[] scores = Arrays.stream(scoresStr).mapToInt(Integer::parseInt).toArray();

                competitorList.recordCompetitorScores(competitorNumber, scores);

                JOptionPane.showMessageDialog(this, "Scores recorded successfully.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void showAllCompetitorsDialog() {
        String table = competitorList.generateCompetitorsTable();

        JTextArea textArea = new JTextArea(table);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(this, scrollPane, "All Competitors", JOptionPane.PLAIN_MESSAGE);
    }

    public void showResultsDialog() {
        String report = competitorList.generateFinalReport();

        JTextArea textArea = new JTextArea(report);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(this, scrollPane, "Final Results Report", JOptionPane.PLAIN_MESSAGE);
    }

    public void showDetailsDialog() {
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

    public void showDetailsOptionsDialog(Competitor competitor) {
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

    public void showAmendDialog() {
        String competitorID = JOptionPane.showInputDialog("Enter Competitor ID:");
        if (competitorID != null && !competitorID.isEmpty()) {
            Competitor details = competitorList.searchCompetitorByNumber(Integer.parseInt(competitorID));
            if (details != null) {
                showAmendDetailsDialog(details);
            } else {
                JOptionPane.showMessageDialog(this, "Competitor not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void showDetails(String details) {
        JTextArea textArea = new JTextArea(details);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Competitor Details", JOptionPane.PLAIN_MESSAGE);
    }

    public void showAmendDetailsDialog(Competitor competitor) {
        JTextField firstNameField = new JTextField(competitor.getName().getFirstName());
        JTextField lastNameField = new JTextField(competitor.getName().getLastName());
        JTextField emailField = new JTextField(competitor.getEmail());
        JTextField dateOfBirthField = new JTextField(competitor.getDateOfBirth());
        JTextField categoryField = new JTextField(competitor.getCategory());
        JTextField levelField = new JTextField(competitor.getLevel());

        Object[] message = {
                "First Name:", firstNameField,
                "Last Name:", lastNameField,
                "Email:", emailField,
                "Date of Birth:", dateOfBirthField,
                "Category:", categoryField,
                "Level:", levelField,
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

            // Create a new Competitor object
            Competitor newDetails = new Competitor(competitor.getCompetitorNumber(), new Name(firstName, lastName),
                    email,
                    dateOfBirth, category, level, competitor.getScores());

            competitorList.amendCompetitorByID(competitor.getCompetitorNumber(), newDetails);

            JOptionPane.showMessageDialog(this, "Competitor details amended successfully.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void showRemoveDialog() {
        String competitorID = JOptionPane.showInputDialog("Enter Competitor ID:");
        if (competitorID != null && !competitorID.isEmpty()) {
            int id = Integer.parseInt(competitorID);
            competitorList.removeCompetitorByID(id);

            JOptionPane.showMessageDialog(this, "Competitor removed successfully.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void showRegisterCompetitorDialog() {
        JTextField numberField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField dateOfBirthField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField levelField = new JTextField();
        JTextField scoresField = new JTextField();

        Object[] message = {
                "Competitor Number:", numberField,
                "First Name:", firstNameField,
                "Last Name:", lastNameField,
                "Email:", emailField,
                "Date of Birth:", dateOfBirthField,
                "Category:", categoryField,
                "Level:", levelField,
                "Scores (comma-separated):", scoresField,
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Register Competitor", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            // Collect the values from the text fields
            int competitorNumber = Integer.parseInt(numberField.getText());
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String dateOfBirth = dateOfBirthField.getText();
            String category = categoryField.getText();
            String level = levelField.getText();
            String scoresString = scoresField.getText();

            // Validate input
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()
                    || dateOfBirth.isEmpty()
                    || category.isEmpty() || level.isEmpty() || scoresString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convert scores from string to int array
            int[] scores = Arrays.stream(scoresString.split(","))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // Create a new Competitor object
            Competitor newCompetitor = new Competitor(competitorNumber, new Name(firstName, lastName), email,
                    dateOfBirth, category,
                    level, scores);

            // Check if a competitor with the same email and category already exists
            Competitor existingCompetitor = competitorList.searchCompetitorByEmailAndCategory(email, category);
            if (existingCompetitor != null) {
                // Competitor with the same email and category already exists
                JOptionPane.showMessageDialog(this, "Competitor with the same email and category already exists.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Proceed with registration
                competitorList.addCompetitorToCSV(newCompetitor);
                JOptionPane.showMessageDialog(this, "Competitor registered successfully.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void launchGUI() {
        showRoleSelectionDialog();
    }
}
