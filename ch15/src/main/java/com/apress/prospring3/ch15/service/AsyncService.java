package com.apress.prospring3.ch15.service;

import java.util.concurrent.Future;

public interface AsyncService {

	public void asyncTask();

	public Future<String> asyncWithReturn(String name);

}
