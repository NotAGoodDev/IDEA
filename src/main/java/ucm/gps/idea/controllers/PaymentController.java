package ucm.gps.idea.controllers;


import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.entities.PaymentintentDTO;
import ucm.gps.idea.services.PaymentService;

@RestController
@RequestMapping("/api/Stripe")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/paymentintent")
    public ResponseEntity<String> payment(@RequestBody PaymentintentDTO paymentintentDTO){
        try {
            PaymentIntent paymentIntent = paymentService.paymentIntent(paymentintentDTO);
            String paymentStr = paymentIntent.toJson();
            return new ResponseEntity<String>(paymentStr, HttpStatus.OK);

        } catch (StripeException e) {
            e.printStackTrace();
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
