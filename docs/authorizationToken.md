# Authorization Token

This guide shows how to invalidate authorization token.

[AuthorizationTokenResource](../netbanking/src/main/java/cz/csas/netbanking/authorizationToken/AuthorizationTokenResource.java)

```java

    // Get AuthorizationTokenResource Instance
    Netbanking.getInstance().getNetbankingClient().getOrdersAuthorizationTokenResource()...

```

## 1. Invalidate authorization token

You can invalidate authorization token by calling the `invalidate` method on [AuthorizationTokenResource](../netbanking/src/main/java/cz/csas/netbanking/authorizationToken/AuthorizationTokenResource.java).

```java

    // Invalidate authorization token
    Netbanking.getInstance().getNetbankingClient().getAuthorizationTokenResource()
        .invalidate(CallbackWebApi<WebApiEmptyResponse> callback);

```
