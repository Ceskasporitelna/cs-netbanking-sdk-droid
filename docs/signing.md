# Signing

Most of the create/update/delete active calls done by the user/application need to be signed by the client. User can use various authorization methods, to confirm his/hers intention to execute active operation and authorize it.

You can find possible signing authorization methods in the following list:

- NO AUTHORIZATION - validation of user intent without additional security measure. This form of signing is usually done by clicking some button in the UI.
- TAC - validation of user intent to execute order by one time password sent to user personal device via SMS
- MOBILE CASE (NOT IMPLEMENTED YET) - validation of the user response using mobile application, this method have two forms (user can choose which he'll use)
    - ONLINE - mobile application receives PUSH notification with relevant data for authorization and user just clicks confirmation button in mobile application (data are sent over internet to bank)
    - QR - mobile application retrieves relevant data for authorization by reading QR code displayed in frontend application, generates onetime password and user enters this OTP into frontend application to authorize operation

The signing is done in the following steps:

## 1. Create signable order

You can sign any call that returns signable response. Responses that are signable implement [Signable](https://github.com/Ceskasporitelna/cs-core-sdk-droid/blob/master/core/src/main/java/cz/csas/cscore/webapi/signing/Signable.java) interface. Response that is signable has `signing` key that contains [SigningObject](https://github.com/Ceskasporitelna/cs-core-sdk-droid/blob/master/core/src/main/java/cz/csas/cscore/webapi/signing/SigningObject.java). [SigningObject](https://github.com/Ceskasporitelna/cs-core-sdk-droid/blob/master/core/src/main/java/cz/csas/cscore/webapi/signing/SigningObject.java) contains public `state` which shows the current state of the signing and `signId` which is an unique identifier of the signing process for that particular order. You can use function `isOpen()` to see if the object is ready to be signed.

```java
    
    // call specific resource
    Netbanking.getInstance().getNetbankingClient().getCardsResource()
        .withId(cardId).getLimitsResource()
        .update(request, new CallbackWebApi<ChangeCardLimitsResponse>() {
                   @Override
                   public void success(ChangeCardLimitsResponse changeCardLimitsResponse) {
                       mChangeCardLimitsResponse = changeCardLimitsResponse;
                   }
       
                   @Override
                   public void failure(CsSDKError error) {}
               });
    
    // get the signing object
    SigningObject mSigningObject = mChangeCardLimitsResponse.signing();
    
```

## 2. Get info

Before you can sign the order, you need to find out which authorization methods you can use to sign the object.

You have to call method `getInfo()` to get the necessary information for the signing on the [SigningObject](https://github.com/Ceskasporitelna/cs-core-sdk-droid/blob/master/core/src/main/java/cz/csas/cscore/webapi/signing/SigningObject.java).This method returns Callback with [FilledSigningObject](https://github.com/Ceskasporitelna/cs-core-sdk-droid/blob/master/core/src/main/java/cz/csas/cscore/webapi/signing/FilledSigningObject.java).

```java
    
    // fill your signing object by calling info method
    mSigningObject.getInfo(new CallbackWebApi<FilledSigningObject>() {
          @Override
          public void success(FilledSigningObject filledSigningObject) {
              mFilledSigningObject = filledSigningObject;
          }
    
          @Override
          public void failure(CsSDKError error) {}
      });;
    
```

## 3. Start signing

The [FilledSigningObject](https://github.com/Ceskasporitelna/cs-core-sdk-droid/blob/master/core/src/main/java/cz/csas/cscore/webapi/signing/FilledSigningObject.java) extends [SigningObject](https://github.com/Ceskasporitelna/cs-core-sdk-droid/blob/master/core/src/main/java/cz/csas/cscore/webapi/signing/SigningObject.java) so you get all of the methods and properties like `isOpen()` from it. In addition [FilledSigningObject](https://github.com/Ceskasporitelna/cs-core-sdk-droid/blob/master/core/src/main/java/cz/csas/cscore/webapi/signing/FilledSigningObject.java) has `authorizationType` and `scenarios` properties.

Convenience methods are also available. For example `canBeSignedWith(authType)` which takes `authorizationType` and returns true or false based on whether or not passed `authorizationType` is available, `getPossibleAuthorizationTypes()` method that returns all possible `authorizationTypes`.

The most important methods are `startSigningWithTac()`, `startSigningWithCaseMobile()` and `startSigningWithNoAuthorization()` that return `TacSigningProcess`, `CaseMobileSigningProcess` or `NoAuthSigningProcess` in Callback.

Call one of these methods to start signing.

```java
    
    mFilledSigningObject.startSigningWithMobileCase(new CallbackWebApi<MobileCaseSigningProcess>() {
            @Override
            public void success(MobileCaseSigningProcess mobileCaseSigningProcess) {
                mMobileCaseSigningProcess = mobileCaseSigningProcess;
            }

            @Override
            public void failure(CsSDKError error) {}
        });
    
```

## 4. Finish signing

`TacSigningProcess`, `CaseMobileSigningProcess` or `NoAuthSigningProcess` have two methods. First one is `cancel()` that cancels the signing process and `finishSigning()` which finishes the signing.

`TacSigningProcess` and `CaseMobileSigningProcess` finishSigning takes `oneTimePassword` as a parameter for authorization. All of the methods return updated [SigningObject](https://github.com/Ceskasporitelna/cs-core-sdk-droid/blob/master/core/src/main/java/cz/csas/cscore/webapi/signing/SigningObject.java).

```java
    
    mMobileCaseSigningProcess.finishSigning(String oneTimePassword, CallbackWebApi<SigningObject> callback);
    
```

If the call was successful then the `state` value should be either `DONE` or `OPEN`.

`OPEN` means that you need to sign the order by additional method. Call `getInfo()` on the signing object to continue signing. `DONE` state indicates that the order was fully signed and no further signing is needed.

```java
    
    mSigningObject.isDone();
    
```