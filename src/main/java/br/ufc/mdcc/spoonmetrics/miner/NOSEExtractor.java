package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

public class NOSEExtractor extends AbstractProcessor<CtClass<?>> {
    @Override
    public void process(CtClass<?> ctClass) {
        if (Util.isValid(ctClass)) {
            String qualifiedName = ctClass.getQualifiedName();
            int nose = 0;

            for (CtMethod m : ctClass.getAllMethods())
                nose += m.getThrownTypes().size();

            Dataset.store(qualifiedName, new Measure(Metric.NOSE, nose));
        }
    }
}
