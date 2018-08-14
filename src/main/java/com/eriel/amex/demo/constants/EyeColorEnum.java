package com.eriel.amex.demo.constants;

/**
 * Relevant to User's profiles.
 */
public enum EyeColorEnum {
    GREEN(0), BROWN(1), BLUE(2), BLACK(3), HAZEL(4);

    private int numVal;

    EyeColorEnum(int numVal){
        this.numVal = numVal;
    }

    public int getNumVal(){
        return numVal;
    }

    public static EyeColorEnum getByVal(int val){
        if(BROWN.numVal == val) return BROWN;
        if(BLUE.numVal == val) return BLUE;
        if(BLACK.numVal == val) return BLACK;
        if(HAZEL.numVal == val) return HAZEL;
        if(GREEN.numVal == val) return GREEN;
        else throw new IllegalArgumentException(String.format("The integer=%s does not correspond to any of the enumerated values.", val));
    }
}
