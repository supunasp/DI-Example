import it.supunasp.di.consumer.CashPaymentSite;
import it.supunasp.di.consumer.WebSite;
import it.supunasp.di.injector.AutoWiredInjector;
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

    AutoWiredInjector autoWiredInjector;
    private WebSite webSite;
    private PaymentService mockedPaymentService;


    @Before
    public void init() throws InstantiationException, IllegalAccessException {

        autoWiredInjector = Mockito.spy(new AutoWiredInjector());
        mockedPaymentService = Mockito.spy(new CashPaymentServiceImpl());

        /*
            mock CashPaymentServiceImpl to avoid any outside transactions
         */
        Mockito.doReturn(mockedPaymentService).when(autoWiredInjector).getInstanceOfClass(CashPaymentServiceImpl.class);

    }

    private WebSite getWebSite() throws Exception {
        WebSite webSite = new CashPaymentSite();
        autoWiredInjector.injectBeans(webSite);
        return webSite;
    }

    @Test
    public void testSuccessPayment() throws Exception {

        /*
         * when to avoid real business logic
         */
        Mockito.doReturn(true).when(mockedPaymentService).processPayment(Mockito.any(PaymentRequest.class));
        webSite = getWebSite();

        PaymentRequest cashPaymentRequest = new PaymentRequest("110100000", 100.0);
        boolean success = webSite.takePayment(cashPaymentRequest);

        Assert.assertTrue(success);
    }

    @Test
    public void testFailedPayment() throws Exception {

        /*
         * when to avoid real business logic
         */
        Mockito.doReturn(false).when(mockedPaymentService).processPayment(Mockito.any(PaymentRequest.class));


        PaymentRequest cashPaymentRequest = new PaymentRequest("110100000", 100.0);
        webSite = getWebSite();
        boolean success = webSite.takePayment(cashPaymentRequest);

        Assert.assertFalse(success);
    }

    @After
    public void tear() {
        autoWiredInjector = null;
        webSite = null;
        mockedPaymentService = null;
    }

}
