package tetris.Model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import tetris.Controller.Controller;
import tetris.View.GenerationFigure;

import java.util.ArrayList;

import static tetris.Model.ConstantsModel.*;
import static tetris.View.Constants.*;

public class Model {
    public static int[][] playingField = new int[XMAX / SIZE][YMAX / SIZE];
    private static int linesNo = 0;

    public static GenerationFigure makeRect() {
        int block = (int) (Math.random() * 7);
        String name;
        Rectangle a = new Rectangle(SIZE - OFFSET_LEFT, SIZE - OFFSET_LEFT),
                b = new Rectangle(SIZE - OFFSET_LEFT, SIZE - OFFSET_LEFT),
                c = new Rectangle(SIZE - OFFSET_LEFT, SIZE - OFFSET_LEFT),
                d = new Rectangle(SIZE - OFFSET_LEFT, SIZE - OFFSET_LEFT);

        if (block == FORM_ONE) {
            a.setX(XMAX / HALF - SIZE);
            b.setX(XMAX / HALF - SIZE);
            b.setY(SIZE);
            c.setX(XMAX / HALF);
            c.setY(SIZE);
            d.setX(XMAX / HALF + SIZE);
            d.setY(SIZE);
            name = SHAPE_ONE;
        } else if (block == FORM_TWO) {
            a.setX(XMAX / HALF + SIZE);
            b.setX(XMAX / HALF - SIZE);
            b.setY(SIZE);
            c.setX(XMAX / HALF);
            c.setY(SIZE);
            d.setX(XMAX / HALF + SIZE);
            d.setY(SIZE);
            name = SHAPE_TWO;
        } else if (block == FORM_THREE) {
            a.setX(XMAX / HALF - SIZE);
            b.setX(XMAX / HALF);
            c.setX(XMAX / HALF - SIZE);
            c.setY(SIZE);
            d.setX(XMAX / HALF);
            d.setY(SIZE);
            name = SHAPE_THREE;
        } else if (block == FORM_FOUR) {
            a.setX(XMAX / HALF + SIZE);
            b.setX(XMAX / HALF);
            c.setX(XMAX / HALF);
            c.setY(SIZE);
            d.setX(XMAX / HALF - SIZE);
            d.setY(SIZE);
            name = SHAPE_FOUR;
        } else if (block == FORM_FIVE) {
            a.setX(XMAX / HALF - SIZE);
            b.setX(XMAX / HALF);
            c.setX(XMAX / HALF);
            c.setY(SIZE);
            d.setX(XMAX / HALF + SIZE);
            name = SHAPE_FIVE;
        } else if (block == FORM_SIX) {
            a.setX(XMAX / HALF + SIZE);
            b.setX(XMAX / HALF);
            c.setX(XMAX / HALF + SIZE);
            c.setY(SIZE);
            d.setX(XMAX / HALF + SIZE + SIZE);
            d.setY(SIZE);
            name = SHAPE_SIX;
        } else {
            a.setX(XMAX / HALF - SIZE - SIZE);
            b.setX(XMAX / HALF - SIZE);
            c.setX(XMAX / HALF);
            d.setX(XMAX / HALF + SIZE);
            name = SHAPE_SEVEN;
        }
        return new GenerationFigure(a, b, c, d, name);
    }

    public static void moveRight(GenerationFigure form) {
        try {
            if (form.square1.getX() + MOVE <= XMAX - SIZE && form.square2.getX() + MOVE <= XMAX - SIZE
                    && form.square3.getX() + MOVE <= XMAX - SIZE && form.square4.getX() + MOVE <= XMAX - SIZE) {
                int movea = playingField[((int) form.square1.getX() / SIZE) + OFFSET_RIGHT][((int) form.square1.getY() / SIZE)];
                int moveb = playingField[((int) form.square2.getX() / SIZE) + OFFSET_RIGHT][((int) form.square2.getY() / SIZE)];
                int movec = playingField[((int) form.square3.getX() / SIZE) + OFFSET_RIGHT][((int) form.square3.getY() / SIZE)];
                int moved = playingField[((int) form.square4.getX() / SIZE) + OFFSET_RIGHT][((int) form.square4.getY() / SIZE)];
                if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                    form.square1.setX(form.square1.getX() + MOVE);
                    form.square2.setX(form.square2.getX() + MOVE);
                    form.square3.setX(form.square3.getX() + MOVE);
                    form.square4.setX(form.square4.getX() + MOVE);
                }
            }
        } catch (ArrayIndexOutOfBoundsException error) {
        }
    }

    public static void moveLeft(GenerationFigure form) {
        try {
            if (form.square1.getX() - MOVE >= END_FIELD && form.square2.getX() - MOVE >= END_FIELD &&
                    form.square3.getX() - MOVE >= END_FIELD && form.square4.getY() - MOVE >= END_FIELD) {
                int maovea = playingField[((int) form.square1.getX()) / SIZE - OFFSET_LEFT][(int) form.square1.getY() / SIZE];
                int maoveb = playingField[((int) form.square2.getX() / SIZE) - OFFSET_LEFT][(int) form.square1.getY() / SIZE];
                int maovec = playingField[((int) form.square3.getX() / SIZE) - OFFSET_LEFT][(int) form.square1.getY() / SIZE];
                int maoved = playingField[((int) form.square4.getX() / SIZE) - OFFSET_LEFT][(int) form.square1.getY() / SIZE];
                if (maovea == END_FIELD && maoveb == END_FIELD && maovec == END_FIELD && maoved == END_FIELD) {
                    form.square1.setX(form.square1.getX() - MOVE);
                    form.square2.setX(form.square2.getX() - MOVE);
                    form.square3.setX(form.square3.getX() - MOVE);
                    form.square4.setX(form.square4.getX() - MOVE);
                }
            }
        } catch (ArrayIndexOutOfBoundsException error) {
        }
    }

    public static void moveDown(GenerationFigure form, Pane group) {
        try {
            if (form.square1.getY() == YMAX - SIZE || form.square2.getY() == YMAX - SIZE ||
                    form.square3.getY() == YMAX - SIZE || form.square4.getY() == YMAX - SIZE || moveA(form) || moveB(form)
                    || moveC(form) || moveD(form)) {
                playingField[((int) form.square1.getX()) / SIZE][((int) form.square1.getY()) / SIZE] = NOT_EMPTY_PLACE;
                playingField[((int) form.square2.getX()) / SIZE][((int) form.square2.getY()) / SIZE] = NOT_EMPTY_PLACE;
                playingField[((int) form.square3.getX()) / SIZE][((int) form.square3.getY()) / SIZE] = NOT_EMPTY_PLACE;
                playingField[((int) form.square4.getX()) / SIZE][((int) form.square4.getY()) / SIZE] = NOT_EMPTY_PLACE;
                removeRows(group);
                Controller.generationNewRect();
            }
            if (form.square1.getY() + MOVE < YMAX && form.square2.getY() + MOVE < YMAX && form.square3.getY() + MOVE < YMAX
                    && form.square4.getY() + MOVE < YMAX) {
                int movea = playingField[(int) form.square1.getX() / SIZE][((int) form.square1.getY() / SIZE) + OFFSET_RIGHT];
                int moveb = playingField[(int) form.square2.getX() / SIZE][((int) form.square2.getY() / SIZE) + OFFSET_RIGHT];
                int movec = playingField[(int) form.square3.getX() / SIZE][((int) form.square3.getY() / SIZE) + OFFSET_RIGHT];
                int moved = playingField[(int) form.square4.getX() / SIZE][((int) form.square4.getY() / SIZE) + OFFSET_RIGHT];
                if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                    form.square1.setY(form.square1.getY() + MOVE);
                    form.square2.setY(form.square2.getY() + MOVE);
                    form.square3.setY(form.square3.getY() + MOVE);
                    form.square4.setY(form.square4.getY() + MOVE);
                }
            }
        } catch (ArrayIndexOutOfBoundsException error) {
        }

    }

    private static boolean moveA(GenerationFigure form) {
        return (playingField[(int) form.square1.getX() / SIZE][((int) form.square1.getY() / SIZE) + OFFSET_RIGHT] == NOT_EMPTY_PLACE);
    }

    private static boolean moveB(GenerationFigure form) {
        return (playingField[(int) form.square2.getX() / SIZE][((int) form.square2.getY() / SIZE) + OFFSET_RIGHT] == NOT_EMPTY_PLACE);
    }

    private static boolean moveC(GenerationFigure form) {
        return (playingField[(int) form.square3.getX() / SIZE][((int) form.square3.getY() / SIZE) + OFFSET_RIGHT] == NOT_EMPTY_PLACE);
    }

    private static boolean moveD(GenerationFigure form) {
        return (playingField[(int) form.square4.getX() / SIZE][((int) form.square4.getY() / SIZE) + OFFSET_RIGHT] == NOT_EMPTY_PLACE);
    }

    private static void removeRows(Pane pane) {
        ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Rectangle> newrects = new ArrayList<Rectangle>();
        int full = 0;
        for (int i = 0; i < playingField[0].length; i++) {
            for (int j = 0; j < playingField.length; j++) {
                if (playingField[j][i] == 1)
                    full++;
            }
            if (full == playingField.length)
                lines.add(i);
            full = 0;
        }
        while (lines.size() > 0) {
            for (int i = 0; i < pane.getChildren().size(); ++i) {
                if (pane.getChildren().get(i) instanceof Rectangle) {
                    rects.add((Rectangle) pane.getChildren().get(i));
                }
            }
            linesNo++;

            for (Rectangle node : rects) {
                Rectangle a = node;
                if (a.getY() == lines.get(0) * SIZE) {
                    playingField[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                    pane.getChildren().remove(node);
                } else
                    newrects.add(node);
            }

            for (Rectangle node : newrects) {
                Rectangle a = node;
                if (a.getY() < lines.get(0) * SIZE) {
                    playingField[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                    a.setY(a.getY() + SIZE);
                }
            }
            lines.remove(0);
            rects.clear();
            newrects.clear();
            for (int i = 0; i < pane.getChildren().size(); ++i) {
                if (pane.getChildren().get(i) instanceof Rectangle) {
                    rects.add((Rectangle) pane.getChildren().get(i));
                }
            }
            for (Rectangle node : rects) {
                Rectangle a = node;
                try {
                    playingField[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
            rects.clear();
        }
    }

    public static int getLine() {
        return linesNo;
    }

    public static boolean controllingTurn(Rectangle rect, int x, int y) {
        boolean xb = false;
        boolean yb = false;
        if (x >= 0)
            xb = rect.getX() + x * MOVE <= XMAX - SIZE;
        if (x < 0)
            xb = rect.getX() + x * MOVE >= 0;
        if (y >= 0)
            yb = rect.getY() - y * MOVE > 0;
        if (y < 0)
            yb = rect.getY() + y * MOVE < YMAX;
        return xb && yb && playingField[((int) rect.getX() / SIZE) + x][((int) rect.getY() / SIZE) - y] == 0;
    }

    public static int getLinesNo() {
        return linesNo;
    }
}
