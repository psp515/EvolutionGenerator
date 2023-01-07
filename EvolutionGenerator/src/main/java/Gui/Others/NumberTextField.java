package Gui.Others;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField
{

    @Override
    public void replaceText(int start, int end, String text)
    {
        if (validate(text))
        {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text)
    {
        if (validate(text))
        {
            super.replaceSelection(text);
        }
    }

    public int getValue()
    {
        return Integer.parseInt(this.getText());
    }

    private boolean validate(String text)
    {
        return text.matches("[0-9]*");
    }
}
