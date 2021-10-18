package ua.sinitsyn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sinitsyn.model.Report;
import ua.sinitsyn.model.User;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    public List<Report> findByUser(User user);
}
