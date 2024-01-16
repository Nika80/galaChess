package ru.vsu.cs.karmanova_v_v.model.board;


public enum CellType {
    YELLOW_LIGHT("48;2;255;255;255", "38;2;255;255;255"),
    YELLOW_DARK("48;2;245;204;71", "38;2;245;204;71"),
    ORANGE_LIGHT("48;2;255;255;255", "38;2;255;255;255"),
    ORANGE_DARK("48;2;238;155;76", "38;2;238;155;76");

    private final String code;
    private final String codeFigure;

    CellType(String code, String codeFigure) {
        this.code = code;
        this.codeFigure = codeFigure;
    }
    public String getCode() {
        return code;
    }

    public String getCodeFigure() {
        return codeFigure;
    }

    public boolean isOpposite(CellType other) {
        return (this == YELLOW_LIGHT && other == ORANGE_DARK) ||
                (this == YELLOW_LIGHT && other == ORANGE_LIGHT) ||
                (this == YELLOW_DARK && other == ORANGE_DARK) ||
                (this == YELLOW_DARK && other == ORANGE_LIGHT) ||
                (this == ORANGE_LIGHT && other == YELLOW_LIGHT) ||
                (this == ORANGE_LIGHT && other == YELLOW_DARK) ||
                (this == ORANGE_DARK && other == YELLOW_DARK) ||
                (this == ORANGE_DARK && other == YELLOW_LIGHT);
    }
}
