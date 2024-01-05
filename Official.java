public class Official {
    private int ID;
    private Name name;
    private String accessLevel;

    public Official(int ID, Name name, String accessLevel) {
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

    // Method to register competitor on arrival using CompetitorList methods
    public void registerCompetitorOnArrival(Competitor competitor, CompetitorList competitorList) {
        competitorList.addCompetitorToCSV(competitor, "competitors.csv");
        System.out.println("Competitor registered on arrival.");
    }

    // Method to remove competitor using CompetitorList methods
    public void removeCompetitor(int competitorNumber, CompetitorList competitorList) {
        competitorList.removeCompetitorByID(competitorNumber, "competitors.csv");
        System.out.println("Competitor removed successfully.");
    }

    // Method to amend competitor details using CompetitorList methods
    public void amendCompetitorDetails(int competitorNumber, Competitor updatedCompetitor,
            CompetitorList competitorList) {
        competitorList.amendCompetitorByID(competitorNumber, updatedCompetitor, "competitors.csv");
        System.out.println("Competitor details amended successfully.");
    }
}
