# Goals

This guide walks you through retrieving current user's accounts goals.

[GoalsResource](../netbanking/src/main/java/cz/csas/netbanking/goals/GoalsResource.java)

```java

    // Get GoalsResource Instance
    Netbanking.getInstance().getNetbankingClient().getGoalsResource()...

```

## 1. List all of current users goals

You can list all of current users goals by calling the `list` method on [GoalsResource](../netbanking/src/main/java/cz/csas/netbanking/goals/GoalsResource.java). 

```java

    // List all goals
    Netbanking.getInstance().getNetbankingClient().getGoalsResource()
        .list(CallbackWebApi<GoalsListResponse> callback);

```

## 2. Update current users goals 

You can update current users goals by calling the `withId` method on [GoalsResource](../netbanking/src/main/java/cz/csas/netbanking/goals/GoalsResource.java) with id as a parameter and then calling the update method and giving it payload in object as a parameter. For payload properties please see [GoalsUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/goals/GoalsUpdateRequest.java) and check also the response [GoalsListResponse](../netbanking/src/main/java/cz/csas/netbanking/goals/GoalsListResponse.java).

```java

    // Goals update
    Netbanking.getInstance().getNetbankingClient().getGoalsResource()
        .withId(Object id)
        .update(GoalsUpdateRequest request, CallbackWebApi<GoalsListResponse> callback);

```