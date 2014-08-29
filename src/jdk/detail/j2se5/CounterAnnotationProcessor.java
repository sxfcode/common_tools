package jdk.detail.j2se5;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
@SupportedAnnotationTypes("jdk.j2se5.CounterAnnotation")
public class CounterAnnotationProcessor extends AbstractProcessor {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean process(Set<? extends TypeElement> elementSet,
			RoundEnvironment re) {
		Iterator it =elementSet.iterator();
		while (it.hasNext()) {
			TypeElement t = (TypeElement)it.next();
			System.out.println("name:"+t.getQualifiedName());
		}
		return false;
	}

}
