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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

/**
* This helper test case tests that the item in the TESTITEM variable can be purchased using the normal 
* website checkout flow or gives a warning if the item is out of stock. If the item doesn't exist, it
* is assumed that the previous item was the last item and raises a flag to assist with loops
* 
* siteIndex = string representing the final digits of the address of the webpage under test
*/
String successText = 'YOUR ORDER HAS BEEN PROCESSED!'

// Test object variables
TestObject purchaseItem = findTestObject('setupteardown/Item1', [('index') : testItem])

TestObject outOfStock = findTestObject('setupteardown/OutOfStockButton')

TestObject radioButton = findTestObject('setupteardown/RadioButton')

TestObject addToCart = findTestObject('Object Repository/AddToCart')

// If the user isn't logged in, log them in
boolean userLoggedIn = WebUI.verifyElementNotPresent(findTestObject('LandingPage/LoginLink'), 3, FailureHandling.OPTIONAL)

if (!userLoggedIn) {
	KeywordUtil.markPassed('Logging in user...')
	
    CustomKeywords.'automationTestStore.SiteAdmin.logUserIn'()
}

// Navigate to the webpage under test based on which site index has been passed on call
WebUI.navigateToUrl('https://automationteststore.com/index.php?rt=product/category&path=' + siteIndex)

if (WebUI.verifyElementPresent(purchaseItem, 3, FailureHandling.OPTIONAL)) {
    WebUI.click(purchaseItem)

    // warning if item out of stock
    boolean inStock = WebUI.verifyElementNotPresent(outOfStock, 3, FailureHandling.OPTIONAL)

    if (!(inStock)) {
        WebUI.comment('Item Out of Stock. Moving to next Item')

        GlobalVariable.RESULT = 'Warning. Item out of stock.'

        KeywordUtil.markWarning('Out of Stock')

        CustomKeywords.'automationTestStore.SiteAdmin.logUserOut'()

        return null
    }
    
    boolean noButton = WebUI.verifyElementNotPresent(radioButton, 3, FailureHandling.OPTIONAL)

    if (!(noButton)) {
        WebUI.click(radioButton)
    }
    
    WebUI.verifyElementPresent(addToCart, 3, FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Object Repository/AddToCart'))

    WebUI.click(findTestObject('Object Repository/ShoppingCart'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/ShoppingCart/Checkout'))

    WebUI.click(findTestObject('Object Repository/ShoppingCart/Checkout'))

    WebUI.waitForElementVisible(findTestObject('Object Repository/CheckoutConfirmation/ConfirmOrderButton'), 3)

    WebUI.click(findTestObject('Object Repository/CheckoutConfirmation/ConfirmOrderButton'))

    // Check the item was successfully purchased
    if (WebUI.verifyTextPresent(successText, false, FailureHandling.CONTINUE_ON_FAILURE)) {
        
		GlobalVariable.RESULT = 'Passed. Item purchased.'

        CustomKeywords.'automationTestStore.SiteAdmin.logUserOut'()

        return null
		
    } else {
        GlobalVariable.RESULT = 'Checkout Failed. Item not purchased.'

        CustomKeywords.'automationTestStore.SiteAdmin.logUserOut'()

        return null
    }
    // End the loop since there are no more items 
} else {
    GlobalVariable.FLAG = true
	
    GlobalVariable.RESULT = 'No more items'

    KeywordUtil.markPassed('No more items')

    CustomKeywords.'automationTestStore.SiteAdmin.logUserOut'()

    return null
}

