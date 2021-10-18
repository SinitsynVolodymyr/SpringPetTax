package ua.sinitsyn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.sinitsyn.dto.ReportDto;
import ua.sinitsyn.dto.ReportDtoForInspector;
import ua.sinitsyn.dto.ReportDtoInspectorStatus;
import ua.sinitsyn.dto.ReportInputDto;
import ua.sinitsyn.model.Report;
import ua.sinitsyn.model.ReportStatus;
import ua.sinitsyn.model.User;
import ua.sinitsyn.service.ReportService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("cabinet/inspector")
public class InspectorCabinetController {

    @Autowired
    ReportService reportService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getListReport(Authentication auth, Model model){
        User user = (User) auth.getPrincipal();
        List<Report> reports = reportService.findByAll();
        List<ReportDtoForInspector> reportDtoList = reports.stream().map(report -> ReportDtoForInspector.builder().
                id(report.getId()).
                value(report.getValue()).
                reportStatus(report.getReportStatus().name()).
                username(report.getUser().getUsername()).
                build()
        ).collect(Collectors.toList());
        model.addAttribute("reportList", reportDtoList);
        model.addAttribute("username",user.getUsername());
        return "inspectorCabinet";
    }



    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity addTask(@RequestBody ReportDtoInspectorStatus reportDto) {
        Report report = reportService.findById(reportDto.getId()).get();
        report.setReportStatus(ReportStatus.valueOf(reportDto.getReportStatus()));
        reportService.saveReport(report);

        return new ResponseEntity(HttpStatus.OK);
    }


}
