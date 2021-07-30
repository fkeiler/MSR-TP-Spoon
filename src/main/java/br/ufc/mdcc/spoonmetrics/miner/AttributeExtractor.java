package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtEnumValue;
import spoon.reflect.declaration.CtField;

public class AttributeExtractor extends AbstractProcessor<CtClass<?>> {
    @Override
    public void process(CtClass<?> ctClass) {
        if (Util.isValid(ctClass)) {
            String qualifiedName = ctClass.getQualifiedName();

            int noda = 0,
                nopa = 0,
                nopra = 0;

            for (CtField<?> f : ctClass.getFields()){
                if (!(f instanceof CtEnumValue)) {
                    noda++;
                    if (f.isPrivate())
                        nopra++;
                    else if (f.isPublic())
                        nopa++;
                }
            }

            Dataset.store(qualifiedName, new Measure(Metric.NODA, noda));
            Dataset.store(qualifiedName, new Measure(Metric.NOPA, nopa));
            Dataset.store(qualifiedName, new Measure(Metric.NOPRA, nopra));
        }
    }
}
