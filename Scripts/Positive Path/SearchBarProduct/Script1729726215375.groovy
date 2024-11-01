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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import com.kms.katalon.core.exception.StepErrorException as StepErrorException

// Load test data from Data File
def testData = TestDataFactory.findTestData('Data Files/SearchTermsProduct')

// Open browser and navigate to the website
WebUI.openBrowser('')

WebUI.navigateToUrl('https://automationteststore.com')

// Create test objects
TestObject searchBar = findTestObject('Object Repository/LandingPage/SearchBar')
TestObject noProduct = findTestObject('Object Repository/PageSearch/NoProductText')
	
// Get the current WebDriver
WebDriver driver = DriverFactory.getWebDriver()

// Iterate through the data-driven test data
for (int i = 1; i <= testData.getRowNumbers(); i++) {
    String searchTerm = testData.getValue('SearchTerm', i)

    String expectedResult = testData.getValue('ExpectedResult', i)

    // Enter the search term into the search bar
	WebUI.waitForElementVisible(searchBar, 1)
    boolean isElementPresent = WebUI.verifyElementPresent(searchBar, 1)
	if (!isElementPresent) {
		KeywordUtil.markFailedAndStop('Searchbar not found, terminating test')
	}
	
	WebUI.setText(searchBar, searchTerm)

    // Click the search button
    WebUI.click(findTestObject('Object Repository/LandingPage/SearchButton'))

//    // Wait for search results to load (adjust time as necessary)
//    WebUI.delay(3)
//
//    // Get current URL and validate search result based on expected outcome
//    String pageSource = driver.getPageSource()

    try {
        if (expectedResult == "Relevant products appear") {
            // Attempt to check if no products text is displayed
            boolean isNoText = WebUI.verifyTextNotPresent('no product that matches', false
				, FailureHandling.CONTINUE_ON_FAILURE
				)
            if (isNoText) {
				WebUI.comment('Test passed: Relevant products found for search term: ' + searchTerm)
            }
			else {
				KeywordUtil.markFailed('No relevant products found for: ' + searchTerm)
				WebUI.closeBrowser()
			} 
        } 
		 else if (expectedResult == "No products found") {
            // Attempt to check if no products message is displayed
            boolean isText = WebUI.verifyTextPresent('no product that matches', false
				, FailureHandling.CONTINUE_ON_FAILURE
				)
            if (isText) {
				WebUI.comment('Test passed: No products found message displayed for search term: ' + searchTerm)
            }
			else {
				KeywordUtil.markFailedAndStop('Unexpected products found for: ' + searchTerm)
				WebUI.closeBrowser()
			}
        }
	}
    finally {
    // Clear the search bar for the next iteration
    WebUI.clearText(findTestObject('Object Repository/LandingPage/SearchBar'))
    }
}

// Close the browser after the test
WebUI.closeBrowser()