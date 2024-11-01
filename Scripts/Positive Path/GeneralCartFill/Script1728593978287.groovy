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

WebUI.openBrowser('')
WebUI.navigateToUrl('https://automationteststore.com/')
// Verification statement
String successText = "YOUR ORDER HAS BEEN PROCESSED!"
// Get the current WebDriver
WebDriver driver = DriverFactory.getWebDriver()
// Test steps
WebUI.click(findTestObject('Object Repository/LandingPage/Featured01'))

WebUI.click(findTestObject('Object Repository/LandingPage/Featured04'))

WebUI.click(findTestObject('Object Repository/ShoppingCart'))
// United Kingdom
WebUI.selectOptionByValue(findTestObject('Object Repository/ShoppingCart/CountrySelector'),
	'222', true)
// Aberdeen
WebUI.selectOptionByValue(findTestObject('Object Repository/ShoppingCart/StateSelector'), 
    '3513', true)

WebUI.setText(findTestObject('Object Repository/ShoppingCart/InputZIP'), '12345')

WebUI.click(findTestObject('Object Repository/ShoppingCart/Checkout'))

WebUI.click(findTestObject('Object Repository/AccountLogin/GuestCheckoutSelect'))

WebUI.click(findTestObject('Object Repository/AccountLogin/ContinueButton'))
// Fill shipping info form
WebUI.click(findTestObject('Object Repository/GuestCheckout/FirstNameTextBox'))

WebUI.setText(findTestObject('Object Repository/GuestCheckout/FirstNameTextBox'), 'Jane')

WebUI.setText(findTestObject('Object Repository/GuestCheckout/LastNameTextBox'), 'Doe')

WebUI.setText(findTestObject('Object Repository/GuestCheckout/EmailTextBox'), 'janedoe@janedoe.com')

WebUI.setText(findTestObject('Object Repository/GuestCheckout/Address1TextBox'), '123 Fake St')

WebUI.setText(findTestObject('Object Repository/GuestCheckout/CityTextBox'), 'Aberdeen')

WebUI.selectOptionByValue(findTestObject('Object Repository/GuestCheckout/StateSelector'), 
    '3513', true)

WebUI.setText(findTestObject('Object Repository/GuestCheckout/ZIPCodeTextBox'), '12345')

WebUI.click(findTestObject('Object Repository/GuestCheckout/ContinueButton'))

WebUI.click(findTestObject('Object Repository/CheckoutConfirmation/ConfirmOrderButton'))
// Verify order is complete
WebUI.verifyElementText(findTestObject('Object Repository/OrderProcessed/ConfirmationText'), successText)
// Close the browser
WebUI.closeBrowser()

