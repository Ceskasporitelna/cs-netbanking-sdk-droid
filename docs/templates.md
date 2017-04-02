# Templates

This guide walks you through retrieving templates.

[TemplatesResource](../netbanking/src/main/java/cz/csas/netbanking/templates/TemplatesResource.java)

```java

    // Get TemplatesResource Instance
    Netbanking.getInstance().getNetbankingClient().getTemplatesResource()...

```

## 1. List all templates

You can list all templates by calling the `list` method on [TemplatesResource](../netbanking/src/main/java/cz/csas/netbanking/templates/TemplatesResource.java).

```java

    // List all templates
    Netbanking.getInstance().getNetbankingClient().getTemplatesResource()
        .list(PaginatedParameters parameters, CallbackWebApi<TemplatesListResponse> callback);

```

## 2. Get template detail

Get template detail by calling the `withId` method on [TemplatesResource](../netbanking/src/main/java/cz/csas/netbanking/templates/TemplatesResource.java) and then calling the get method. See [Template](../netbanking/src/main/java/cz/csas/netbanking/templates/Template.java) for full response.

```java

    // Template detail
    Netbanking.getInstance().getNetbankingClient().getTemplatesResource()
        .withId(Object id)
        .get(CallbackWebApi<Template> callback);

```
