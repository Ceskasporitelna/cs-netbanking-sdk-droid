# Authorization Limits

This guide walks you through retrieving current user's authorization limits.

[AuthorizationLimitsResource](../netbanking/src/main/java/cz/csas/netbanking/authorizationLimits/AuthorizationLimitsResource.java)

```java

    // Get AuthorizationLimitsResource Instance
    Netbanking.getInstance().getNetbankingClient().getAuthorizationLimitsResource()...

```

## 1. List all of current users authorization limits

You can list all of current users authorization limits by calling the `list` method on [AuthorizationLimitsResource](../netbanking/src/main/java/cz/csas/netbanking/authorizationLimits/AuthorizationLimitsResource.java). The method takes object with properties such as pageSize or sortBy as a parameter. See all parameters in [AuthorizationLimitsParameters](../netbanking/src/main/java/cz/csas/netbanking/authorizationLimits/AuthorizationLimitsParameters.java).

```java

    // List all authorization limits
    Netbanking.getInstance().getNetbankingClient().getAuthorizationLimitsResource()
        .list(AuthorizationLimitsParameters parameters, CallbackWebApi<AuthorizationLimitsListResponse> callback);

```

## 2. Get individual current users authorization limit

You can get detail of the individual current users authorization limit by calling the `withId` method on [AuthorizationLimitsResource](../netbanking/src/main/java/cz/csas/netbanking/authorizationLimits/AuthorizationLimitsResource.java) with id as a parameter and then calling the get method. For complete response see [AuthorizationLimit](../netbanking/src/main/java/cz/csas/netbanking/authorizationLimits/AuthorizationLimit.java).

```java

    // Get authorization limit
    Netbanking.getInstance().getNetbankingClient().getAuthorizationLimitsResource()
        .withId(Object id)
        .get(CallbackWebApi<AuthorizationLimit> callback);

```