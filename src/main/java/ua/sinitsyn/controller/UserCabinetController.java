package ua.sinitsyn.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.sinitsyn.dto.ReportDto;
import ua.sinitsyn.dto.ReportInputDto;
import ua.sinitsyn.model.Report;
import ua.sinitsyn.model.ReportStatus;
import ua.sinitsyn.model.User;
import ua.sinitsyn.service.ReportService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("cabinet/user")
public class UserCabinetController {
    static Logger log = Logger.getLogger(UserCabinetController.class.getName());

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getListReport(Authentication auth, Model model){
        User user = (User) auth.getPrincipal();
        List<Report> reports = reportService.findByUser(user);
        List<ReportDto> reportDtoList = reports.stream().map(report -> ReportDto.builder().
                id(report.getId()).
                value(report.getValue()).
                reportStatus(report.getReportStatus().name()).
                build()
        ).collect(Collectors.toList());
        model.addAttribute("reportList", reportDtoList);
        model.addAttribute("username",user.getUsername());
        return "userCabinet";
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity addTask(Authentication auth,
                                  @RequestBody ReportInputDto reportDto) {
        User user = (User) auth.getPrincipal();
        log.info("User ("+user.getEmail()+") is creating new report: "+reportDto.getValue());
        reportService.saveReport(Report.builder().
                value(reportDto.getValue()).
                user(user).
                reportStatus(ReportStatus.SENT).
                build());

        return new ResponseEntity(HttpStatus.OK);
    }

}
