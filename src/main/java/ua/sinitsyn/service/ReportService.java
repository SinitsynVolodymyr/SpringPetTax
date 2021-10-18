package ua.sinitsyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sinitsyn.model.Report;
import ua.sinitsyn.model.User;
import ua.sinitsyn.repo.ReportRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    public Optional<Report> findById(Long id){
       return reportRepository.findById(id);
    }

    public List<Report> findByUser(User user){
        return reportRepository.findByUser(user);
    }

    public List<Report> findByAll(){
        return reportRepository.findAll();
    }

}
