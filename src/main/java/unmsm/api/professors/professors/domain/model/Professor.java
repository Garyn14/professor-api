package unmsm.api.professors.professors.domain.model;


import java.util.List;
import java.util.Map;

/**
 * @author garyn
 * @date 8/05/2025 00:38
 */

public class Professor {
    private String id;
    private String name;
    private String faculty;
    private Integer ratingsCount;
    private Double averageRating;
    private String profileUrl;
    private List<String> tags;
    private Map<String, String> stats;
    private List<Map<String, String>> comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Map<String, String> getStats() {
        return stats;
    }

    public void setStats(Map<String, String> stats) {
        this.stats = stats;
    }

    public List<Map<String, String>> getComments() {
        return comments;
    }

    public void setComments(List<Map<String, String>> comments) {
        this.comments = comments;
    }
}
