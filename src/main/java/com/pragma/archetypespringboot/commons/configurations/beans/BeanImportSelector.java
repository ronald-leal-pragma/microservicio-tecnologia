package com.pragma.archetypespringboot.commons.configurations.beans;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.logging.Logger;

import static com.pragma.archetypespringboot.ArchetypeSpringbootApplication.*;

public class BeanImportSelector implements ImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {

    String[] useCaseClasses = ScannerClasses.scannerClasses(USE_CASE_ROUTE);
    String[] serviceClasses = ScannerClasses.scannerClasses(SERVICE_ROUTE);
    String[] adapterClasses = ScannerClasses.scannerClasses(ADAPTER_ROUTE);

    String[] totalScanner = Arrays.copyOf(useCaseClasses, useCaseClasses.length + serviceClasses.length + adapterClasses.length);
    System.arraycopy(serviceClasses, 0, totalScanner, useCaseClasses.length, serviceClasses.length);
    System.arraycopy(adapterClasses, 0, totalScanner, useCaseClasses.length + serviceClasses.length, adapterClasses.length);

    Logger.getLogger(BeanImportSelector.class.getName()).info("Imported Beans: " + StringUtils.arrayToCommaDelimitedString(totalScanner));

    return totalScanner;
  }

}
