package pl.pwr.enrollment.semester;

public class SemesterDetailsDto {

	private final Long id;
	private final String academicYear;
	private final String semesterType;
	private final Integer year;
	private final Integer semesterNumber;
	private final Integer currentEcts;
	private final Integer currentZzu;

	public SemesterDetailsDto(Long id, String academicYear, String semesterType, Integer year, Integer semesterNumber, Integer currentEcts, Integer currentZzu) {
		this.id = id;
		this.academicYear = academicYear;
		this.semesterType = semesterType;
		this.year = year;
		this.semesterNumber = semesterNumber;
		this.currentEcts = currentEcts;
		this.currentZzu = currentZzu;
	}

	public Long getId() {
		return id;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public String getSemesterType() {
		return semesterType;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getSemesterNumber() {
		return semesterNumber;
	}

	public Integer getCurrentEcts() {
		return currentEcts;
	}

	public Integer getCurrentZzu() {
		return currentZzu;
	}
}
