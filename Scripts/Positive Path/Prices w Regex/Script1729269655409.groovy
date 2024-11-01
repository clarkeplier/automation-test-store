import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import java.util.regex.Matcher as Matcher
import java.util.regex.Pattern as Pattern

// Open the target webpage
WebUI.openBrowser('')

WebUI.navigateToUrl('https://automationteststore.com')

WebDriver driver = DriverFactory.getWebDriver()

String skinsheenPrice = '29.50'
String shockPrice = '14.00'

WebUI.click(findTestObject('Object Repository/LandingPage/SearchBar'))

WebUI.setText(findTestObject('Object Repository/LandingPage/SearchBar'), 'skinsheen')

WebUI.click(findTestObject('Object Repository/LandingPage/SearchButton'))

WebUI.verifyElementText(findTestObject('Object Repository/ProductPageSkinsheenBronzer/PriceSkinsheen'), '$' + skinsheenPrice)

WebUI.click(findTestObject('Object Repository/ProductPageSkinsheenBronzer/AddToCart'))

WebUI.click(findTestObject('Object Repository/ShoppingCart/SearchBar'))

WebUI.setText(findTestObject('Object Repository/ShoppingCart/SearchBar'), 'shock')

WebUI.click(findTestObject('Object Repository/ShoppingCart/SearchButton'))

WebUI.verifyElementText(findTestObject('Object Repository/ProductPageShockDeodorant/PriceShock'), '$' + shockPrice)

WebUI.click(findTestObject('Object Repository/ProductPageShockDeodorant/AddToCart'))

// Extract page source
String pageSource = driver.getPageSource()

// Regular expression pattern for extracting prices (e.g., $xx.xx or similar)
String pricePattern = '\\$\\d+\\.\\d{2}'

// Compile the pattern
Pattern pattern = Pattern.compile(pricePattern)

// Create matcher to find occurrences of the price pattern in the page source
Matcher matcher = pattern.matcher(pageSource)

// Variables to store extracted prices and total
List<Float> prices = []

String previousPriceString = null

int counter = 1

float predictedTotal = Float.parseFloat(skinsheenPrice) + Float.parseFloat(shockPrice)

float total = 0.0 // To hold the last value, assumed to be the displayed total

// Loop to extract and store prices in variables
while (matcher.find()) {
    // Extract price and convert to float (remove "$" symbol first)
    String priceString = matcher.group().replaceAll(/\$/, '')
    
    // Skip the price if it is the same as the previous price (duplicate)
    if (priceString.equals(previousPriceString)) {
        continue
    }
    
    float priceValue = Float.parseFloat(priceString)

        // Dynamically store price in a numbered variable (price1, price2, etc.)
        this."price${counter}" = priceValue
        prices.add(priceValue)
    
        counter++

    // Update previousPriceString to the current one
    previousPriceString = priceString
}

// Output prices and calculated total
for (int i = 0; i < prices.size(); i++) {
    println "Price${i+1}: \$${prices[i]}"
	if(prices[i] == predictedTotal) {
		total+=prices[i]
		break
	}
}

// Use assert statement to verify the calculated total matches the displayed total
assert total == predictedTotal : "Verification FAILED: Matching total not found."

// If the assert passes, print a success message
println "Verification PASSED: Calculated total matches the predicted total."

// Close browser
WebUI.closeBrowser()	