package ua.sinitsyn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDtoForInspector {
    Long id;
    String value;
    String reportStatus;
    String username;
}
