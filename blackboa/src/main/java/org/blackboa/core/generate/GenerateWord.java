package org.blackboa.core.generate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GenerateWord {
	
	@Value("${generate.path}")
	private String path;
	
	
}
