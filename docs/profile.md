# Profile

This guide walks you through retrieving current user's profile and information about their last logins.

[ProfileResource](../netbanking/src/main/java/cz/csas/netbanking/profile/ProfileResource.java)

```java

    // Get ProfileResource Instance
    Netbanking.getInstance().getNetbankingClient().getProfileResource()...

```

## 1. Get current users profile detail

You can get current users profile detail by calling the get method on [ProfileResource](../netbanking/src/main/java/cz/csas/netbanking/profile/ProfileResource.java). For complete response with description please see [Profile](../netbanking/src/main/java/cz/csas/netbanking/profile/Profile.java).

```java

    // Users profile
    Netbanking.getInstance().getNetbankingClient().getProfileResource()
        .get(CallbackWebApi<Profile> callback);

```

## 2. List current users last logins

You can list current user's last logins by calling the list method on [LastLoginResource](../netbanking/src/main/java/cz/csas/netbanking/profile/LastLoginResource.java). For complete response with description please see [LastLoginListResponse](../netbanking/src/main/java/cz/csas/netbanking/profile/LastLoginListResponse.java).

```java

    // Users last logins
    Netbanking.getInstance().getNetbankingClient().getProfileResource()
        .getLastLogin()
        .list(CallbackWebApi<LastLoginListResponse> callback);

```