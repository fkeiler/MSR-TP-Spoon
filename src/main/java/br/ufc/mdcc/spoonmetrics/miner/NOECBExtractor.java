package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.code.CtComment;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class NOECBExtractor extends AbstractProcessor<CtClass<?>> {
    @Override
    public void process(CtClass<?> ctClass) {
        String qualifiedName = ctClass.getQualifiedName();

        int noecb = 0;
        for (CtCatch c : ctClass.getElements(new TypeFilter<>(CtCatch.class))){
            Boolean isOnlyComments = true;
            for(CtStatement s : c.getBody().getStatements()) {
                if (!(s instanceof CtComment))
                    isOnlyComments = false;
            }
            if (isOnlyComments) noecb++;
        }

        Dataset.store(qualifiedName, new Measure(Metric.NOECB, noecb));
    }
}
