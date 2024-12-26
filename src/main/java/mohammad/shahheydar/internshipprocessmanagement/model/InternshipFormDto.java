package mohammad.shahheydar.internshipprocessmanagement.model;

public record InternshipFormDto(
        StudentDto student,
        String phone,
        String state,
        String city,
        String supervisorName,
        String supervisorPhone,
        String supervisorWebUrl,
        String address,
        String placeName,
        String website,
        String term
        ) {
}
