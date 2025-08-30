package gr.aueb.cf.realestateapp.dto;

public record ResponseMessageDTO(
        String code,
        String description
) {
    public ResponseMessageDTO(String code) {
        this(code, "");
    }
}
