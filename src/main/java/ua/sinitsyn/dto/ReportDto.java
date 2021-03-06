package ua.sinitsyn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.sinitsyn.model.ReportStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    Long id;
    String value;
    String reportStatus;
}
