package ru.vsu.cs.karmanova_v_v.model.figures;


public enum FigureType {
    WHITE("38;2;170;189;219"),
    BLACK("38;2;0;0;0");

    private final String code;

    FigureType(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

}
