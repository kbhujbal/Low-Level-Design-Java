class Application {
    private Button button;
    private TextBox textBox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        textBox = factory.createTextBox();
    }

    public void renderUI() {
        button.render();
        textBox.render();
    }
}
