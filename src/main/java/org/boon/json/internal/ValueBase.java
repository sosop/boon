package org.boon.json.internal;


import org.boon.Dates;
import org.boon.core.reflection.Conversions;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.boon.Exceptions.die;

public  class ValueBase extends Number implements Value, CharSequence, org.boon.core.Value {
    public int startIndex;
    public int endIndex;
    public Object value;

    public Type type;




    public ValueBase( Type type ) {
        this.type = type;
    }

    public ValueBase( Map<String, Object> map ) {
        this.value = map;
        this.type = Type.MAP;
    }

    public ValueBase( List<Object> list ) {
        this.value = list;
        this.type = Type.LIST;
    }

    public ValueBase() {
    }



    @Override
    public int intValue() {
        return Integer.parseInt ( toString () );
    }

    @Override
    public long longValue() {
        return Long.parseLong ( toString ()  );
    }

    @Override
    public float floatValue() {
        return Float.parseFloat ( toString ()  );

    }

    @Override
    public boolean booleanValue() {
        return false;
    }


    @Override
    public String stringValue() {
        return type.toString ();
    }

    @Override
    public String stringValueEncoded() {
        return toString ();
    }



    public String toString() {
        return type.toString ();
    }

    @Override
    public Object toValue() {
        if (value!=null) {
            return value;
        }
        switch ( type ) {
            case FALSE:
                return false;
            case TRUE:
                return true;
            case NULL:
                return null;
        }
        die();
        return null;

    }

    @Override
    public Enum toEnum( Class<? extends Enum> cls ) {
        switch ( type ) {
            case STRING:
                return Conversions.toEnum ( cls, stringValue () );
            case INTEGER:
                return Conversions.toEnum ( cls, intValue () );
            case NULL:
                return null;
        }
        die("toEnum " + cls + " value was " + stringValue ());
        return null;

    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt( int index ) {
        return '0';
    }

    @Override
    public CharSequence subSequence( int start, int end ) {
        return "";
    }


    @Override
    public Date dateValue() {

        if (type == Type.STRING) {

            String string = stringValue ();

            return Dates.fromISO8601 ( string );
        } else {

            return new Date (Dates.utc ( longValue ()));
        }

    }





    public byte byteValue() {
        return Byte.parseByte ( toString () );
    }

    public short shortValue() {
        return Short.parseShort ( toString () );
    }


    public BigDecimal bigDecimalValue() {
        return new BigDecimal ( toString () );
    }

    public BigInteger bigIntegerValue() {
        return new BigInteger ( toString () );
    }

    @Override
    public double doubleValue() {
        return Double.parseDouble ( toString ()  );
    }


}
