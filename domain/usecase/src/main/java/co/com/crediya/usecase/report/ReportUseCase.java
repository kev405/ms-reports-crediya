package co.com.crediya.usecase.report;

import co.com.crediya.model.report.Report;
import co.com.crediya.model.report.ReportMessage;
import co.com.crediya.model.report.gateways.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Log
public class ReportUseCase {

    private final ReportRepository reportRepository;

    private static final String REPORT_ID = "SUMMARY_REPORT";

    public Mono<Void> processReport(ReportMessage message) {
        log.info("Iniciando procesamiento de reporte para un monto de: " +
                message.amount());

        return reportRepository.getReport(REPORT_ID)
                .switchIfEmpty(Mono.fromCallable(() -> {
                    log.warning(
                            "No se encontró un reporte existente. Creando uno nuevo.");
                    return new Report(REPORT_ID, 0, BigDecimal.ZERO);
                }))

                .map(currentReport -> {
                    log.info("Reporte actual - Cantidad: " + currentReport.quantityApproved() + " Monto: " +
                            currentReport.totalAmountApproved());

                    Integer newQuantity =
                            currentReport.quantityApproved() + 1;
                    BigDecimal newAmount =
                            currentReport.totalAmountApproved()
                                    .add(message.amount());

                    log.info(
                            "Nuevos valores calculados - Cantidad: " + newQuantity + " Monto: " +
                            newAmount);

                    return new Report(
                            REPORT_ID,
                            newQuantity,
                            newAmount
                    );
                })
                .flatMap(reportRepository::save)
                .then();
    }
}
