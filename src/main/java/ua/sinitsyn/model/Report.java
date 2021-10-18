package ua.sinitsyn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String value;
    @ManyToOne
    private User user;
    @Enumerated(value = EnumType.STRING)
    private ReportStatus reportStatus = ReportStatus.SENT;

    public Report(String value) {
        this.value = value;
    }
}
