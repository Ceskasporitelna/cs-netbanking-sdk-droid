# CSNetbankingSDK
This SDK allows you to access information from Česká spořitelna a.s. [Netbanking API](http://docs.ext0csnetbanking3.apiary.io/).

# [CHANGELOG](CHANGELOG.md)

# Requirements
- Android 4.1+
- CSCoreSDK 0.16+
- Gradle 2.8+
- Android Studio 1.5+

# NetbankingSDK Installation
**IMPORTANT!** You need to have your SSH keys registered with the GitHub since this repository is private.

You can install CSNetbankingSDK using the following git and gradle settings.

1. Navigate to your git configured project repository and process this command to add CSNetbanking as a submodule:
```
    git submodule add https://github.com/Ceskasporitelna/cs-netbanking-sdk-droid.git your_lib_folder/cs-netbanking-sdk-droid
```

2. Insert these two lines into your project settings.gradle file to include your submodules:
```gradle
    include ':core'
    project (':core').projectDir = new File(settingsDir, 'your_lib_folder/cs-netbanking-sdk-droid/lib/cs-core-sdk-droid/core')
    include ':netbanking'
    project (':netbanking').projectDir = new File(settingsDir, 'your_lib_folder/cs-netbanking-sdk-droid/netbanking')
```

3. Insert this line into your module build.gradle file to compile your submodules:
```gradle
    dependencies {
        ...
        compile project(':core')
        compile project(':netbanking')
        ...
    }
```

As CSCoreSDK is a dependency of CSNetbankingSDK, you are able to use it as well.

**See [CoreSDK](https://github.com/Ceskasporitelna/cs-core-sdk-droid)**

# Usage

After you've installed the SDK using git submodules you will be able to use the module in your project.

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