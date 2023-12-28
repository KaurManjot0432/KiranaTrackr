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

    /**
     * Retrieves a report based on the specified type and optional parameters.
     *
     * @param reportType The type of the report (e.g., DAILY, MONTHLY).
     * @param date       The specific date for the report (optional).
     * @param year       The year for the report (optional).
     * @param month      The month for the report (optional).
     * @param startDate  The start date for the report range (optional).
     * @param endDate    The end date for the report range (optional).
     * @return ResponseEntity containing the generated report and HTTP status.
     */

    @GetMapping("/{storeId}/{reportType}")
    public ResponseEntity<?> getReport(@PathVariable String storeId,
                                       @PathVariable String reportType,
                                                  @RequestParam(required = false) String date,
                                                  @RequestParam(required = false) Integer year,
                                                  @RequestParam(required = false) Integer month,
                                                  @RequestParam(required = false) String startDate,
                                                  @RequestParam(required = false) String endDate) {

        try {
            ReportType reportTypeEnum = ReportType.valueOf(reportType);
            ReportResult reportResult = reportServiceFactory.getReportGenerator(storeId, reportTypeEnum, date, year, month, startDate, endDate);
            return new ResponseEntity<>(reportResult, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid report type", HttpStatus.BAD_REQUEST);
        }  catch (Exception e) {
            return new ResponseEntity<>("Error occurred while processing your request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
