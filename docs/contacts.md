# Contacts

This guide walks you through retrieving current user's accounts contacts. It can contain addresses, phones and email addresses.

[ContactsResource](../netbanking/src/main/java/cz/csas/netbanking/contacts/ContactsResource.java)

```java

    // Get ContactsResource Instance
    Netbanking.getInstance().getNetbankingClient().getContactsResource()...

```

## 1. List all of current users contacts

You can list all of current users contacts by calling the `list` method on [ContactsResource](../netbanking/src/main/java/cz/csas/netbanking/contacts/ContactsResource.java). 

```java

    // List all contacts
    Netbanking.getInstance().getNetbankingClient().getContactsResource()
        .list(CallbackWebApi<ContactsListResponse> callback);

```

## 2. Get individual current users contact

You can get detail of the individual current users contact by calling the `withId` method on [ContactsResource](../netbanking/src/main/java/cz/csas/netbanking/contacts/ContactsResource.java) with id as a parameter and then calling the get method. For complete response see [Contact](../netbanking/src/main/java/cz/csas/netbanking/contacts/Contact.java).

```java

    // Contact detail
    Netbanking.getInstance().getNetbankingClient().getContactsResource()
        .withId(Object id)
        .get(CallbackWebApi<Contact> callback);

```