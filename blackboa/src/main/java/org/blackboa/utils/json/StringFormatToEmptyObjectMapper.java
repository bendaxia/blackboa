package org.blackboa.utils.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class StringFormatToEmptyObjectMapper extends ObjectMapper {
	
	private static final long serialVersionUID = 1496261841726671595L;

	public StringFormatToEmptyObjectMapper() {
		super();
		this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
		this.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()  
        {  
            @Override  
            public void serialize(  
                    Object value,  
                    JsonGenerator jg,  
                    SerializerProvider sp) throws IOException, JsonProcessingException  
            {  
                jg.writeString("");  
            }  
        });
	
	}
	
	

}
