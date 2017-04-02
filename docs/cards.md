# Cards

This guide walks you through retrieving current user's cards and information like limits, transactions, transfers etc. You can also issue card's actions or updating or adding and marking a transaction.

[CardsResource](../netbanking/src/main/java/cz/csas/netbanking/cards/CardsResource.java)

```java

    // Get CardsResource Instance
    Netbanking.getInstance().getNetbankingClient().getCardsResource()...

```

## 1. List all of current users cards

You can list all of current users cards by calling the `list` method on [CardsResource](../netbanking/src/main/java/cz/csas/netbanking/cards/CardsResource.java). The method takes object with properties such as pageSize or sortBy as a parameter. See all supported parameters in [CardsParameters](../netbanking/src/main/java/cz/csas/netbanking/cards/CardsParameters.java). For full response see [CardsListResponse](../netbanking/src/main/java/cz/csas/netbanking/cards/CardsListResponse.java).

```java

    // List all cards
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .list(CardsParameters parameters, CallbackWebApi<CardsListResponse> callback);

```

## 2. Get cards detail

You can get detail of the individual card by calling the `withId` method on [CardsResource](../netbanking/src/main/java/cz/csas/netbanking/cards/CardsResource.java) with id as a parameter and then calling the get method. For full response see [Card](../netbanking/src/main/java/cz/csas/netbanking/cards/Card.java).

```java

    // Card detail
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .withId(Object id)
        .get(CallbackWebApi<Card> callback);

```

## 3. Update cards settings

Update cards settings by getting the [CardsResource](../netbanking/src/main/java/cz/csas/netbanking/cards/CardsResource.java) and then calling the `update` method on it. The method takes object with properties as a parameter. Currently only alias can be changed. For all supported parameters see [ChangeCardSettingsRequest](../netbanking/src/main/java/cz/csas/netbanking/cards/ChangeCardSettingsRequest.java) and [ChangeCardSettingsResponse](../netbanking/src/main/java/cz/csas/netbanking/cards/ChangeCardSettingsResponse.java) for full response.

```java

    // Update card
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .withId(Object id)
        .update(ChangeCardSettingsRequest request, CallbackWebApi<ChangeCardSettingsResponse> callback);

```

## 4. Get cards delivery information

Get cards delivery by getting the [CardDeliveryResource](../netbanking/src/main/java/cz/csas/netbanking/cards/CardDeliveryResource.java) and then calling the `get` method. For full response see [CardDelivery](../netbanking/src/main/java/cz/csas/netbanking/cards/CardDelivery.java).

```java

    // Card delivery info
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .withId(Object id)
        .getDeliveryResource()
        .get(CallbackWebApi<CardDelivery> callback);

```

## 5. Add or change note and mark transaction

Add or change note and mark transaction by getting the [TransactionsResource](../netbanking/src/main/java/cz/csas/netbanking/TransactionsResource.java) and calling the `update` method on it. The method takes object with properties as a parameter. See all supported parameters in [TransactionUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/TransactionUpdateRequest.java) and [TransactionUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/TransactionUpdateResponse.java) for full response.


```java

    // Card transaction update
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .withId(Object id)
        .getTransactionsResource()
        .withId(Object id)
        .update(TransactionUpdateRequest request, CallbackWebApi<TransactionUpdateResponse> callback);

```

## 6. Export transaction history

Extract transaction history into signed PDF by getting the [TransactionsResource](../netbanking/src/main/java/cz/csas/netbanking/TransactionsResource.java) and calling the `export` method on it. The method takes object with properties as a parameter. See all supported parameters in [ExportTransactionsParameters](../netbanking/src/main/java/cz/csas/netbanking/ExportTransactionsParameters.java).

```java

    // Card transaction export
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .withId(Object id)
        .getTransactionsResource()
        .getExportResource()
        .export(ExportTransactionsParameters parameters, CallbackWebApi<WebApiStream> callback);

```

## 7. Issue cards action

Issue cards action by getting the [CardActionResource](../netbanking/src/main/java/cz/csas/netbanking/cards/CardActionResource.java) and calling the `update` method on it. Currently supported actions are reissue pin, lock card, unlock card, activate card, set automatic card replacement on, set automatic card replacement off and replacement card request. Possibility to issue action is controlled by flags and features on particular card:

- reissue pin - reissuePin feature
- lock card - onlineLocking feature
- unlock card - onlineUnlocking feature
- activate card - activationAllowed flag
- set automatic card replacement on - if automaticReplacementOn flag is not present
- set automatic card replacement off - if automaticReplacementOn flag is present
- replace card - replacementCard feature

For supported parameters see [CardActionRequest](../netbanking/src/main/java/cz/csas/netbanking/cards/CardActionRequest.java) and [CardActionResponse](../netbanking/src/main/java/cz/csas/netbanking/cards/CardActionResponse.java) for full response.

```java

    // Issue cards action update
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .withId(Object id)
        .getActionsResource()
        .update(CardActionRequest request, CallbackWebApi<CardActionResponse> callback);

```

## 8. Get cards limits

Get cards limits by getting the [CardLimitsResource](../netbanking/src/main/java/cz/csas/netbanking/cards/CardLimitsResource.java) and then calling the `list` method. For full response see [CardLimitsListResponse](../netbanking/src/main/java/cz/csas/netbanking/cards/CardLimitsListResponse.java).

```java

    // Card limits
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .withId(Object id)
        .getLimitsResource()
        .list(CallbackWebApi<CardLimitsListResponse> callback);

```

## 9. Update cards limits

Update cards limits by getting the [CardLimitsResource](../netbanking/src/main/java/cz/csas/netbanking/cards/CardLimitsResource.java) and then calling the `update` method. The method takes object with properties as a parameter. See all supported parameters in [ChangeCardLimitsRequest](../netbanking/src/main/java/cz/csas/netbanking/cards/ChangeCardLimitsRequest.java) and [ChangeCardLimitsResponse](../netbanking/src/main/java/cz/csas/netbanking/cards/ChangeCardLimitsResponse.java) for full response.

```java

    // Card limits update
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .withId(Object id)
        .getLimitsResource()
        .update(ChangeCardLimitsRequest request, CallbackWebApi<ChangeCardLimitsResponse> callback);

```

## 10. Get cards 3D Secure status

Get cards 3D Secure status by getting the [CardSecure3DResource](../netbanking/src/main/java/cz/csas/netbanking/cards/CardSecure3DResource.java) and then calling the `get` method. See [SecureSettings](../netbanking/src/main/java/cz/csas/netbanking/cards/SecureSettings.java) for full response.

```java

    // Card 3D secure status 
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .withId(Object id)
        .getSecure3DResource()
        .get(CallbackWebApi<SecureSettings> callback);

```

## 11. Pay up credit card debt

Pay up credit card debt by getting the [CardTransferResource](../netbanking/src/main/java/cz/csas/netbanking/cards/CardTransferResource.java) and then calling the `update` method. The method takes object with properties as a parameter. See [CardTransferRequest](../netbanking/src/main/java/cz/csas/netbanking/cards/CardTransferRequest.java) for supported parameters and [TransferResponse](../netbanking/src/main/java/cz/csas/netbanking/TransferResponse.java) for full response.

```java

    // Card debt payment
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .withId(Object id)
        .getTransferResource()
        .update(CardTransferRequest request, CallbackWebApi<TransferResponse> callback);

```

## 12. Get statements of a cards account

Get statements of a cards account by getting the [CardAccountsResource](../netbanking/src/main/java/cz/csas/netbanking/cards/CardAccountsResource.java) then calling the `withId` method and passing it id of the account after that getting the [StatementsResource](../netbanking/src/main/java/cz/csas/netbanking/StatementsResource.java) and finally calling the `list` method. The method takes object with properties such as pageSize or sortBy as a parameter. See [StatementsListResponse](../netbanking/src/main/java/cz/csas/netbanking/StatementsListResponse.java) for full response.

```java

    // List card statements
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .withId(Object id)
        .getAccountsResource()
        .withId(Object id)
        .getStatements()
        .list(StatementsParameters parameters, CallbackWebApi<StatementsListResponse> callback);

```

## 13. Download statement of a cards account

Download statement of a cards account by getting the [StatementsResource](../netbanking/src/main/java/cz/csas/netbanking/StatementsResource.java) and then calling the `download` method. The method takes object with properties as a parameter. See [StatementsDownloadParameters](../netbanking/src/main/java/cz/csas/netbanking/StatementsDownloadParameters.java) for all supported parameters.

```java

    // Download card statements
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .withId(Object id)
        .getAccountsResource()
        .withId(Object id)
        .getStatements()
        .download(StatementsDownloadParameters parameters, CallbackWebApi<WebApiStream> callback);

```
