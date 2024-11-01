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
import internal.GlobalVariable as Global
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import org.openqa.selenium.By as By
import internal.GlobalVariable as GlobalVariable

// 
Global.FLAG = false

def pageInfo = TestDataFactory.findTestData('Data Files/SiteIndexLog')

def testItem = TestDataFactory.findTestData('Data Files/TestItems')

for (int i = 1; i <= pageInfo.getRowNumbers(); i++) {
	
	String pageNo = pageInfo.getValue('pageNumber', i)
	
	String itemType = pageInfo.getValue('itemType', i)

	for (int j = 1; (j <= testItem.getRowNumbers()); j++) {
	
		if (Global.FLAG) {
			
		break
		
		}
		
		Global.RESULT = 'Failed. Item not purchased.'

		WebUI.callTestCase(findTestCase('Positive Path/HelperCase'), ([('siteIndex') : pageNo, ('itemType') : itemType ]), FailureHandling.CONTINUE_ON_FAILURE)

		String iteration = j.toString()
	
		WebUI.comment('Test Result For Item ' + iteration + ': ' + Global.RESULT)
	}
}
