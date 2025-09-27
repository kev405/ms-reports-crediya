package co.com.crediya.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;
import java.math.BigDecimal;

@DynamoDbBean
public class ReportEntity {

    private String reportAbout;
    private Integer quantityApproved;
    private BigDecimal totalAmountApproved;

    public ReportEntity() {
    }

    public ReportEntity(String reportAbout,Integer quantityApproved, BigDecimal totalAmountApproved) {
        this.reportAbout = reportAbout;
        this.quantityApproved = quantityApproved;
        this.totalAmountApproved = totalAmountApproved;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("reportAbout")
    public String getReportAbout() {
        return reportAbout;
    }

    public void setReportAbout(String reportAbout) {
        this.reportAbout = reportAbout;
    }

    @DynamoDbAttribute("quantityApproved")
    public Integer getQuantityApproved() {
        return quantityApproved;
    }

    public void setQuantityApproved(Integer quantityApproved) {
        this.quantityApproved = quantityApproved;
    }

    @DynamoDbAttribute("totalAmountApproved")
    public BigDecimal getTotalAmountApproved() {
        return totalAmountApproved;
    }

    public void setTotalAmountApproved(BigDecimal totalAmountApproved) {
        this.totalAmountApproved = totalAmountApproved;
    }

    @Override
    public String toString() {
        return "ReportEntity{" +
                "quantityApproved=" + quantityApproved +
                ", reportAbout='" + reportAbout + '\'' +
                ", totalAmountApproved=" + totalAmountApproved +
                '}';
    }
}
