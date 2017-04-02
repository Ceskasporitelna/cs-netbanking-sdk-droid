# Settings

This guide walks you through retrieving settings.

[SettingsResource](../netbanking/src/main/java/cz/csas/netbanking/settings/SettingsResource.java)

```java

    // Get SettingsResource Instance
    Netbanking.getInstance().getNetbankingClient().getSettingsResource()...

```

## 1. Get user settings 

Get settings by calling the `withId` method on [SettingsResource](../netbanking/src/main/java/cz/csas/netbanking/settings/SettingsResource.java) and then calling the get method. See [Settings](../netbanking/src/main/java/cz/csas/netbanking/settings/Settings.java) for full response.

```java

    // Get settings
    Netbanking.getInstance().getNetbankingClient().getSettingsResource()
        .get(CallbackWebApi<Settings> callback);

```

## 2. Change user settings

Update settings by getting the [SettingsResource](../netbanking/src/main/java/cz/csas/netbanking/settings/SettingsResource.java) and then calling the `update` method on it. The method takes object with properties as a parameter. Currently only alias can be changed. For all supported parameters see [SettingsUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/settings/SettingsUpdateRequest.java) and [SettingsUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/settings/SettingsUpdateResponse.java) for full response.

```java

    // Update settings
    Netbanking.getInstance().getNetbankingClient().getSettingsResource()
        .update(SettingsUpdateRequest request, CallbackWebApi<SettingsUpdateResponse> callback);

```
