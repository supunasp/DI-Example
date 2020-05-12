import it.supunasp.di.consumer.CCPaymentSite;
import it.supunasp.di.consumer.CashPaymentSite;
import it.supunasp.di.consumer.WebSite;
import it.supunasp.di.injector.AutoWiredInjector;
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

    AutoWiredInjector autoWiredInjector;
    private WebSite webSite;
    private PaymentService mockedPaymentService;


    @Before
    public void init() throws InstantiationException, IllegalAccessException {

        autoWiredInjector = Mockito.spy(new AutoWiredInjector());
        mockedPaymentService = Mockito.spy(new CCPaymentServiceImpl());

        /*
            mock CashPaymentServiceImpl to avoid any outside transactions
         */

        Mockito.doReturn(mockedPaymentService).when(autoWiredInjector).getInstanceOfClass(CCPaymentServiceImpl.class);

    }

    private WebSite getWebSite() throws Exception {
        WebSite webSite = new CCPaymentSite();
        autoWiredInjector.injectBeans(webSite);
        return webSite;
    }


    @Test
    public void testSuccessPayment() throws Exception {

        /*
         * when to avoid real business logic
         */
        Mockito.doReturn(true).when(mockedPaymentService).processPayment(Mockito.any(PaymentRequest.class));

        PaymentRequest creditCardPaymentRequest = new PaymentRequest("4104-0000-2222-2211", 100.0);
        webSite = getWebSite();
        boolean success = webSite.takePayment(creditCardPaymentRequest);

        Assert.assertTrue(success);
    }

    @Test
    public void testFailedPayment() throws Exception {

        /*
         * when to avoid real business logic
         */
        Mockito.doReturn(false).when(mockedPaymentService).processPayment(Mockito.any(PaymentRequest.class));


        PaymentRequest creditCardPaymentRequest = new PaymentRequest("4104-0000-2222-2211", 100.0);
        webSite = getWebSite();
        boolean success = webSite.takePayment(creditCardPaymentRequest);

        Assert.assertFalse(success);
    }


    @After
    public void tear() {
        mockedPaymentService = null;
        webSite = null;
        autoWiredInjector = null;
    }

}
