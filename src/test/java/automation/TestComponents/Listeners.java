package automation.TestComponents;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import automation.resources.ExtentReportNG;

public class Listeners implements ITestListener {

	ExtentReports extent = ExtentReportNG.getReportObject();
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test passed.");
	}
	  
	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test failed.");
		test.fail(result.getThrowable());
		//Take Screenshot, attach it the report
		
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}
}
