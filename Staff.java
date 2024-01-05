public class Staff {
    private int ID;
    private Name name;
    private String accessLevel;

    public Staff(int ID, Name name, String accessLevel) {
        this.ID = ID;
        this.name = name;
        this.accessLevel = accessLevel;
    }

    // Getter and Setter methods

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    // Method to record scores using CompetitorList methods
    public void recordScores(int competitorNumber, int[] scores, CompetitorList competitorList) {
        competitorList.recordCompetitorScores(competitorNumber, scores, "competitors.csv");
        System.out.println("Scores recorded successfully.");
    }

    // Method to search competitor by number using CompetitorList methods
    public void searchCompetitorByNumber(int competitorNumber, CompetitorList competitorList) {
        Competitor foundCompetitor = competitorList.searchCompetitorByNumber(competitorNumber);
        if (foundCompetitor != null) {
            System.out.println("Competitor found: " + foundCompetitor.generateShortDetails());
        } else {
            System.out.println("Competitor not found.");
        }
    }

    // Method to generate summary report using CompetitorList methods
    public void generateSummaryReport(CompetitorList competitorList) {
        competitorList.saveReportToFile("ResultReport.txt");
        System.out.println("Summary report generated successfully.");
    }
}
