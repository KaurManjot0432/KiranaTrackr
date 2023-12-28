package com.example.KiranaTrackr.services.report;

import com.example.KiranaTrackr.dtos.report.ReportResult;

public interface ReportService {
    public ReportResult generateDailyReport(String storeId, String date);
    public ReportResult generateCustomReport(String storeId, String startDate, String endDate);
}