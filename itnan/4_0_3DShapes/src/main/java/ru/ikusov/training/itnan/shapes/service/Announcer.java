package ru.ikusov.training.itnan.shapes.service;

import ru.ikusov.training.itnan.shapes.model.Shape;
import ru.ikusov.training.utils.Console;

import static java.lang.System.*;
import static ru.ikusov.training.utils.Console.p;
import static ru.ikusov.training.utils.MyMath.r;

public class Announcer {
    public static void announceShapeAction(Shape shape, String actionTitle, boolean success) throws InterruptedException {
        String name = shape.getClass().getSimpleName();
        String actioning = Character.toUpperCase(actionTitle.charAt(0)) + actionTitle.substring(1) + "ing";
        String actioned = actionTitle + "ed";
        String not = success ? "" : "not";
        String successfully = success ? "successfully" : "";
        Console.Color textColor = success ? Console.Color.GREEN : Console.Color.RED;

        int actionTime = r(30);
        out.printf("%s new %s, please wait", actioning, name);
        for(int i=0;i<actionTime; i++, Thread.sleep(r(300))) out.print(".");
        out.print(textColor.coloredString(String.format("\n%s has %s been %s %s!\n", name, not, successfully, actioned)));
    }
}
