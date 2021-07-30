package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtThrow;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

public class NOREExtractor extends AbstractProcessor<CtClass<?>> {
    @Override
    public void process(CtClass<?> ctClass) {
        if (Util.isValid(ctClass)){
            String qualifiedName = ctClass.getQualifiedName();
            int nore = 0;

            for (CtMethod m : ctClass.getAllMethods())
                nore += m.getElements(new TypeFilter<>(CtThrow.class)).size();

            Dataset.store(qualifiedName, new Measure(Metric.NORE, nore));
        }
    }
}
