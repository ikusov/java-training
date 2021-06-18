package ru.ikusov.training.utils;

import static ru.ikusov.training.utils.MyMath.r;

public class Linguistic {
    private enum Gender {
        MALE("ов", "ый", "ин"),
        FEMALE("ова", "ая", "ина");

        String[] lastNameEnd;

        Gender(String... lastNameEnd) {
            this.lastNameEnd = lastNameEnd;
        }
    }

    public static String generateRussianName() {
        return generateRussianName(Math.random()<0.5 ? Gender.FEMALE : Gender.MALE);
    }

    private static String generateRussianName(Gender gender) {

        String[] consonant = "БВГДЖЗЙКЛМНПРСТФХЦЧШЩ".toLowerCase().split("");
        String[] doubleConsonant = new String[consonant.length * consonant.length];
        for (int i = 0; i<consonant.length; i++)
            for (int j=0; j<consonant.length; j++) {
                doubleConsonant[i*consonant.length+j] = consonant[i] + consonant[j];
            }

        char[] vowel = "АЕЁИОУЫЭЮЯ".toLowerCase().toCharArray();

        //last name end for specified gender
        String[] lastNameEnd = gender.lastNameEnd;

        int numSylsFirst = 2 + r(3);
        int numSylsLast = 2 + r(4);

        String firstName = "";

        //probably adding vowel to begin of the name
        firstName += r()<50 ? vowel[r(vowel.length)] : "";

        //generating main syllables of first name
        for (int i=0; i<numSylsFirst; i++) {
            //syllable contains consonant or double consonant (probability 80:20)
            firstName += r()<80
                    ? consonant[r(consonant.length)]
                    : doubleConsonant[r(doubleConsonant.length)];

            //syllable contains vowel, if it is not the last syllable
            // oh wait, female name can contain vowel at the end!
            if (i<numSylsFirst-1 || gender == Gender.FEMALE) {
                firstName += vowel[r(vowel.length)];
            }
        }

        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();

        String lastName = "";

        //probably adding vowel to begin of the name
        lastName += r()<50 ? vowel[r(vowel.length)] : "";

        //generating main syllables of first name
        for (int i=0; i<numSylsLast; i++) {
            //syllable contains consonant or double consonant (probability 80:20)
            lastName += r()<80
                    ? consonant[r(consonant.length)]
                    : doubleConsonant[r(doubleConsonant.length)];

            //syllable contains vowel, if it is not the last syllable
            if (i<numSylsLast-1) {
                lastName += vowel[r(vowel.length)];
            }
        }

        //add last name ending for specified gender
        lastName += lastNameEnd[r(lastNameEnd.length)];

        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();

        return firstName + " " + lastName;
    }

}
