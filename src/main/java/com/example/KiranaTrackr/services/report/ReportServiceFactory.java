package com.example.KiranaTrackr.services.report;

import com.example.KiranaTrackr.dtos.report.ReportResult;
import com.example.KiranaTrackr.dtos.report.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceFactory {

    private final ReportGenerator reportGenerator;

    /**
     * Retrieves the appropriate report generator based on the specified report type.
     *
     * @param reportType The type of the report (DAILY, CUSTOM, etc.).
     * @param date       The date for daily reports.
     * @param year       The year for custom reports.
     * @param month      The month for custom reports.
     * @param startDate  The start date for custom reports.
     * @param endDate    The end date for custom reports.
     * @return ReportResult generated based on the specified report type.
     * @throws IllegalArgumentException If an unsupported report type is provided.
     */
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
