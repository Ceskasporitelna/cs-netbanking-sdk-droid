# Orders

This guide walks you through listing all payments, getting payment's detail, deleting payment, listing limits and retrieving current available booking date. You can also create and update domestic payment or recharge credit on prepaid cards.

[OrdersResource](../netbanking/src/main/java/cz/csas/netbanking/orders/OrdersResource.java)

```java

    // Get OrdersResource Instance
    Netbanking.getInstance().getNetbankingClient().getOrdersResource()...

```

## 1. List all payment orders

List all payment orders by calling the `list` method on [PaymentsResource](../netbanking/src/main/java/cz/csas/netbanking/orders/PaymentsResource.java). The method takes object with properties such as pageSize or sortBy as a parameter. See all supported parameters in [PaymentsParameters](../netbanking/src/main/java/cz/csas/netbanking/orders/PaymentsParameters.java). For full response see [PaymentsListResponse](../netbanking/src/main/java/cz/csas/netbanking/orders/PaymentsListResponse.java).

```java

    // List all payments
    Netbanking.getInstance().getNetbankingClient().getOrdersResource()
        .getPaymentsResource()
        .list(PaymentsParameters parameters, CallbackWebApi<PaymentsListResponse> callback);

```

## 2. Get payments detail

Get payments detail by calling the `withId` method on [PaymentsResource](../netbanking/src/main/java/cz/csas/netbanking/orders/PaymentsResource.java) and then calling the get method. See [Payment](../netbanking/src/main/java/cz/csas/netbanking/orders/Payment.java) for full response.

```java

    // Payment detail
    Netbanking.getInstance().getNetbankingClient().getOrdersResource()
        .getPaymentsResource()
        .withId(Object id)
        .get(CallbackWebApi<Payment> callback);

```

## 3. Delete payment

Delete payment by getting the [PaymentResource](../netbanking/src/main/java/cz/csas/netbanking/orders/PaymentResource.java) and then calling the `delete` method.

```java

    // Delete payment
    Netbanking.getInstance().getNetbankingClient().getOrdersResource()
        .getPaymentsResource()
        .withId(Object id)
        .delete(CallbackWebApi<RemovePaymentOrderResponse> callback);

```

## 4. Create domestic payment

Create domestic payment by getting the [PaymentsDomesticResource](../netbanking/src/main/java/cz/csas/netbanking/orders/PaymentsDomesticResource.java) and then calling the `create` method. The create method takes object with properties as a parameter. See [DomesticPaymentCreateRequest](../netbanking/src/main/java/cz/csas/netbanking/orders/DomesticPaymentCreateRequest.java) for all supported parameters and [DomesticPaymentCreateResponse](../netbanking/src/main/java/cz/csas/netbanking/orders/DomesticPaymentCreateResponse.java) for full response.

```java

    // Domestic payment
    Netbanking.getInstance().getNetbankingClient().getOrdersResource()
        .getPaymentsResource()
        .getDomesticResource()
        .create(DomesticPaymentCreateRequest request, CallbackWebApi<DomesticPaymentCreateResponse> callback);

```

## 5. Update domestic payment

Create domestic payment by getting the [PaymentsDomesticResource](../netbanking/src/main/java/cz/csas/netbanking/orders/PaymentsDomesticResource.java) and then calling the `create` method. The create method takes object with properties as a parameter. See [DomesticPaymentCreateRequest](../netbanking/src/main/java/cz/csas/netbanking/orders/DomesticPaymentCreateRequest.java) for all supported parameters and [DomesticPaymentUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/orders/DomesticPaymentUpdateResponse.java) for full response.

```java

    // Update domestic payment
    Netbanking.getInstance().getNetbankingClient().getOrdersResource()
        .getPaymentsResource()
        .getDomesticResource()
        .withId(Object id)
        .update(DomesticPaymentUpdateRequest request, CallbackWebApi<DomesticPaymentUpdateResponse> callback);

```

## 6. Get remaining amounts for payments

Get remaining amounts for payment orders by getting the [PaymentLimitsResource](../netbanking/src/main/java/cz/csas/netbanking/orders/PaymentLimitsResource.java) resource and then calling the `list` method.

```java

    // Payment limits
    Netbanking.getInstance().getNetbankingClient().getOrdersResource()
        .getPaymentsResource()
        .getLimitsResource()
        .list(CallbackWebApi<PaymentLimitsListResponse> callback);

```

## 7. Recharge the credit on prepaid card

Recharge the credit on prepaid card by getting the [PaymentsMobileResource](../netbanking/src/main/java/cz/csas/netbanking/orders/PaymentsMobileResource.java) and calling the `create` method. The create method takes object with properties as a parameter. See [MobilePaymentRequest](../netbanking/src/main/java/cz/csas/netbanking/orders/MobilePaymentRequest.java) for all supported parameters and [MobilePaymentResponse](../netbanking/src/main/java/cz/csas/netbanking/orders/MobilePaymentResponse.java) for full response.

```java

    // Mobile payment
    Netbanking.getInstance().getNetbankingClient().getOrdersResource()
        .getPaymentsResource()
        .getMobileResource()
        .create(MobilePaymentRequest request, CallbackWebApi<MobilePaymentResponse> callback);

```

## 8. Update booking dates

Update current available booking sates by getting the [PaymentBookingDateResource](../netbanking/src/main/java/cz/csas/netbanking/orders/PaymentBookingDateResource.java) and calling `update` method. See [PaymentBookingDateRequest](../netbanking/src/main/java/cz/csas/netbanking/orders/PaymentBookingDateRequest.java) for all supported parameters and [PaymentBookingDateResponse](../netbanking/src/main/java/cz/csas/netbanking/orders/PaymentBookingDateResponse.java) for full response.

```java

    // Booking dates
    Netbanking.getInstance().getNetbankingClient().getOrdersResource()
        .getPaymentsResource()
        .getBookingDateResource()
        .update(PaymentBookingDateRequest request, CallbackWebApi<PaymentBookingDateResponse> callback);

```