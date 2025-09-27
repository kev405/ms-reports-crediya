package co.com.crediya.model.report;

import java.math.BigDecimal;

public record Report (String reportAbout, Integer quantityApproved, BigDecimal totalAmountApproved) {
}
