package com.pragma.archetypespringboot.user.infrastructure.configurations.beans;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.logging.Logger;

import static com.pragma.archetypespringboot.user.infrastructure.configurations.beans.ScannerClasses.scannerClasses;

public class BeanImportSelector implements ImportSelector {

  public static final String USE_CASE_ROUTE = "com.pragma.archetypespringboot.user.domain.usecases";
  public static final String SERVICE_ROUTE = "com.pragma.archetypespringboot.user.application.services.impl";
  public static final String ADAPTER_ROUTE = "com.pragma.archetypespringboot.user.infrastructure.adapters";

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {

    String[] useCaseClasses = scannerClasses(USE_CASE_ROUTE);
    String[] serviceClasses = scannerClasses(SERVICE_ROUTE);
    String[] adapterClasses = scannerClasses(ADAPTER_ROUTE);

    String[] totalScanner = Arrays.copyOf(useCaseClasses, useCaseClasses.length + serviceClasses.length + adapterClasses.length);
    System.arraycopy(serviceClasses, 0, totalScanner, useCaseClasses.length, serviceClasses.length);
    System.arraycopy(adapterClasses, 0, totalScanner, useCaseClasses.length + serviceClasses.length, adapterClasses.length);

    Logger.getLogger(BeanImportSelector.class.getName()).info("Imported Beans: " + StringUtils.arrayToCommaDelimitedString(totalScanner));

    return totalScanner;
  }

}
