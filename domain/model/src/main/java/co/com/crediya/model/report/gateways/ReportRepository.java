package co.com.crediya.model.report.gateways;

import co.com.crediya.model.report.Report;
import reactor.core.publisher.Mono;

public interface ReportRepository {
    Mono<Report> save(Report report);
    Mono<Report> getReport(String id);
}
