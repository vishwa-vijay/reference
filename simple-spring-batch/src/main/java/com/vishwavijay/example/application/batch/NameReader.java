package com.vishwavijay.example.application.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class NameReader implements ItemReader<String> {

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return Long.toString(System.currentTimeMillis());
	}

}
