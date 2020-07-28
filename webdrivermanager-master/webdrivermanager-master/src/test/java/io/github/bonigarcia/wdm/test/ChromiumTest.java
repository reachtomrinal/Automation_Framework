/*
 * (C) Copyright 2020 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia.wdm.test;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;
import static org.junit.Assume.assumeTrue;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.base.BrowserTestParent;

/**
 * Test with Google Chromium browser.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 3.8.0
 */
public class ChromiumTest extends BrowserTestParent {

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromiumdriver().setup();
    }

    @Before
    public void setupTest() {
        File chromium = new File(getChromiumBinary());
        assumeTrue(chromium.exists());

        ChromeOptions options = new ChromeOptions();
        options.setBinary(chromium);
        driver = new ChromeDriver(options);
    }

    private String getChromiumBinary() {
        if (IS_OS_WINDOWS) {
            String localAppDat = System.getenv("LOCALAPPDATA")
                    .replaceAll("\\\\", "\\\\\\\\");
            return localAppDat + "\\Chromium\\Application\\chrome.exe";
        } else {
            return IS_OS_MAC
                    ? "/Applications/Chromium.app/Contents/MacOS/Chromium"
                    : "/usr/bin/chromium-browser";
        }
    }

}
