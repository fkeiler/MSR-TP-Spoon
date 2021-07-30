package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

public class DNIFExtractor extends AbstractProcessor<CtClass<?>> {
    @Override
    public void process(CtClass<?> ctClass) {
        String qualifiedName = ctClass.getQualifiedName();

        int maxDepth = 0;
        for (CtMethod<?> m : ctClass.getAllMethods()) {
            for (CtIf i : m.getElements(new TypeFilter<>(CtIf.class))){
                int depth = nestedDepth(i);
                if (depth > maxDepth) maxDepth = depth;
            }
        }

        Dataset.store(qualifiedName, new Measure(Metric.DNIF, maxDepth));
    }

    public int nestedDepth(CtIf i){
        int maxDepth = 0;
        for (CtIf ii : i.getThenStatement().getElements(new TypeFilter<>(CtIf.class))) {
            int depth = nestedDepth(ii);
            if (depth > maxDepth) maxDepth = depth;
        }
        if (i.getElseStatement() != null)
            for (CtIf ii : i.getElseStatement().getElements(new TypeFilter<>(CtIf.class))) {
                int depth = nestedDepth(ii);
                if (depth > maxDepth) maxDepth = depth;
            }

        return maxDepth+1;
    }
}
