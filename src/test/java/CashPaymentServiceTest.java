import it.supunasp.di.consumer.WebSite;
import it.supunasp.di.injector.CashPaymentServiceInjector;
import it.supunasp.di.injector.PaymentServiceInjector;
import it.supunasp.di.model.PaymentRequest;
import it.supunasp.di.service.CashPaymentServiceImpl;
import it.supunasp.di.service.PaymentService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CashPaymentServiceTest {

    private PaymentServiceInjector injector;
    private PaymentService mockedPaymentService;


    @Before
    public void init() {

        injector = Mockito.spy(new CashPaymentServiceInjector());
        mockedPaymentService = Mockito.spy(new CashPaymentServiceImpl());

        /*
            mock CashPaymentServiceImpl to avoid any outside transactions
         */
        Mockito.doReturn(mockedPaymentService).when(injector).getPaymentServiceInstance();

    }


    @Test
    public void testSuccessPayment() {

        /*
         * when to avoid real business logic
         */
        Mockito.doReturn(true).when(mockedPaymentService).processPayment(Mockito.any(PaymentRequest.class));

        PaymentRequest cashPaymentRequest = new PaymentRequest("110100000", 100.0);
        WebSite app = injector.getWebSite();
        boolean success = app.takePayment(cashPaymentRequest);

        Assert.assertTrue(success);
    }

    @Test
    public void testFailedPayment() {

        /*
         * when to avoid real business logic
         */
        Mockito.doReturn(false).when(mockedPaymentService).processPayment(Mockito.any(PaymentRequest.class));


        PaymentRequest cashPaymentRequest = new PaymentRequest("110100000", 100.0);
        WebSite app = injector.getWebSite();
        boolean success = app.takePayment(cashPaymentRequest);

        Assert.assertFalse(success);
    }

    @After
    public void tear() {
        injector = null;
    }

}
