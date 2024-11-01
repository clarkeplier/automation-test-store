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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('https://automationteststore.com')

CustomKeywords.'automationTestStore.SiteAdmin.logUserIn'()

String successText = 'YOUR ORDER HAS BEEN PROCESSED!'
String siteIndex = '43'
TestObject radioButton = findTestObject('setupteardown/RadioButton')
TestObject outOfStock = findTestObject('setupteardown/OutOfStockButton')

// Test steps
WebUI.navigateToUrl('https://automationteststore.com/index.php?rt=product/category&path=' + siteIndex)

TestObject MyTestObject = findTestObject('setupteardown/Item1', [('index') : '/div[3]/div[3]/div/div/a'])

println MyTestObject.getSelectorCollection().toString()
//WebUI.verifyElementPresent(radioButton, 3, FailureHandling.OPTIONAL)
//
//WebUI.click(radioButton)

WebUI.click(MyTestObject)

if (WebUI.verifyElementPresent(outOfStock, 3, FailureHandling.OPTIONAL)) {
	
	WebUI.comment('Item Out of Stock. Moving to next Item')
	
	KeywordUtil.markPassed('Test Passed: Out Of Stock')
	
	CustomKeywords.'automationTestStore.SiteAdmin.logUserOutAndClose'()
	}
else {
	WebUI.click(findTestObject('Object Repository/AddToCart'))

	WebUI.click(findTestObject('Object Repository/ShoppingCart'))

	WebUI.verifyElementVisible(findTestObject('Object Repository/ShoppingCart/Checkout'))

	WebUI.click(findTestObject('Object Repository/ShoppingCart/Checkout'))

	WebUI.waitForElementVisible(findTestObject('Object Repository/CheckoutConfirmation/ConfirmOrderButton'), 3)

	WebUI.click(findTestObject('Object Repository/CheckoutConfirmation/ConfirmOrderButton'))

	WebUI.verifyTextPresent(successText, false)

	CustomKeywords.'automationTestStore.SiteAdmin.logUserOutAndClose'()
}
