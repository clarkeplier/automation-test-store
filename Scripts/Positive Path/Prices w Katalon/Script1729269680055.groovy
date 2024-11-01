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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://automationteststore.com/')

String skinsheenPrice = '$29.50'

String shockPrice = '$14.00'

String subtotal = '$43.50'

String total = '$45.50'

WebUI.click(findTestObject('Object Repository/LandingPage/SearchBar'))

WebUI.setText(findTestObject('Object Repository/LandingPage/SearchBar'), 'skinsheen')

WebUI.click(findTestObject('Object Repository/LandingPage/SearchButton'))

WebUI.verifyElementText(findTestObject('Object Repository/ProductPageSkinsheenBronzer/PriceSkinsheen'), skinsheenPrice)

WebUI.click(findTestObject('Object Repository/ProductPageSkinsheenBronzer/AddToCart'))

WebUI.setText(findTestObject('Object Repository/ShoppingCart/SearchBar'), '')

WebUI.click(findTestObject('Object Repository/ShoppingCart/SearchBar'))

WebUI.setText(findTestObject('Object Repository/ShoppingCart/SearchBar'), 'shock')

WebUI.click(findTestObject('Object Repository/ShoppingCart/SearchButton'))

WebUI.verifyElementText(findTestObject('Object Repository/ProductPageShockDeodorant/PriceShock'), shockPrice)

WebUI.click(findTestObject('Object Repository/ProductPageShockDeodorant/AddToCart'))

WebUI.verifyElementText(findTestObject('Object Repository/ShoppingCart/SubTotal'), subtotal)

WebUI.verifyElementText(findTestObject('Object Repository/ShoppingCart/FinalTotal'), total)

WebUI.verifyElementText(findTestObject('Object Repository/ShoppingCart/ProductPrice1'), skinsheenPrice)

WebUI.verifyElementText(findTestObject('Object Repository/ShoppingCart/ProductPrice2'), shockPrice)

WebUI.closeBrowser()

