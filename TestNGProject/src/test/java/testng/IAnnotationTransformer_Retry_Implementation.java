package testng;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class IAnnotationTransformer_Retry_Implementation implements IAnnotationTransformer {
	
	  public void transform(
		      ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		  
		  annotation.setRetryAnalyzer(testng.IRetryAnnalyzerImplementation.class);
	  }
	
}
