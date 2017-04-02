# Bundles

This guide walks you through creating current user's accounts bundles. Bundle represents set of payment orders which can be signed at once.

[BundlesResource](../netbanking/src/main/java/cz/csas/netbanking/bundles/BundlesResource.java)

```java

    // Get BundlesResource Instance
    Netbanking.getInstance().getNetbankingClient().getBundlesResource()...

```

## 1. Create bundle

Create bundle by getting the [BundlesResource](../netbanking/src/main/java/cz/csas/netbanking/bundles/BundlesResource.java) and then calling the `create` method. The create method takes object with properties as a parameter. See [BundleCreateRequest](../netbanking/src/main/java/cz/csas/netbanking/bundles/BundleCreateRequest.java) for all supported parameters and [Bundle](../netbanking/src/main/java/cz/csas/netbanking/bundles/Bundle.java) for full response.

```java

    // Create bundle
    Netbanking.getInstance().getNetbankingClient().getBundlesResource()
        .create(BundleCreateRequest request, CallbackWebApi<Bundle> callback);
       
```
