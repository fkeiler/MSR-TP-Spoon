package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtFor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

public class DNFORExtractor extends AbstractProcessor<CtClass<?>> {
    @Override
    public void process(CtClass<?> ctClass) {
        if (Util.isValid(ctClass)) {
            String qualifiedName = ctClass.getQualifiedName();
            int maxDepth = 0;

            for (CtMethod m : ctClass.getAllMethods()) {
                for (CtFor f : m.getElements(new TypeFilter<>(CtFor.class))) {
                    int depth = getMaxDepth(f);
                    if (depth > maxDepth) maxDepth = depth;
                }
            }

            Dataset.store(qualifiedName, new Measure(Metric.DNFOR, maxDepth));
        }
    }

    public int getMaxDepth(CtFor f) {
        int maxDepth = 0;

        for (CtFor ff : f.getBody().getElements(new TypeFilter<>(CtFor.class))) {
            int depth = getMaxDepth(ff);
            if (depth > maxDepth) maxDepth = depth;
        }

        return maxDepth + 1;
    }
}
