package ua.sinitsyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sinitsyn.model.Report;
import ua.sinitsyn.model.User;
import ua.sinitsyn.repo.ReportRepository;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    public List<Report> findByUser(User user){
        return reportRepository.findByUser(user);
    }

}
