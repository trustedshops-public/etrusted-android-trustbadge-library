# etrusted-android-trustbadge-library

[![trustedshops-public](https://circleci.com/gh/trustedshops-public/etrusted-android-trustbadge-library.svg?style=shield)](https://circleci.com/gh/trustedshops-public/etrusted-android-trustbadge-library)
[![GitHub License](https://img.shields.io/badge/license-MIT-lightgrey.svg)](https://github.com/trustedshops-public/etrusted-android-trustbadge-library/blob/main/LICENSE)
[![codecov](https://codecov.io/gh/trustedshops-public/etrusted-android-trustbadge-library/branch/main/graph/badge.svg?token=CnT4ETYgkH)](https://codecov.io/gh/trustedshops-public/etrusted-android-trustbadge-library)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=trustedshops-public_etrusted-android-trustbadge-library&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=trustedshops-public_etrusted-android-trustbadge-library)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=trustedshops-public_etrusted-android-trustbadge-library&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=trustedshops-public_etrusted-android-trustbadge-library)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=trustedshops-public_etrusted-android-trustbadge-library&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=trustedshops-public_etrusted-android-trustbadge-library)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=trustedshops-public_etrusted-android-trustbadge-library&metric=bugs)](https://sonarcloud.io/summary/new_code?id=trustedshops-public_etrusted-android-trustbadge-library)

> This project is currently work in progress and used only be a few
> customers. APIs might not be stable yet and might change without
> further notice

Show the Trustmark and the Shop Grade widget in your Android app, all in 5 steps!

The Trustmark widget shows the validity of your trust certificate by Trusted Shop:

<img src="https://user-images.githubusercontent.com/27926337/215702112-ae9ea5c8-76f1-479c-8d2c-fc6726204f06.jpg" height="90">

In case of an expired certificate, the Trustmark widgets is presented like the following instead:

<img src="https://user-images.githubusercontent.com/27926337/215715480-a614bee6-20f8-4012-bba0-5ddbf2be46d5.png" height="90">

The Shop Grade widget expands to show shop rating and status with a nice animation effect. Currently, the widget only shows the aggregate rating and the shop status.
In the future, the widget will also show the the shop reviews.

<img src="https://user-images.githubusercontent.com/27926337/215702099-a4a99457-23e6-41b9-9811-f91282a1f4fc.jpg" height="100">


## Usage
**Requirements:**
- Access to [eTrusted Control Center](https://app.etrusted.com)
- Your `channelId` and `TSID`
For more info about how to get `channelId` and `TSID` read the [Getting the `channelId` and `TSID`]() sections (last section in this document).

You can add the Trustbadge widget to your Android project in 5 steps:

1. [📝 Get your `client_id` and `client_secret`](#1-📝-get-your-client_id-and-client_secret)
2. [🛠️ Copy the configuration file](#2-🛠️-copy-the-configuration-file)
3. [🐘 Implement the dependency](#3-🐘-implement-the-dependency)
4. [⚙️ Configure the library](#4-⚙️-configure-the-library)
5. [🚀 Show the widget](#5-🚀-show-the-widget)

### 1. 📝 Get your `client_id` and `client_secret`
- Navigate to [eTrustd Control Center](https://app.etrusted.com) on your browser
- Navigate to `Settings > SSO Clients MAnagement`
- Click on `Create new client` and follow the steps to get a set of `client_id` and `client_secret`.
- Note them down as you will need it in the next step.

### 2. 🛠️ Copy the configuration file
- Create an empty file named `trustbadge-config.json` under root directory of your android project.
- Add the following json template inside the file:
```
{
  "client_id": "(Your client id)",
  "client_secret": "(Your client secret)"
}
```
- Replace `(Your client id)` with your client id from the eTrusted control center
- Replace `(Your client secret)` with your client secret from the eTrusted control center
- Save the file.

### 3. 🐘 Implement the dependency
- In your `app` module's `build.gradle` file, add the following line in your dependencies block:
```
dependencies {
    implementation("com.etrusted.android.trustbadge.library:(version))")
}
```
- replace (version) with the latest version of the library. (See the releases page).
- Sync the project and make sure it is successful. If it is not, please make sure that the configuration file is placed under project root directory (e.g. not inside the `app` module's directory)

### 4. ⚙️ Configure the library 
- Once the dependency is added and the gradle sync is successful, add the following line in the launcher activity (e.g. MainActivity). It needs to be called once during the app startup before using the Trustbage library's widgets.
- Now your project is ready to use the widget! 

### 5. 🚀 Show the widget:
We recommend using Jetpack Compose to show the widget in your app.

---
#### Jetpack Compose Sample (Fastest):

Simple use the Trustbadge Compose function anywhere in your composables. You can also use a Compose `modifier` to modify the widget.
```
Trustbadge(
    badgeContext = TrustbadgeContext.SHOP_GRADE,
    tsid = "X330A2E7D449E31E467D2F53A55DDD070",
    channelId = "chl-b309535d-baa0-40df-a977-0b375379a3cc"
)
```

---
#### Legacy XML Sample (Requires more configuration):
Using the Legacy UI system requires more steps to show the widget:

First, add a `ComposeView` tag in your layout's XML file, for example:
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/hello_world"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Hello Android!" />

    <androidx.compose.ui.platform.ComposeView
        android:id="@+id/compose_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>
```

Then in your source file, inflate the layout and get the `ComposeView` using the XML ID, set a composition strategy that works best for you and call the `setContent()` to use Compose.
Example of loading the Trustbadge in a fragment:
```
override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExampleBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // In Compose world
                Trustbadge(
                    badgeContext = TrustbadgeContext.SHOP_GRADE,
                    tsid = "X330A2E7D449E31E467D2F53A55DDD070",
                    channelId = "chl-b309535d-baa0-40df-a977-0b375379a3cc"
                )
            }
        }
        return view
    }
```
For more information about Compose interoperability with the legacy system, please refer to [Android official docs for Interoperability APIs](https://developer.android.com/jetpack/compose/interop/interop-apis).

---
Your eTrusted `account` might contain more than one `channel`. The `Trustbadge` function requires a `channelId` to determine which channel it should choose to load the information from your `account`.
Please read the section [Getting `channelId` and `TSID`]() for more information about how to get that information.

The `Trustbadge` function requires a `badgeContext` to determine either showing the Trust Mark or the Shop Grade widget.
You can simply pass either of the following options to set your desired `badgeContext`:
- TrustbadgeContext.SHOP_GRADE
- TrustbadgeContext.
- TBD

## Getting `channelId` and `TSID`
- You can find your `channelId` by navigating to your desired channel on [eTrustd Control Center](https://app.etrusted.com) in your browser. You can simply copy the `channelId` from the address bar (starts with `chl-`) for your desired channel as shown in the example image:
<img width="500" src="https://user-images.githubusercontent.com/27926337/215760110-6d00a5ec-3b0c-4458-a867-acf75d6afa8b.png">
- If you don't have a `TSID` (usually shared during the onboarding process with Trusted Shops), You can get your `TSID` by contacting Trsuted Shop via email: members@trustedshops.com

## Support
Please [let us know](https://github.com/trustedshops-public/etrusted-android-trustbadge-library/issues) if you
have suggestions or questions. You may also contact Trusted Shop's mobile engineering team via email: mobileapp@trustedshops.com
