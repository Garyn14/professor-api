package unmsm.api.professors.professors.application.dto;

import java.time.LocalDate;

/**
 * @author garyn
 * @date 14/06/2025 15:32
 */
public class OrcidResponse {
  private String orcidId;
  private String keyWords;
  private String currentJob;
  private boolean publications;
  private LocalDate lastUpdated;

  public String getOrcidId() {
    return orcidId;
  }

  public void setOrcidId(String orcidId) {
    this.orcidId = orcidId;
  }

  public String getKeyWords() {
    return keyWords;
  }

  public void setKeyWords(String keyWords) {
    this.keyWords = keyWords;
  }

  public String getCurrentJob() {
    return currentJob;
  }

  public void setCurrentJob(String currentJob) {
    this.currentJob = currentJob;
  }

  public boolean isPublications() {
    return publications;
  }

  public void setPublications(boolean publications) {
    this.publications = publications;
  }

  public LocalDate getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(LocalDate lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  @Override
  public String toString() {
    return "OrcidResponse{"
        + "orcidId='"
        + orcidId
        + '\''
        + ", keyWords='"
        + keyWords
        + '\''
        + ", currentJob='"
        + currentJob
        + '\''
        + ", publications="
        + publications
        + ", lastUpdated="
        + lastUpdated
        + '}';
  }
}
