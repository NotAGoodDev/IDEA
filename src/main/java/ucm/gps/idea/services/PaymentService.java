package ucm.gps.idea.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.PaymentIntentDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${stripe.key.private}")
    String secretKey;

    public PaymentIntent paymentIntent(PaymentIntentDTO paymentintentDTO) throws StripeException {
        Stripe.apiKey = secretKey;
        Map<String, Object> params = new HashMap<>();

        params.put("amount", paymentintentDTO.getAmount());
        params.put("currency", paymentintentDTO.getCurrency());
        params.put("description", paymentintentDTO.getDescription());
        //params.put("ownerName", paymentintentDTO.getOwnerName());
        //params.put("cardNumber", paymentintentDTO.getCardNumber());
        //params.put("expirationDate", paymentintentDTO.getExpirationDate());
        //params.put("validateNumber", paymentintentDTO.getValidateNumber());

        List<String> paymentMethodTypes = new ArrayList<String>();

        paymentMethodTypes.add("card");

        //Se queda sin camelCase por cosas del stripe
        params.put("payment_method_types", paymentMethodTypes);

        return PaymentIntent.create(params);
    }

    public PaymentIntent confirm(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);

        //Se queda sin camelCase por cosas del stripe
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
