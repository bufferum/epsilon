package lts;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Allows you to get a list of all fields of the imported class.
 *
 * @returns <ul>
 *          <li>(Object) <code>field</code></li>
 *          <li>(String) <code>field_name</code></li>
 *      </ul>
 * @version 1.0
 * @author bufferum7011
 */
public class Declared_fields {


    ////////// Constructors //////////
    public Declared_fields() {}
    private Declared_fields(Object field, String field_name) {
        this.field = field;
        this.field_name = field_name;
    }


    ////////// Variables //////////
    private Object field;
    private String field_name;
    private static List<Declared_fields> list_declared_fields;
    public static int length;


    ////////// Methods //////////
    /**
     * This method collects instances variables, and their names in <code>list_declared_fields</code>.
     *
     * @return List< Declared_fields >
     *      <ul>
     *          <li>(Object) <code>field</code></li>
     *          <li>(String) <code>field_name</code></li>
     *      </ul>
     */
    public static List<Declared_fields> set_class(Object my_class) {

        Field[] fields = my_class.getClass().getDeclaredFields();
        length = fields.length;
        list_declared_fields = new ArrayList<>();

        for(int i = 0; i < length; i++) {

            Field field = fields[i];
            field.setAccessible(true);
            try {

                list_declared_fields.add(
                    new Declared_fields(
                        (my_class == null) ? "null" : (Object) field.get(my_class),
                        field.getName()
                    )
                );

            }
            catch(Exception e) {
                Print.error("[DECLARED_FIELD_SET_CLASS] - " + e + "\n");
            }

        }

        return list_declared_fields;
    }

    public static void to_string() {

        Print.logger_disable();
        String s = "{\n";

        try {

            for(int i = 0; i < list_declared_fields.size(); i++) {

                s += "    [class_name=" + ((list_declared_fields.get(i).field == null) ? "?null?" : (Object) list_declared_fields.get(i).field.getClass().getSimpleName());
                s += " | field_name=" + list_declared_fields.get(i).field_name.toString();
                s += " | field=" + ((list_declared_fields.get(i).field == null) ? "?null?" : (Object) list_declared_fields.get(i).field.toString()) + "]\n";

            }

        }
        catch(Exception e) { Print.error("[DECLARED_FIELD_TO_STRING] - " + e + "\n"); }
        finally {
            s += "}";
            Print.debag(s);
            Print.logger_enable();
        }

    }

    /** @return List< Declared_fields > <code>this.field</code> */
    public static Object get_field(int i) { return list_declared_fields.get(i).field; }

    /** @return List< Declared_fields > <code>this.field_name</code> */
    public static String get_field_name(int i) { return list_declared_fields.get(i).field_name; }

}