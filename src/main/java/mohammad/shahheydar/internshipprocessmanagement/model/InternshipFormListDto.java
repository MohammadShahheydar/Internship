package mohammad.shahheydar.internshipprocessmanagement.model;

public record InternshipFormListDto(
        Long id,
        String state,
        String city,
        String placeName,
        String address,
        String term,
        String passedUnit,
        String supervisorName) {
}
