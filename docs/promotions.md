# Promotions

This guide walks you through retrieving promotions.

[PromotionsResource](../netbanking/src/main/java/cz/csas/netbanking/promotions/PromotionsResource.java)

```java
    
    // Get PromotionsResource Instance
    Netbanking.getInstance().getNetbankingClient().getPromotionsResource()...
    
```

## 1. List all promotions for user

You can list all promotions by calling the `list` method on [PromotionsResource](../netbanking/src/main/java/cz/csas/netbanking/promotions/PromotionsResource.java). 

```java

    // List all promotions
    Netbanking.getInstance().getNetbankingClient().getPromotionsResource()
        .list(CallbackWebApi<PromotionsListResponse> callback);

```

## 2. Hide promotion

Hide promotion by getting the [PromotionsActionsResource](../netbanking/src/main/java/cz/csas/netbanking/promotions/PromotionsActionsResource.java) and then calling the `create` method. The create method takes object with properties as a parameter. See [PromotionActionCreateRequest](../netbanking/src/main/java/cz/csas/netbanking/promotions/PromotionActionCreateRequest.java) for all supported parameters and [PromotionActionCreateResponse](../netbanking/src/main/java/cz/csas/netbanking/promotions/PromotionActionCreateResponse.java) for full response.

```java

    // Hide promotion
    Netbanking.getInstance().getNetbankingClient().getPromotionsResource()
            .create(PromotionActionCreateRequest request, CallbackWebApi<PromotionActionCreateResponse> callback);

```
