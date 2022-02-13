package testng;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class IAnnotationTransformerImplementation implements IAnnotationTransformer {
	
	public void transform(
		      ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		String testcase = testMethod.getName();
		
		if(testcase.equals("windowSettings")) {
			annotation.setPriority(1);
		}else if(testcase.equals("navigateURL")) {
			annotation.setPriority(2);
			annotation.setEnabled(true);
		}else if(testcase.equals("getPageInfo")) {
			annotation.setPriority(3);
		}else if(testcase.equals("searchProduct")) {
			annotation.setPriority(4);
		}
		
		
		  }

}
