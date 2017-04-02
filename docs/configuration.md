#Configuration
In order to use the NetbankingSDK, you have to configure the CoreSDK.
##1. Configure CoreSDK

Before using any SDK based on CoreSDK in your application, you need to initialize it by providing it your WebApiKey:

```java
    CoreSDK.getInstance().useWebApiKey( "YourApiKey" )
```

For more configuration options see **[CoreSDK configuration guide](https://github.com/Ceskasporitelna/cs-core-sdk-droid/blob/master/docs/configuration.md)**

## 2. Configure Netbanking
You have to configure places before using NetbankingSDK.

You can find example of Netbanking configuration options below:
```java
    
    Netbanking.getInstance().init();
    
    Netbanking.getInstance().init(WebApiConfiguration webApiConfiguration);

```   
Both configuration methods are allowed. The necessary condition for the first one is to have configured CoreSDK as noted above in the first section. The second method is available because of configuration independent on CoreSDK.

Now you are all set to use the NetbankingSDK! See the [Netbanking usage guide](netbanking.md) to learn how to use NetbankingSDK.


