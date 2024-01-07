import java.util.ArrayList;

public class Competition {
    private int ID;
    private String competitionName;
    private String competitionCategory;
    private String competitionDate;
    private ArrayList<Competitor> competitorsList;

    public Competition(int ID, String competitionName, String competitionCategory, String competitionDate) {
        this.ID = ID;
        this.competitionName = competitionName;
        this.competitionCategory = competitionCategory;
        this.competitionDate = competitionDate;
        this.competitorsList = new ArrayList<>();
    }

    // Getter and Setter methods

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionCategory() {
        return competitionCategory;
    }

    public void setCompetitionCategory(String competitionCategory) {
        this.competitionCategory = competitionCategory;
    }

    public String getCompetitionDate() {
        return competitionDate;
    }

    public void setCompetitionDate(String competitionDate) {
        this.competitionDate = competitionDate;
    }

    public ArrayList<Competitor> getCompetitorsList() {
        return competitorsList;
    }

    public void setCompetitorsList(ArrayList<Competitor> competitorsList) {
        this.competitorsList = competitorsList;
    }

    // Method to register competitor using CompetitorList methods
    public void registerCompetitor(Competitor competitor, CompetitorList competitorList) {
        competitorList.addCompetitorToCSV(competitor);
        competitorsList.add(competitor);
        System.out.println("Competitor registered for the competition.");
    }

    // Method to search competitor by ID using CompetitorList methods
    public Competitor searchCompetitorByID(int competitorID, CompetitorList competitorList) {
        return competitorList.searchCompetitorByNumber(competitorID);
    }
}
