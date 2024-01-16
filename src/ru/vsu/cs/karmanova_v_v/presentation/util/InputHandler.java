package ru.vsu.cs.karmanova_v_v.presentation.util;

import ru.vsu.cs.karmanova_v_v.model.board.Coordinate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {

    private final static Pattern validMove = Pattern.compile("([a-jA-J][1-9])(-)([a-jA-J][1-9])");

	public InputHandler(){
	}

    public Coordinate parse(String val){
        int x = Character.toLowerCase(val.charAt(0));
        int y = Integer.parseInt(String.valueOf(val.charAt(1)));

        return new Coordinate((char) x, y);
    }

    public Coordinate getFrom(String val){
        Matcher matcher = validMove.matcher(val);
        matcher.matches();
        String coords = matcher.group(1);

        return parse(coords);
    }

    public Coordinate getTo(String val){
        Matcher matcher = validMove.matcher(val);
        matcher.matches();
        String coords =  matcher.group(3);

        return parse(coords);
    }

    public boolean isValid(String val){
        Matcher matcher = validMove.matcher(val);

        return matcher.matches();
    }
}
