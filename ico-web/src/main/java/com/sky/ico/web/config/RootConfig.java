package com.sky.ico.web.config;

import org.springframework.context.annotation.Configuration;
import java.util.regex.Pattern;

import org.springframework.context.annotation.ImportResource;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;

import com.sky.ico.web.config.RootConfig.WebPackage;

@Configuration
@ImportResource("classpath:application.xml")
@ComponentScan(basePackages = {"com.sky"},
		excludeFilters = {
				@Filter(type = FilterType.CUSTOM, value = WebPackage.class)
		}
)
public class RootConfig {
	public static class WebPackage extends RegexPatternTypeFilter {
	    public WebPackage() {
	      super(Pattern.compile("com\\.sky\\.ico\\.web"));
	    }
	  }
}
