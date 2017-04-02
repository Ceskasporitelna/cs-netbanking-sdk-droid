# Securities

This guide walks you through retrieving securities.

[SecuritiesResource](../netbanking/src/main/java/cz/csas/netbanking/securities/SecuritiesResource.java)

```java

    // Get SecuritiesResource Instance
    Netbanking.getInstance().getNetbankingClient().getSecuritiesResource()...

```

## 1. List all securities accounts

You can list all securities by calling the `list` method on [SecuritiesResource](../netbanking/src/main/java/cz/csas/netbanking/securities/SecuritiesResource.java). 

```java

    // List all securities
    Netbanking.getInstance().getNetbankingClient().getSecuritiesResource()
        .list(PaginatedParameters parameters, CallbackWebApi<SecuritiesListResponse> callback);

```

## 2. Get security account detail

Get security detail by calling the `withId` method on [SecuritiesResource](../netbanking/src/main/java/cz/csas/netbanking/securities/SecuritiesResource.java) and then calling the get method. See [Security](../netbanking/src/main/java/cz/csas/netbanking/securities/Security.java) for full response.

```java

    // Security detail
    Netbanking.getInstance().getNetbankingClient().getSecuritiesResource()
        .withId(Object id)
        .get(CallbackWebApi<Security> callback);

```

## 3. Change Securities Account Settings

Update security by getting the [SecuritiesResource](../netbanking/src/main/java/cz/csas/netbanking/securities/SecuritiesResource.java) and then calling the `update` method on it. The method takes object with properties as a parameter. Currently only alias can be changed. For all supported parameters see [SecurityUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/securities/SecurityUpdateRequest.java) and [SecurityUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/securities/SecurityUpdateResponse.java) for full response.

```java

    // Update security
    Netbanking.getInstance().getNetbankingClient().getSecuritiesResource()
        .withId(Object id)
        .update(SecurityUpdateRequest request, CallbackWebApi<SecurityUpdateResponse> callback);

```

## 4. Add or change note and mark securities transactions

Add or change note and mark security transaction by getting the [CzTransactionsResource](../netbanking/src/main/java/cz/csas/netbanking/CzTransactionsResource.java) and calling the `update` method on it. The method takes object with properties as a parameter. See all supported parameters in [TransactionUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/TransactionUpdateRequest.java) and [TransactionUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/TransactionUpdateResponse.java) for full response.


```java

    // Securities transaction update
    Netbanking.getInstance().getNetbankingClient().getSecuritiesResource()
        .withId(Object id)
        .getTransactionsResource()
        .withId(Object id)
        .update(TransactionUpdateRequest request, CallbackWebApi<TransactionUpdateResponse> callback);

```

## 5. Export securities transactions history

Extract transaction history into signed PDF by getting the [CzTransactionsResource](../netbanking/src/main/java/cz/csas/netbanking/CzTransactionsResource.java) and calling the `export` method on it. The method takes object with properties as a parameter. See all supported parameters in [ExportTransactionsParameters](../netbanking/src/main/java/cz/csas/netbanking/ExportTransactionsParameters.java).

```java

    // Securities transaction export
    Netbanking.getInstance().getNetbankingClient().getSecuritiesResource()
        .withId(Object id)
        .getTransactionsResource()
        .getExportResource()
        .export(ExportTransactionsParameters parameters, CallbackWebApi<WebApiStream> callback);

```
