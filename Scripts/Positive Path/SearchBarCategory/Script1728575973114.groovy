import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import org.openqa.selenium.By as By
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// Load test data from Data File
def testData = TestDataFactory.findTestData('Data Files/SearchCategory') // Adjust the file path to your data source

// Open browser and navigate to the website
WebUI.openBrowser('')
WebUI.navigateToUrl('https://automationteststore.com') // Replace with your actual website URL

// Get the current WebDriver
WebDriver driver = DriverFactory.getWebDriver()

// Iterate through the data-driven test data
for (int i = 1; i <= testData.getRowNumbers(); i++) {
    String searchTerm = testData.getValue('SearchTerm', i)
	String testCategory = testData.getValue('Category', i)
    String expectedResult = testData.getValue('ExpectedResult', i)
    
    // Enter the search term into the search bar
    WebUI.click(findTestObject('Object Repository/LandingPage/SearchBar'))
    
    // Click the search button
    WebUI.click(findTestObject('Object Repository/LandingPage/Categories/' + testCategory))
    
    // Wait for search results to load (adjust time as necessary)
    WebUI.delay(3)
    
    // Get current URL and validate search result based on expected outcome
    String pageSource = driver.getPageSource()
    
    if (expectedResult == "Relevant products appear") {
        // Check if the search term appears in the search results (assume it appears in product titles or descriptions)
        if (pageSource.contains(searchTerm)) {
            WebUI.comment('Test passed: Relevant products found for search term: ' + searchTerm)
        } else {
            WebUI.comment('Test failed: No relevant products found for search term: ' + searchTerm)
        }
    } else if (expectedResult == "No products found") {
        // Check if a "no product that matches" message is displayed
        if (pageSource.contains('no product that matches')) {
            WebUI.comment('Test passed: No products found message displayed for search term: ' + searchTerm)
        } else {
            WebUI.comment('Test failed: Products were found unexpectedly for search term: ' + searchTerm)
        }
    }
    
    // Optionally clear the search bar for the next iteration
    WebUI.clearText(findTestObject('Object Repository/LandingPage/SearchBar'))
}

// Close the browser after the test
WebUI.closeBrowser()
