import it.supunasp.di.consumer.WebSite;
import it.supunasp.di.injector.CCPaymentServiceInjector;
import it.supunasp.di.injector.PaymentServiceInjector;
import it.supunasp.di.model.PaymentRequest;
import it.supunasp.di.service.CCPaymentServiceImpl;
import it.supunasp.di.service.PaymentService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CCPaymentServiceTest {

    private PaymentServiceInjector injector;
    private PaymentService mockedPaymentService;


    @Before
    public void init() {

        injector = Mockito.spy(new CCPaymentServiceInjector());
        mockedPaymentService = Mockito.spy(new CCPaymentServiceImpl());

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

        PaymentRequest creditCardPaymentRequest = new PaymentRequest("4104-0000-2222-2211", 100.0);
        WebSite app = injector.getWebSite();
        boolean success = app.takePayment(creditCardPaymentRequest);

        Assert.assertTrue(success);
    }

    @Test
    public void testFailedPayment() {

        /*
         * when to avoid real business logic
         */
        Mockito.doReturn(false).when(mockedPaymentService).processPayment(Mockito.any(PaymentRequest.class));


        PaymentRequest creditCardPaymentRequest = new PaymentRequest("4104-0000-2222-2211", 100.0);
        WebSite app = injector.getWebSite();
        boolean success = app.takePayment(creditCardPaymentRequest);

        Assert.assertFalse(success);
    }


    @After
    public void tear() {
        injector = null;
    }

}
