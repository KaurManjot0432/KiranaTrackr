package com.example.KiranaTrackr.services.report;

import com.example.KiranaTrackr.dtos.Report.ReportResult;

import java.time.LocalDate;

public interface ReportService {
    public ReportResult generateDailyReport(String date);
    public ReportResult generateCustomReport(String startDate, String endDate);
}