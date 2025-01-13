package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import java.net.URL;
import java.time.Duration;

public class Driver {
    private Driver() {
    }

    public static AppiumDriver Android;
    public static AppiumDriver iOS;

    public static AppiumDriver getAndroidDriver() {
        if (Android == null) {
            try {
                UiAutomator2Options options = new UiAutomator2Options()
                        .setPlatformName(ConfigurationReader.getProperty("android.platformName"))
                        .setAutomationName(ConfigurationReader.getProperty("android.automationName"))
                        .setPlatformVersion(ConfigurationReader.getProperty("android.platformVersion"))
                        .setDeviceName(ConfigurationReader.getProperty("android.deviceName"))
                        .setUdid(ConfigurationReader.getProperty("android.udid"))
                        .setAppPackage(ConfigurationReader.getProperty("android.appPackage"))
                        .setAppActivity(ConfigurationReader.getProperty("android.appActivity"))
                        .setAutoGrantPermissions(true);
                //                        .setAppPackage("com.yolcu360")
//                        .setAppActivity("com.yolcu360.mobile.MainActivity")

                Android = new AppiumDriver(new URL(ConfigurationReader.getProperty("appium.server.url")), options);

            } catch (Exception e) {
                System.err.println("Android driver oluşturma hatası: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return Android;
    }

    public static AppiumDriver getIOSDriver() {
        if (iOS == null) {
            try {
                XCUITestOptions options = new XCUITestOptions()
                        .setPlatformName(ConfigurationReader.getProperty("ios.platformName"))
                        .setAutomationName(ConfigurationReader.getProperty("ios.automationName"))
                        .setPlatformVersion(ConfigurationReader.getProperty("ios.platformVersion"))
                        .setDeviceName(ConfigurationReader.getProperty("ios.deviceName"))
                        .setUdid(ConfigurationReader.getProperty("ios.udid"))
                        .setBundleId(ConfigurationReader.getProperty("ios.bundleId"))
                        .setNoReset(true)
                        .setAutoAcceptAlerts(true);

                iOS = new AppiumDriver(new URL("http://127.0.0.1:4723"), options);
            } catch (Exception e) {
                System.err.println("iOS driver oluşturma hatası: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return iOS;
    }

    public static WebDriver getCurrentDriver() {
        try {
            if ("Android".equals(OS.OS)) {
                return Android;
            } else if ("iOS".equals(OS.OS)) {
                return iOS;
            } else {
                throw new IllegalStateException("Desteklenmeyen işletim sistemi: " + OS.OS);
            }
        } catch (Exception e) {
            System.err.println("getCurrentDriver hatası: " + e.getMessage());
            return null;
        }
    }

    public static void closeDriver() {
        try {
            if (iOS != null) {
                iOS.quit();
                iOS = null;
            }
        } catch (Exception e) {
            System.err.println("iOS driver kapatma hatası: " + e.getMessage());
        }

        try {
            if (Android != null) {
                Android.quit();
                Android = null;
            }
        } catch (Exception e) {
            System.err.println("Android driver kapatma hatası: " + e.getMessage());
        }
    }
}