package testng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetryAnnalyzerImplementation implements IRetryAnalyzer{
	
	int iCount = 0;
	int iMaxCount = 5;

	@Override
	public boolean retry(ITestResult result) {
		System.out.println("Retry Method Name is : "+result.getName());
		if(iCount < iMaxCount) {
			iCount++;
			return true;
		}
		return false;
	}

}
