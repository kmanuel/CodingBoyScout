package com.klowdscy.controller;

import com.klowdscy.dao.ScoutDao;
import com.klowdscy.domain.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by manuel on 26.11.17.
 */
@Controller
public class ScoutController {

    private final ScoutDao scoutDao;

    @Autowired
    public ScoutController(ScoutDao scoutDao) {
        this.scoutDao = scoutDao;
    }

    @GetMapping("/")
    public String listScouts(Model model) {
        List<Scout> scouts = scoutDao.findAll();
        model.addAttribute("scouts", scouts);
        return "list";
    }

}
