# Messages

This guide walks you through retrieving current user's messages.

[MessagesResource](../netbanking/src/main/java/cz/csas/netbanking/messages/MessagesResource.java)

```java
    
    // Get MessagesResource Instance
    Netbanking.getInstance().getNetbankingClient().getMessagesResource()...

```

## 1. List all messages

You can list all of current users messages by calling the `list` method on [MessagesResource](../netbanking/src/main/java/cz/csas/netbanking/messages/MessagesResource.java). 

```java

    // List all messages
    Netbanking.getInstance().getNetbankingClient().getMessagesResource()
        .list(MessagesParameters parameters, CallbackWebApi<MessagesListResponse> callback);

```

## 2. List all messages mandatory resources

You can list all of current users messages by calling the `list` method on [MessagesMandatoryResource](../netbanking/src/main/java/cz/csas/netbanking/messages/MessagesMandatoryResource.java). 

```java

    // List all messages mandatory resources
    Netbanking.getInstance().getNetbankingClient().getMessagesResource().getMandatoryResource()
        .list(CallbackWebApi<MessagesListResponse> callback);

```

## 3. Get message detail

You can get message detail by calling the `withId` method on [MessagesResource](../netbanking/src/main/java/cz/csas/netbanking/messages/MessagesResource.java) with id as a parameter and then calling the get method. For full response see [Message](../netbanking/src/main/java/cz/csas/netbanking/messages/Message.java).

```java

    // Message detail
    Netbanking.getInstance().getNetbankingClient().getMessagesResource()
        .withId(Object id)
        .get(CallbackWebApi<Message> callback);

```

## 4. Update message

Update message by getting the [MessageResource](../netbanking/src/main/java/cz/csas/netbanking/messages/MessageResource.java) and then calling the `update` method on it. The method takes object with properties as a parameter. Currently only alias can be changed. For all supported parameters see [MessageUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/messages/MessageUpdateRequest.java) and [WebApiEmptyResponse](https://github.com/Ceskasporitelna/cs-core-sdk-droid/blob/master/core/src/main/java/cz/csas/cscore/webapi/WebApiEmptyResponse.java) for full response.

```java

    // Update message
    Netbanking.getInstance().getNetbankingClient().getMessagesResource()
        .withId(Object id)
        .update(MessageUpdateRequest request, CallbackWebApi<WebApiEmptyResponse> callback);

```

## 5. Delete message

Delete message by getting the [MessageResource](../netbanking/src/main/java/cz/csas/netbanking/messages/MessageResource.java) and then calling the `delete` method.

```java

    // Delete message
    Netbanking.getInstance().getNetbankingClient().getMessagesResource()
            .withId(Object id)
            .delete(CallbackWebApi<WebApiEmptyResponse> callback);

```

## 6. Download message attachment

Download message attachment by getting the [MessageAttachmentResource](../netbanking/src/main/java/cz/csas/netbanking/messages/MessageAttachmentResource.java) and then calling the `download` method on it.

```java

    // Download message attachment
    Netbanking.getInstance().getNetbankingClient().getMessagesResource()
            .withId(Object id)
            .getAttachmentsResource()
            .withId(Object id)
            .download(CallbackWebApi<WebApiStream> callback);

```