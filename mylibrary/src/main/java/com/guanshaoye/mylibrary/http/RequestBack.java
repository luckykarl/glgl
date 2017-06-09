package com.guanshaoye.mylibrary.http;


public abstract  class RequestBack {

	public abstract void onComplete(FlpBack back);

	public abstract void onFlpException(FlpException e);

	public  void inProgress(float progress){

	 }
}
