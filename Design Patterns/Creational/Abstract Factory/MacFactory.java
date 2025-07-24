class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public TextBox createTextBox() {
        return new MacTextBox();
    }
}