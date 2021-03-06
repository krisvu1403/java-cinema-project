package com.myproject.controller;

import com.myproject.Util.ResponseUtil;
import com.myproject.model.common.ResponseModel;
import com.myproject.model.entity.Cinema;
import com.myproject.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cinema")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private ResponseUtil responseUtil;

    @ResponseBody
    @GetMapping("all")
    public ResponseEntity all() {
        ResponseModel<List<Cinema>> response = cinemaService.findAll();
        return responseUtil.createResponse(HttpStatus.OK, response);
    }
}
