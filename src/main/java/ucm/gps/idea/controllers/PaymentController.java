package ucm.gps.idea.controllers;


import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucm.gps.idea.entities.PaymentIntentDTO;
import ucm.gps.idea.models.PaymentModel;
import ucm.gps.idea.services.PaymentService;

@RestController
@RequestMapping("/api/Stripe")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    private static final Logger logger = LoggerFactory.getLogger(CreatorController.class);

    @PostMapping("/paymentintent")
    public ResponseEntity<String> payment(@RequestBody PaymentModel paymentModel){
        try {
            logger.info("Payment controller. Intend of payment");
            PaymentIntentDTO paymentIntentDTO =
                    new PaymentIntentDTO(paymentModel.getDescription(), paymentModel.getAmount(), paymentModel.getCurrency(),
                            paymentModel.getOwnerName(), paymentModel.getCardNumber(), paymentModel.getExpirationDate(),
                            paymentModel.getValidateNumber());

            PaymentIntent paymentIntent = paymentService.paymentIntent(paymentIntentDTO);
            String paymentStr = paymentIntent.toJson();
            logger.info("Payment controller. Intend of payment success");
            return new ResponseEntity<String>(paymentStr, HttpStatus.OK);

        } catch (StripeException e) {
            e.printStackTrace();
            logger.info("Payment controller. Intend of payment denied");
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<String> confirm(@PathVariable String id){
        try {
            PaymentIntent paymentIntent = paymentService.confirm(id);
            String paymentStr = paymentIntent.toJson();
            return new ResponseEntity<String>(paymentStr, HttpStatus.OK);

        } catch (StripeException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable String id){
        try {
            PaymentIntent paymentIntent = paymentService.cancel(id);
            String paymentStr = paymentIntent.toJson();
            return new ResponseEntity<String>(paymentStr, HttpStatus.OK);

        } catch (StripeException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }
}
