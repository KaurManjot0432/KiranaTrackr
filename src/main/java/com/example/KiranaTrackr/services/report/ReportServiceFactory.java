package com.example.KiranaTrackr.services.report;

import com.example.KiranaTrackr.dtos.Report.ReportResult;
import com.example.KiranaTrackr.dtos.Report.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceFactory {

    private final ReportGenerator reportGenerator;

    @Autowired
    public ReportServiceFactory(ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

    public ReportResult getReportGenerator(ReportType reportType, String date, Integer year, Integer month, String startDate, String endDate) {
        return switch (reportType) {
            case DAILY -> reportGenerator.generateDailyReport(date);
            case CUSTOM -> reportGenerator.generateCustomReport(startDate, endDate);
            default -> throw new IllegalArgumentException("Unsupported report type: " + reportType);
        };
    }
}
