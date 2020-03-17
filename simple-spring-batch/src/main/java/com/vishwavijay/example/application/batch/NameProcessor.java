package com.vishwavijay.example.application.batch;

import org.springframework.batch.item.ItemProcessor;

public class NameProcessor implements ItemProcessor<String, String> {

	@Override
	public String process(String input) throws Exception {
		return String.format("Processed %s", input);
	}

}
