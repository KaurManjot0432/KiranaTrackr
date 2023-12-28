package com.example.KiranaTrackr.controllers;

import com.example.KiranaTrackr.dtos.report.ReportResult;
import com.example.KiranaTrackr.dtos.report.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.KiranaTrackr.services.report.ReportServiceFactory;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportServiceFactory reportServiceFactory;

    @Autowired
    public ReportController(ReportServiceFactory reportServiceFactory) {
        this.reportServiceFactory = reportServiceFactory;
    }

    @GetMapping("/{reportType}")
    public ResponseEntity<?> getReport(@PathVariable String reportType,
                                                  @RequestParam(required = false) String date,
                                                  @RequestParam(required = false) Integer year,
                                                  @RequestParam(required = false) Integer month,
                                                  @RequestParam(required = false) String startDate,
                                                  @RequestParam(required = false) String endDate) {

        try {
            ReportType reportTypeEnum = ReportType.valueOf(reportType);
            ReportResult reportResult = reportServiceFactory.getReportGenerator(reportTypeEnum, date, year, month, startDate, endDate);
            return new ResponseEntity<>(reportResult, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid report type", HttpStatus.BAD_REQUEST);
        }  catch (Exception e) {
            return new ResponseEntity<>("Error occurred while processing your request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
