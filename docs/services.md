# Services

This guide walks you through retrieving services.

[ServicesResource](../netbanking/src/main/java/cz/csas/netbanking/services/ServicesResource.java)

```java

    // Get ServicesResource Instance
    Netbanking.getInstance().getNetbankingClient().getServicesResource()...

```

## 1. List all services

You can list all services by calling the `list` method on [ServicesResource](../netbanking/src/main/java/cz/csas/netbanking/services/ServicesResource.java). 

```java

    // List all services
    Netbanking.getInstance().getNetbankingClient().getServicesResource()
        .list(PaginatedParameters parameters, CallbackWebApi<ServicesListResponse> callback);

```