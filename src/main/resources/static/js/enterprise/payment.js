

// Create a Stripe client.
// Note: this merchant has been set up for demo purposes.
var stripe = Stripe('pk_test_6pRNASCoBOKtIshFeQd4XMUh');

// Create an instance of Elements.
var elements = stripe.elements();

// Custom styling can be passed to options when creating an Element.
// (Note that this demo uses a wider set of styles than the guide below.)
var card = elements.create('card', {
    iconStyle: 'solid',
    style: {
        base: {
            iconColor: '#8898AA',
            color: 'white',
            lineHeight: '36px',
            fontWeight: 300,
            fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
            fontSize: '19px',

            '::placeholder': {
                color: '#8898AA',
            },
        },
        invalid: {
            iconColor: '#e85746',
            color: '#e85746',
        }
    },
    classes: {
        focus: 'is-focused',
        empty: 'is-empty',
    },
});
card.mount('#card-element');

var inputs = document.querySelectorAll('input.field');
Array.prototype.forEach.call(inputs, function(input) {
    input.addEventListener('focus', function() {
        input.classList.add('is-focused');
    });
    input.addEventListener('blur', function() {
        input.classList.remove('is-focused');
    });
    input.addEventListener('keyup', function() {
        if (input.value.length === 0) {
            input.classList.add('is-empty');
        } else {
            input.classList.remove('is-empty');
        }
    });
});

function setOutcome(result) {
    var successElement = document.querySelector('.success');
    var errorElement = document.querySelector('.error');
    successElement.classList.remove('visible');
    errorElement.classList.remove('visible');

    if (result.token) {
        // Use the token to create a charge or a customer
        // https://stripe.com/docs/payments/charges-api
        successElement.querySelector('.token').textContent = result.token.id;
        successElement.classList.add('visible');
    } else if (result.error) {
        errorElement.textContent = result.error.message;
        errorElement.classList.add('visible');
    }
}

card.on('change', function(event) {
    setOutcome(event);
});

document.querySelector('form').addEventListener('submit', function(e) {
    e.preventDefault();
    var form = document.querySelector('form');
    var extraDetails = {
        name: form.querySelector('input[name=cardholder-name]').value,
    };
    stripe.createToken(card, extraDetails).then(setOutcome);
});

