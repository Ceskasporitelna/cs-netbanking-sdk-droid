# Accounts

This guide walks you through retrieving current user's accounts detail and other information like account's services, transactions, repayments etc. There are also actions like changing account's settings or changing and adding note to transaction. Finally you can export transactions or download statements.

[AccountsResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountsResource.java)

```java

    // Get AccountsResource Instance
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()...
    
```

## 1. List all of current users accounts

You can list all of current users accounts by calling the `list` method on [AccountsResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountsResource.java). The method takes object with properties such as pageSize or sortBy as a parameter. See all parameters in [AccountsParameters](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountsParameters.java).

```java

    // List all accounts
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
        .list(AccountsParameters accountsParameters, CallbackWebApi<AccountsListResponse> callback);

```

## 2. Get individual current users account

You can get detail of the individual current users account by calling the `withId` method on [AccountsResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountsResource.java) with id as a parameter and then calling the get method. For complete response see [MainAccount](../netbanking/src/main/java/cz/csas/netbanking/accounts/MainAccount.java).

```java

    // Account detail
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
        .withId(Object id)
        .get(CallbackWebApi<MainAccount> callback);

```

## 3. Update current users account

You can update current users account by calling the `withId` method on [AccountsResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountsResource.java) with id as a parameter and then calling the update method and giving it payload in object as a parameter. Currently only alias can be changed. For payload properties please see [ChangeAccountSettingsRequest](../netbanking/src/main/java/cz/csas/netbanking/accounts/ChangeAccountSettingsRequest.java) and check also the response [ChangeAccountSettingsResponse](../netbanking/src/main/java/cz/csas/netbanking/accounts/ChangeAccountSettingsResponse.java).

```java

    // Account update
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
        .withId(Object id)
        .update(ChangeAccountSettingsRequest request, CallbackWebApi<ChangeAccountSettingsResponse> callback);

```

## 4. Get accounts balances

Get accounts balances by getting the [AccountBalanceResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountBalanceResource.java) and then calling the `get` method. For complete response see [AccountBalance](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountBalance.java)

```java

    // Account balance
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getBalanceResource()
            .get(CallbackWebApi<AccountBalance> callback);

```

## 5. List accounts services

List accounts services by getting the [AccountServicesResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountServicesResource.java) and then calling the `list` method. The method takes object with properties as a parameter. Services resource supports pagination.

```java

    // Account tra
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getServicesResource()
            .list(PaginatedParameters parameters, CallbackWebApi<AccountServicesListResponse> callback);

```

## 6. Add or change note and mark transaction

Add, change or mark transaction by calling the `update` method on [TransactionResource](../netbanking/src/main/java/cz/csas/netbanking/TransactionResource.java) and passing it object with options as a parameter. For payload properties please see [TransactionUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/TransactionUpdateRequest.java) and check also the response [TransactionUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/TransactionUpdateResponse.java).

```java

    // Account transaction update
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getTransactionsResource()
            .withId(Object id)
            .update(TransactionUpdateRequest request, CallbackWebApi<TransactionUpdateResponse> callback);

```

## 7. Export transactions history

Export transaction history into signed PDF by calling the `export` method on [TransactionsResource](../netbanking/src/main/java/cz/csas/netbanking/TransactionsResource.java) and passing it object with options as a parameter. See [ExportTransactionsParameters](../netbanking/src/main/java/cz/csas/netbanking/ExportTransactionsParameters.java).

```java

    // Account transactions history
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getTransactionsResource()
            .getExportResource()
            .export(ExportTransactionsParameters parameters, CallbackWebApi<WebApiStream> callback);

```

## 8. List accounts reservations

List accounts reservations by getting the [AccountReservationsResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountReservationsResource.java) and then calling the `list` method. The method takes object with properties as a parameter and supports pagination. See [ReservationsListResponse](../netbanking/src/main/java/cz/csas/netbanking/accounts/ReservationsListResponse.java) for full response.

```java

    // Account reservations
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getReservationsResource()
            .list(PaginatedParameters parameters, CallbackWebApi<ReservationsListResponse> callback);

```

## 9. Revolve loan disbursement

Revolve loan disbursement by getting the [AccountTransferResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountTransferResource.java) and then calling the `update` method on it. The method takes object with properties as a parameter. See all supported properties in [AccountTransferRequest](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountTransferRequest.java) and [TransferResponse](../netbanking/src/main/java/cz/csas/netbanking/TransferResponse.java) for full response.

```java

    // Account loan disbursement revolving
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getTransferResource()
            .update(AccountTransferRequest request, CallbackWebApi<TransferResponse> callback);

```

## 10. List accounts repayments

List accounts repayments by getting the [AccountRepaymentsResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountRepaymentsResource.java) and then calling the `list` method. For full response see [RepaymentsListResponse](../netbanking/src/main/java/cz/csas/netbanking/accounts/RepaymentsListResponse.java).

```java

    // Account repayments
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getRepaymentsResource()
            .list(CallbackWebApi<RepaymentsListResponse> callback);

```

## 11. List accounts statements

List accounts statements by getting the [StatementsResource](../netbanking/src/main/java/cz/csas/netbanking/StatementsResource.java) and then calling the `list` method. The method takes object with properties such as pageSize or sortBy as a parameter. See all supported parameters in [StatementsParameters](../netbanking/src/main/java/cz/csas/netbanking/StatementsParameters.java). For full response see [StatementsListResponse](../netbanking/src/main/java/cz/csas/netbanking/StatementsListResponse.java).

```java

    // Account statements
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getStatementsResource()
            .list(StatementsParameters parameters, CallbackWebApi<StatementsListResponse> callback);

```

## 12. Download accounts statements

Download accounts statement by getting the [StatementsResource](../netbanking/src/main/java/cz/csas/netbanking/StatementsResource.java) and then calling the `download` method on it. The method takes object with properties as a parameter. For all supported parameters see [StatementsDownloadParameters](../netbanking/src/main/java/cz/csas/netbanking/StatementsDownloadParameters.java).

```java

    // Download account statements
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getStatementsResource()
            .download(StatementsDownloadParameters parameters, CallbackWebApi<WebApiStream> callback);

```

## 13. List sub account statements

List sub accounts statements by getting the [StatementsResource](../netbanking/src/main/java/cz/csas/netbanking/StatementsResource.java) and then calling the `list` method on it. The list method takes object with properties as a parameter. See all supported parameters in [StatementsParameters](../netbanking/src/main/java/cz/csas/netbanking/StatementsParameters.java). For full response see [StatementsListResponse](../netbanking/src/main/java/cz/csas/netbanking/StatementsListResponse.java).

```java

    // Sub account statements
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getSubAccountsResource()
            .withId(Object id)
            .getStatementsResource()
            .list(StatementsParameters parameters, CallbackWebApi<StatementsListResponse> callback);

```

## 14. Download sub account statements

Download sub accounts statement by getting the [StatementsResource](../netbanking/src/main/java/cz/csas/netbanking/StatementsResource.java) and then calling the `download` method on it. The method takes object with properties as a parameter. For all supported parameters see [StatementsDownloadParameters](../netbanking/src/main/java/cz/csas/netbanking/StatementsDownloadParameters.java).

```java

    // Download sub account statements
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getSubAccountsResource()
            .withId(Object id)
            .getStatementsResource()
            .download(StatementsDownloadParameters parameters, CallbackWebApi<WebApiStream> callback);

```

## 15. List accounts direct debits

List accounts direct debits by getting the [AccountDirectDebitsResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountDirectDebitsResource.java) and then calling the `list` method on it. The list method takes object with properties as a parameter. See all supported parameters in [DirectDebitsParameters](../netbanking/src/main/java/cz/csas/netbanking/accounts/DirectDebitsParameters.java). For full response see [DirectDebitsListResponse](../netbanking/src/main/java/cz/csas/netbanking/accounts/DirectDebitsListResponse.java).

```java

    // List all direct debits
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getDirectDebitsResource()
            .list(DirectDebitsParameters parameters, CallbackWebApi<DirectDebitsListResponse> callback);

```

## 16. Create direct debit

Create direct debit by getting the [AccountDirectDebitsResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountDirectDebitsResource.java) and then calling the `create` method. The create method takes object with properties as a parameter. See [DirectDebitCreateRequest](../netbanking/src/main/java/cz/csas/netbanking/accounts/DirectDebitCreateRequest.java) for all supported parameters and [DirectDebitResponse](../netbanking/src/main/java/cz/csas/netbanking/accounts/DirectDebitResponse.java) for full response.

```java

    // Create direct debit
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getDirectDebitsResource()
            .create(DirectDebitCreateRequest request, CallbackWebApi<DirectDebitResponse> callback);

```

## 17. Get direct debit

You can get detail of the direct debit by calling the `withId` method on [AccountDirectDebitsResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountDirectDebitsResource.java) with id as a parameter and then calling the get method. For complete response see [DirectDebit](../netbanking/src/main/java/cz/csas/netbanking/accounts/DirectDebit.java).

```java

    // Get direct debit
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getDirectDebitsResource()
            .withId(Object id)
            .get(CallbackWebApi<DirectDebit> callback);

```

## 18. Delete direct debit

Delete direct debit by getting the [AccountDirectDebitResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountDirectDebitResource.java) and then calling the `delete` method.

```java

    // Delete direct debit
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getDirectDebitsResource()
            .withId(Object id)
            .delete(CallbackWebApi<DirectDebitResponse> callback);

```

## 19. List standing orders

List accounts standing orders by getting the [AccountStandingOrdersResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountStandingOrdersResource.java) and then calling the `list` method on it. The list method takes object with properties as a parameter. See all supported parameters in [StandingOrdersParameters](../netbanking/src/main/java/cz/csas/netbanking/accounts/StandingOrdersParameters.java). For full response see [StandingOrdersListResponse](../netbanking/src/main/java/cz/csas/netbanking/accounts/StandingOrdersListResponse.java).

```java

    // List standing orders
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getStandingOrdersResource()
            .list(StandingOrdersParameters parameters, CallbackWebApi<StandingOrdersListResponse> callback);
       
```

## 20. Create standing orders

Create standing order by getting the [AccountStandingOrdersResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountStandingOrdersResource.java) and then calling the `create` method. The create method takes object with properties as a parameter. See [StandingOrderCreateRequest](../netbanking/src/main/java/cz/csas/netbanking/accounts/StandingOrderCreateRequest.java) for all supported parameters and [StandingOrderResponse](../netbanking/src/main/java/cz/csas/netbanking/accounts/StandingOrderResponse.java) for full response.

```java

    // Create standing order
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getStandingOrdersResource()
            .create(StandingOrderCreateRequest request, CallbackWebApi<StandingOrderResponse> callback);
       
```

## 21. Get standing orders

You can get detail of the standing order by calling the `withId` method on [AccountStandingOrdersResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountStandingOrdersResource.java) with id as a parameter and then calling the get method. For complete response see [StandingOrder](../netbanking/src/main/java/cz/csas/netbanking/accounts/StandingOrder.java).

```java

    // Get standing order
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getStandingOrdersResource()
            .withId(Object id)
            .get(CallbackWebApi<StandingOrder> callback);
       
```

## 22. Delete standing orders

Delete direct debit by getting the [AccountStandingOrderResource](../netbanking/src/main/java/cz/csas/netbanking/accounts/AccountStandingOrderResource.java) and then calling the `delete` method.

```java

    // Delete standing order 
    Netbanking.getInstance().getNetbankingClient().getAccountsResource()
            .withId(Object id)
            .getStandingOrdersResource()
            .withId(Object id)
            .delete(CallbackWebApi<StandingOrderResponse> callback);
       
```
