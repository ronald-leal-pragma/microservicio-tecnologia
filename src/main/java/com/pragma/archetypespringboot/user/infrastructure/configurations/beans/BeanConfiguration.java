package com.pragma.archetypespringboot.user.infrastructure.configurations.beans;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(BeanImportSelector.class)
public class BeanConfiguration {


}
