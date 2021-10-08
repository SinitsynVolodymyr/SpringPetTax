package ua.sinitsyn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cabinet/inspector")
public class InspectorCabinetController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getPage(){
        return "InspectorCabinet";
    }

}
