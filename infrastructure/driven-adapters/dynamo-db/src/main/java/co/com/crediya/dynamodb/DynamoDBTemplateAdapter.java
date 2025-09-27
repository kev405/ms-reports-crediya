package co.com.crediya.dynamodb;

import co.com.crediya.dynamodb.helper.TemplateAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import co.com.crediya.model.report.Report;
import co.com.crediya.model.report.gateways.ReportRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.List;


@Repository
@Slf4j
public class DynamoDBTemplateAdapter extends TemplateAdapterOperations<Report, String, ReportEntity> implements
        ReportRepository {


    public DynamoDBTemplateAdapter(DynamoDbEnhancedAsyncClient connectionFactory, ObjectMapper mapper) {
        super(connectionFactory, mapper, d -> new Report(d.getReportAbout(), d.getQuantityApproved(), d.getTotalAmountApproved()), "report");
    }

    ReportEntity toEntityR(Report r) {
        ReportEntity e = new ReportEntity();
        e.setReportAbout(r.reportAbout());                 // <- CLAVE OBLIGATORIA
        e.setQuantityApproved(r.quantityApproved());
        e.setTotalAmountApproved(r.totalAmountApproved());
        return e;
    }

    @Override
    public Mono<Report> getReport(String id) {
        return super.getById(id);
    }

    @Override
    public Mono<Report> save(Report report) {
        log.info(
                "Guardando reporte con ID: {}, Cantidad Aprobada: {}, Monto Total Aprobado: {}",
                report.reportAbout(), report.quantityApproved(),
                report.totalAmountApproved());
        ReportEntity e = toEntityR(report);
        if (e.getReportAbout() == null || e.getReportAbout().isBlank()) {
            return Mono.error(new IllegalArgumentException("reportAbout no puede ser null/blank"));
        }
        return super.save(report);
    }

    @Override
    protected ReportEntity toEntity(Report model) {
        ReportEntity entity = new ReportEntity();
        entity.setReportAbout(model.reportAbout());
        entity.setQuantityApproved(model.quantityApproved());
        entity.setTotalAmountApproved(model.totalAmountApproved());
        return entity;
    }
}
