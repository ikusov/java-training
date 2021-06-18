package ru.ikusov.training.skillbox;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovementsTest extends TestCase {

    //TODO: test CSV String parser method
    public void testParseCSVString() {
        String CSVString = ",\",\"\",,,\",,";
        CSVString = "odin,dva dva, \"tri, tri, tri\",chetyre,pyat,,\"1488,888\"";

        String[] expectedS = {"", ",\",,,", "", ""};
        expectedS = new String[]{"odin", "dva dva", " tri, tri, tri", "chetyre", "pyat", "", "1488,888"};

        List<String> expected = Arrays.stream(expectedS).toList();
        List<String> actual = Movements.parseCSVString(CSVString);

        Assert.assertEquals(expected, actual);
    }
}