# Plugins

This guide walks you through retrieving current user's plugins.

[PluginsResource](../netbanking/src/main/java/cz/csas/netbanking/plugins/PluginsResource.java)

```java

    // Get PluginsResource Instance
    Netbanking.getInstance().getNetbankingClient().getPluginsResource()...

```

## 1. List all of current users plugins

You can list all of current users plugins by calling the `list` method on [PluginsResource](../netbanking/src/main/java/cz/csas/netbanking/plugins/PluginsResource.java). 

```java

    // List all plugins
    Netbanking.getInstance().getNetbankingClient().getPluginsResource()
        .list(PaginatedParameters parameters, CallbackWebApi<PluginsListResponse> callback);

```

## 2. Update plugin

Update plugin by getting the [PluginResource](../netbanking/src/main/java/cz/csas/netbanking/plugins/PluginResource.java) and then calling the `update` method on it. The method takes object with properties as a parameter. Currently only alias can be changed. For all supported parameters see [PluginUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/plugins/PluginUpdateRequest.java) and [PluginUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/plugins/PluginUpdateResponse.java) for full response.

```java

    // Update plugin
    Netbanking.getInstance().getNetbankingClient().getPluginsResource()
        .withId(Object id)
        .update(PluginUpdateRequest request, CallbackWebApi<PluginUpdateResponse> callback);

```
