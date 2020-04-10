package ucm.gps.idea.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.PaymentintentDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${stripe.key.private}")
    String secretKey;

    public PaymentIntent paymentIntent(PaymentintentDTO paymentintentDTO) throws StripeException {
        Stripe.apiKey = secretKey;
        Map<String, Object> params = new HashMap<>();

        params.put("amount", paymentintentDTO.getAmount());
        params.put("currency", paymentintentDTO.getCurrency());
        params.put("description", paymentintentDTO.getDescription());

        List<String> paymentMethodTypes = new ArrayList<String>();

        paymentMethodTypes.add("card");

        params.put("payment_method_types", paymentMethodTypes);

        return PaymentIntent.create(params);
    }

    public PaymentIntent confirm(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);

        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");

        paymentIntent.confirm(params);
        return paymentIntent;
    }

    public PaymentIntent cancel(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);

        paymentIntent.cancel();

        return paymentIntent;
    }
}
