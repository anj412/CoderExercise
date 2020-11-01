package ru.croc.coder.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "restrictions")
public class ExerciseRestriction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@OneToMany(mappedBy = "restriction")	
	private Set<Exercise> exercises;
		
	private String enterData;
    private String exitData;
    
    private String referenceSolution;

    private LocalDate dateOpened;
    private LocalDate dateClosed;

    private Duration solutionDuration;

    private int maxRam;
    private int maxDiskStorage;

    public String getEnterData() {
        return enterData;
    }

    public void setEnterData(String enterData) {
        this.enterData = enterData;
    }

    public String getExitData() {
        return exitData;
    }

    public void setExitData(String exitData) {
        this.exitData = exitData;
    }

    public String getReferenceSolution() {
        return referenceSolution;
    }

    public void setReferenceSolution(String referenceSolution) {
        this.referenceSolution = referenceSolution;
    }

    public LocalDate getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }

    public LocalDate getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(LocalDate dateClosed) {
        this.dateClosed = dateClosed;
    }

    public Duration getSolutionDuration() {
        return solutionDuration;
    }

    public void setSolutionDuration(Duration solutionDuration) {
        this.solutionDuration = solutionDuration;
    }

    public int getMaxRam() {
        return maxRam;
    }

    public void setMaxRam(int maxRam) {
        this.maxRam = maxRam;
    }

    public int getMaxDiskStorage() {
        return maxDiskStorage;
    }

    public void setMaxDiskStorage(int maxDiskStorage) {
        this.maxDiskStorage = maxDiskStorage;
    }
}
