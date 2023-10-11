package com.pragma.archetypespringboot.generalconfigurations.beans;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(BeanImportSelector.class)
public class BeanConfiguration {


}
