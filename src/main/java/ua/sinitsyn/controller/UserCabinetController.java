package ua.sinitsyn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.sinitsyn.dto.ReportDto;
import ua.sinitsyn.model.Report;
import ua.sinitsyn.model.User;
import ua.sinitsyn.repo.ReportRepository;
import ua.sinitsyn.service.ReportService;

import java.util.List;

@Controller
@RequestMapping("cabinet/user")
public class UserCabinetController {

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getListReport(Authentication auth, Model model){
        User user = (User) auth.getPrincipal();
        List<Report> reports = reportService.findVyUser(user);
        model.addAttribute("reportList", reports);
        model.addAttribute("username",user.getUsername());
        return "userCabinet";
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity addTask(Authentication auth,
                                  @RequestBody ReportDto reportDto) {
        User user = (User) auth.getPrincipal();
        reportService.saveReport(Report.builder().
                value(reportDto.getValue()).
                user(user).
                build());

        return new ResponseEntity(HttpStatus.OK);
    }

}
