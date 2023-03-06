package com.coffeepoweredcrew.mediator;

import javafx.scene.control.TextField;

public class TextBox extends TextField implements  UIControl {

	private UIMediator mediator;
	private boolean update;

	public TextBox(UIMediator mediator) {
		this.mediator = mediator;
		this.setText("Textbox");

		this.mediator.register(this);

		this.textProperty().addListener((v, o, n) -> {
			if(!update) {
				this.mediator.valueChanged(this);
			}
		});
	}

	@Override
	public void controlChanged(UIControl control) {
		this.update = true;
		this.setText(control.getControlValue());
		this.update = false;
	}

	@Override
	public String getControlValue() {
		return getText();
	}

	@Override
	public String getControlName() {
		return "TextBox";
	}
}
