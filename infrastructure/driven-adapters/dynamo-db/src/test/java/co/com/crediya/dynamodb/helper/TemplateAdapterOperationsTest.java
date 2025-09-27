//package co.com.crediya.dynamodb.helper;
//
//import co.com.crediya.dynamodb.DynamoDBTemplateAdapter;
//import co.com.crediya.dynamodb.ReportEntity;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.reactivecommons.utils.ObjectMapper;
//import reactor.test.StepVerifier;
//import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
//import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
//import software.amazon.awssdk.enhanced.dynamodb.Key;
//import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
//
//import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
//import java.util.concurrent.CompletableFuture;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//class TemplateAdapterOperationsTest {
//
//    @Mock
//    private DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient;
//
//    @Mock
//    private ObjectMapper mapper;
//
//    @Mock
//    private DynamoDbAsyncTable<ReportEntity> customerTable;
//
//    private ReportEntity reportEntity;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        when(dynamoDbEnhancedAsyncClient.table("table_name", TableSchema.fromBean(
//                ReportEntity.class)))
//                .thenReturn(customerTable);
//
//        reportEntity = new ReportEntity();
//        reportEntity.setId("id");
//        reportEntity.setAtr1("atr1");
//    }
//
//    @Test
//    void modelEntityPropertiesMustNotBeNull() {
//        ReportEntity reportEntityUnderTest = new ReportEntity("id", "atr1");
//
//        assertNotNull(reportEntityUnderTest.getId());
//        assertNotNull(reportEntityUnderTest.getAtr1());
//    }
//
//    @Test
//    void testSave() {
//        when(customerTable.putItem(reportEntity)).thenReturn(CompletableFuture.runAsync(()->{}));
//        when(mapper.map(reportEntity, ReportEntity.class)).thenReturn(
//                reportEntity);
//
//        DynamoDBTemplateAdapter dynamoDBTemplateAdapter =
//                new DynamoDBTemplateAdapter(dynamoDbEnhancedAsyncClient, mapper);
//
//        StepVerifier.create(dynamoDBTemplateAdapter.save(reportEntity))
//                .expectNextCount(1)
//                .verifyComplete();
//    }
//
//    @Test
//    void testGetById() {
//        String id = "id";
//
//        when(customerTable.getItem(
//                Key.builder().partitionValue(AttributeValue.builder().s(id).build()).build()))
//                .thenReturn(CompletableFuture.completedFuture(reportEntity));
//        when(mapper.map(reportEntity, Object.class)).thenReturn("value");
//
//        DynamoDBTemplateAdapter dynamoDBTemplateAdapter =
//                new DynamoDBTemplateAdapter(dynamoDbEnhancedAsyncClient, mapper);
//
//        StepVerifier.create(dynamoDBTemplateAdapter.getById("id"))
//                .expectNext("value")
//                .verifyComplete();
//    }
//
//    @Test
//    void testDelete() {
//        when(mapper.map(reportEntity, ReportEntity.class)).thenReturn(
//                reportEntity);
//        when(mapper.map(reportEntity, Object.class)).thenReturn("value");
//
//        when(customerTable.deleteItem(reportEntity))
//                .thenReturn(CompletableFuture.completedFuture(reportEntity));
//
//        DynamoDBTemplateAdapter dynamoDBTemplateAdapter =
//                new DynamoDBTemplateAdapter(dynamoDbEnhancedAsyncClient, mapper);
//
//        StepVerifier.create(dynamoDBTemplateAdapter.delete(reportEntity))
//                .expectNext("value")
//                .verifyComplete();
//    }
//}