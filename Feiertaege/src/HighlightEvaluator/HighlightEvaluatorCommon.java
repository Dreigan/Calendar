package HighlightEvaluator;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.toedter.calendar.IDateEvaluator;

public   class HighlightEvaluatorCommon implements IDateEvaluator {

	private final List<Date> list = new ArrayList<>();

    public void add(Date date) {
        list.add(date);
    }

    @Override
    public boolean isSpecial(Date date) {
        return list.contains(date);
    }

    @Override
    public Color getSpecialForegroundColor() {
        return Color.red.darker();
    }

    @Override
    public Color getSpecialBackroundColor() {
        return Color.GREEN;
    }


    @Override
    public String getSpecialTooltip() {
        return  "Koinh Argia";
    }

    @Override
    public boolean isInvalid(Date date) {
        return false;
    }

    @Override
    public Color getInvalidForegroundColor() {
        return Color.red.darker();
    }

    @Override
    public Color getInvalidBackroundColor() {
        return null;
    }

    @Override
    public String getInvalidTooltip() {
        return null;
    }
}