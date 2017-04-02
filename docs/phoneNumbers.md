# Phone Numbers

This guide walks you through retrieving current user's phone numbers.

[PhoneNumbersResource](../netbanking/src/main/java/cz/csas/netbanking/phoneNumbers/PhoneNumbersResource.java)

```java

    // Get PhoneNumbersResource Instance
    Netbanking.getInstance().getNetbankingClient().getPhoneNumbersResource()...

```

## 1. Phone book numbers

You can list all of current users phone numbers by calling the `list` method on [PhoneNumbersResource](../netbanking/src/main/java/cz/csas/netbanking/phoneNumbers/PhoneNumbersResource.java). 

```java

    // List all phone numbers
    Netbanking.getInstance().getNetbankingClient().getPhoneNumbersResource()
        .list(CallbackWebApi<PhoneNumbersListResponse> callback);

```

## 2. Phone book new entry

Create phone number by getting the [PhoneNumbersResource](../netbanking/src/main/java/cz/csas/netbanking/phoneNumbers/PhoneNumbersResource.java) and then calling the `create` method. The create method takes object with properties as a parameter. See [PhoneNumberCreateRequest](../netbanking/src/main/java/cz/csas/netbanking/phoneNumbers/PhoneNumberCreateRequest.java) for all supported parameters and [PhoneNumber](../netbanking/src/main/java/cz/csas/netbanking/phoneNumbers/PhoneNumber.java) for full response.

```java

    // Create phone number
    Netbanking.getInstance().getNetbankingClient().getPhoneNumbersResource()
            .create(PhoneNumberCreateRequest request, CallbackWebApi<PhoneNumber> callback);

```

## 3. Phone book entry update

Update phone number by getting the [PhoneNumberResource](../netbanking/src/main/java/cz/csas/netbanking/phoneNumbers/PhoneNumbersResource.java) and then calling the `update` method on it. The method takes object with properties as a parameter. Currently only alias can be changed. For all supported parameters see [PhoneNumberUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/phoneNumbers/PhoneNumberUpdateRequest.java) and [PhoneNumber](../netbanking/src/main/java/cz/csas/netbanking/phoneNumbers/PhoneNumber.java) for full response.

```java

    // Update phone number
    Netbanking.getInstance().getNetbankingClient().getPhoneNumbersResource()
        .withId(Object id)
        .update(PhoneNumberUpdateRequest request, CallbackWebApi<PhoneNumber> callback);

```

## 4. Phone book entry delete



Delete phone number by getting the [PhoneNumbersResource](../netbanking/src/main/java/cz/csas/netbanking/phoneNumbers/PhoneNumbersResource.java) and then calling the `delete` method.

```java

    // Delete phone number 
    Netbanking.getInstance().getNetbankingClient().getPhoneNumbersResource()
            .withId(Object id)
            .delete(CallbackWebApi<WebApiEmptyResponse> callback);

```
