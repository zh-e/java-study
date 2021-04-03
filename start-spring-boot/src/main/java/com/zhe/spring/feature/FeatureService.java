package com.zhe.spring.feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeatureService {

    @Autowired(required = false)
    private List<Feature> features;

    private Map<Class, Feature> featuresByCtxClass;


    @PostConstruct
    public void init() {
        initFeatures();
    }

    private void initFeatures() {
        featuresByCtxClass = new HashMap<>();

        if (features == null) {
            return;
        }

        for (Feature feature : features) {
            if (feature == null) {
                continue;
            }

            Class featureCtxClass = featureCtxClass(feature);
            if (featureCtxClass == null) {
                continue;
            }

            featuresByCtxClass.put(featureCtxClass, feature);
        }
    }

    private static Class<?> featureCtxClass(Feature feature) {

        final String featureTypeName = Feature.class.getTypeName();

        Type itf = null;
        Type[] itfs = feature.getClass().getGenericInterfaces();
        for (Type itf1 : itfs) {
            if (itf1.getTypeName().startsWith(featureTypeName)) {
                itf = itf1;
                break;
            }
        }

        String paramTypeName = itf.getTypeName();
        paramTypeName = paramTypeName.substring(
                paramTypeName.indexOf('<') + 1, paramTypeName.length() - 1);

        Class paramType = null;
        try {
            paramType = Class.forName(paramTypeName);
        } catch (ClassNotFoundException e) {
            System.out.println("featureCtxClass fail" + e);
        }

        return paramType;
    }



    public <C extends Feature.Context> void filter(C ctx) {
        filter(ctx, false);
    }

    @SuppressWarnings("unchecked")
    public <C extends Feature.Context> void filter(C ctx, boolean throwException) {
        if (ctx == null) {
            return;
        }

        Feature<C> feature = featuresByCtxClass.get(ctx.getClass());
        if (feature == null) {
            return;
        }

        try {
            feature.filter(ctx);

        } catch (Throwable e) {
            if (throwException) {
                throw e;
            }else {
                System.out.println("featureFilter fail" + e);
            }
        }
    }

}
