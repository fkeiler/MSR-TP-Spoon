package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

public class MethodExtractor extends AbstractProcessor<CtClass<?>> {
    @Override
    public void process(CtClass<?> ctClass) {
        if (Util.isValid(ctClass)) {
            String qualifiedName = ctClass.getQualifiedName();

            int nodm = 0,
                nopm = 0,
                noprm = 0;

            for (CtMethod<?> m : ctClass.getMethods()) {
                nodm++;
                if (m.isPrivate())
                    noprm++;
                else if (m.isPublic())
                    nopm++;
            }

            Dataset.store(qualifiedName, new Measure(Metric.NODM, nodm));
            Dataset.store(qualifiedName, new Measure(Metric.NOPM, nopm));
            Dataset.store(qualifiedName, new Measure(Metric.NOPRM, noprm));
        }
    }
}
