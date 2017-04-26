package it.polito.ai.lab3.entities;

public class TestEntity {

	private String text;
	private String ciao;
	
	public TestEntity(){
		this.ciao = "ciaone";
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	public String greet() {
		return ciao;
	}
}
