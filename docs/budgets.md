# Budgets

This guide walks you through retrieving current user's accounts budgets.

[BudgetsResource](../netbanking/src/main/java/cz/csas/netbanking/budgets/BudgetsResource.java)

```java

    // Get BudgetsResource Instance
    Netbanking.getInstance().getNetbankingClient().getBudgetsResource()...

```

## 1. List all of current users budgets

You can list all of current users budgets by calling the `list` method on [BudgetsResource](../netbanking/src/main/java/cz/csas/netbanking/budgets/BudgetsResource.java). 

```java

    // List all budgets
    Netbanking.getInstance().getNetbankingClient().getBudgetsResource()
        .list(CallbackWebApi<BudgetsListResponse> callback);

```

## 2. Update current users budgets

You can update current users budget by calling the `update` method on [BudgetsResource](../netbanking/src/main/java/cz/csas/netbanking/budgets/BudgetsResource.java). For payload properties please see [BudgetsUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/budgets/BudgetsUpdateRequest.java) and check also the response [BudgetsListResponse](../netbanking/src/main/java/cz/csas/netbanking/budgets/BudgetsListResponse.java).

```java

    // Budget update
    Netbanking.getInstance().getNetbankingClient().getBudgetsResource()
        .update(BudgetsUpdateRequest request, CallbackWebApi<BudgetsListResponse> callback);

```