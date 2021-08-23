package ru.ikusov.training.itnan.shapes;

import ru.ikusov.training.itnan.shapes.model.Box;
import ru.ikusov.training.itnan.shapes.model.Shape;
import ru.ikusov.training.itnan.shapes.service.Announcer;
import ru.ikusov.training.itnan.shapes.service.ShapeFactory;
import ru.ikusov.training.utils.Console;

import static ru.ikusov.training.utils.MyMath.r;
import static java.lang.System.*;

public class MainClass {
    public static void main(String... kljhfseroiuhgrs) throws InterruptedException {
        final double MAX_VOLUME = 500;

        ShapeFactory sf = ShapeFactory.getInstance();

        Box box = new Box(MAX_VOLUME);

        while (box.getFullness() < 0.99) {
            Shape shape = sf.createRandomShape(MAX_VOLUME/5);
            Announcer.announceShapeAction(shape, "creat", true);
            Announcer.announceShapeAction(shape, "add", box.add(shape));
            out.print(Console.Color.YELLOW.coloredString(String.format("The box is filled by %.2f%%%n%n", box.getFullness()*100)));
        }
    }
}
