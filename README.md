# CSNetbankingSDK
This SDK allows you to access information from Česká spořitelna a.s. [Netbanking API](http://docs.ext0csnetbanking3.apiary.io/).

# [CHANGELOG](CHANGELOG.md)

# Requirements
- Android 4.1+
- CSCoreSDK 0.16+
- Gradle 2.8+
- Android Studio 1.5+

# NetbankingSDK Installation

## Install
You can install CSNetbankingSDK using the following gradle settings.

1. Check your project build.gradle file that it contains `JCenter` repository:
```gradle
    allprojects {
        repositories {
            ...
            jcenter()
            ...
        }
    }
```

2. Insert these lines into your module build.gradle file to compile CSNetbankingSDK and CoreSDK (change x.y.z to the version you want to use):
```gradle
    dependencies {
        ...
        compile 'cz.csas:cs-core-sdk:x.y.z@aar'
        compile 'cz.csas:cs-netbanking-sdk:x.y.z@aar'
        ...
    }
```

As CSCoreSDK is a dependency of CSNetbankingSDK, you are able to use it as well.

**See [CoreSDK](https://github.com/Ceskasporitelna/cs-core-sdk-droid)**

# Usage

After you've installed the SDK you will be able to use the module in your project.

## Configuration
Before using CSAS SDKs in your application, you need to initialize it by providing it your WebApiKey:

```java
    // Set your WebApi key
    CoreSDK.getInstance().useWebApiKey( "YourApiKey" )

    // Initialize netbanking
    // Only if you haven't set up WebApiConfiguration for CoreSDK
    Netbanking.getInstance().init(WebApiConfiguration);

    //Obtain your NetbankingClient
    NetbankingClient client = Netbanking.getInstance().getNetbankingClient();

```
**See [configuration guide](docs/configuration.md)** for all the available configuration options.

## NetbankingSDK Usage Guide
**See [Usage Guide](./docs/netbanking.md)** for detailed usage guide.

**TIP!** - You can also learn Netbanking SDK by example in our [**demo**](https://github.com/Ceskasporitelna/csas-sdk-demo-droid)!

## Proguard
The NetbankingSDK Proguard additions are necessary to be specified besides the recommended Android Proguard configuration. Copy the NetbankingSDK proguard rules to your project.

**See [consumer-proguard-rules.pro](./netbanking/consumer-proguard-rules.pro).

# Contributing
Contributions are more than welcome!

Please read our [contribution guide](CONTRIBUTING.md) to learn how to contribute to this project.

# Terms and License
Please read our [terms and conditions in license](LICENSE.md)